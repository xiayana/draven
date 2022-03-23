package com.lab8.engine.aspect;

import com.lab8.engine.annotation.CheckValidateParam;
import com.lab8.engine.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AspectUtils {
    @Autowired
    private RedisUtil redisUtil;
    private DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
    private ExpressionParser parser = new SpelExpressionParser();

    @Before(value = "@annotation(com.lab8.engine.annotation.CheckValidateParam)")
    public void validateParamsInRedis(JoinPoint join){
        log.info("进入切面");
        MethodSignature signature = (MethodSignature) join.getSignature();
        Method method = signature.getMethod();
        CheckValidateParam[] annotationsByType = method.getAnnotationsByType(CheckValidateParam.class);
        Object value=null;
        for (CheckValidateParam checkValidate:annotationsByType){
            String key = checkValidate.key();
            EvaluationContext context;
            Expression expression = parser.parseExpression(key);
            if(key.contains("#")&&"#".equals(key)==false){
                context= new StandardEvaluationContext();
                String[] parameterNames = discoverer.getParameterNames(method);
                Object[] args = join.getArgs();
                for (int i=0;i<parameterNames.length;i++){
                    context.setVariable(parameterNames[i],args[i]);
                }
                value = expression.getValue(context);
            }
            log.info("切面参数"+value);
            if(value!=null){
                boolean b = redisUtil.setNxEx(value.toString(), value.toString(), 50);
                if(b){
                    System.out.println("执行其他操作");
                }else {
                    System.out.println("不执行操作");
                }
            }
        }
    }
}
