package com.lab8.engine.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.AlertdetailHadoop;
import com.lab8.engine.entity.DravenMetadata;
import com.lab8.engine.service.AlertdetailHadoopService;
import com.lab8.engine.service.EsperService;
import com.lab8.engine.service.StrategyService;
import com.lab8.engine.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class EsperListener implements UpdateListener {
    private static EsperListener esperListener;  // 静态初使化当前类
    private static Calendar cal = Calendar.getInstance();

    @Autowired
    private StrategyService test1Service;
    @Autowired
    private EsperService esperService;
    @Autowired
    private AlertdetailHadoopService alertdetailHadoopService;
    @Autowired
    private RedisUtil redisUtil;
    @PostConstruct
    public void statr1() {
        List<DravenMetadata> rules = test1Service.queryAll(new DravenMetadata());
        rules.forEach(rule ->
                esperService.addEsperListener(rule.getId(), rule.getEsperSql()));
    }

    @PostConstruct
    public void init() {
        esperListener = this;
    }

    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
        if (eventBeans != null) {
            AlertdetailHadoop alertdetailHadoop = new AlertdetailHadoop();
            if (eventBeans[CommonConstants.NUMBER_ZERO].get("timestamp").toString()!=null &&
                    eventBeans[CommonConstants.NUMBER_ZERO].get("ip").toString()!=null &&
                    eventBeans[CommonConstants.NUMBER_ZERO].get("user").toString()!=null
            ){
                alertdetailHadoop.setTimestamp(eventBeans[CommonConstants.NUMBER_ZERO].get("timestamp").toString());
                alertdetailHadoop.setHostname(eventBeans[CommonConstants.NUMBER_ZERO].get("ip").toString());
                alertdetailHadoop.setAlertsource(eventBeans[CommonConstants.NUMBER_ZERO].get("user").toString());
            }
            alertdetailHadoop.setSite("sanbox");
            alertdetailHadoop.setAlertcontext(eventBeans[CommonConstants.NUMBER_ZERO].getUnderlying().toString());
            alertdetailHadoop.setAlertexecutorid("hdfsAuditLogAlertExecutor");
            alertdetailHadoop.setPolicyid(eventBeans[CommonConstants.NUMBER_ZERO].get("pilicy_id").toString());
           //该策略需要去重的时间
            if (Integer.valueOf((String) eventBeans[CommonConstants.NUMBER_ZERO].get("time_reweight")) > CommonConstants.NUMBER_ZERO) {
                boolean b = esperListener.redisUtil.setNxExMin(eventBeans[CommonConstants.NUMBER_ZERO].get("pilicy_id").toString()
                        ,eventBeans[CommonConstants.NUMBER_ZERO].get("pilicy_id").toString(),Long.valueOf((String) eventBeans[CommonConstants.NUMBER_ZERO].get("time_reweight")));
                if(b){
                    esperListener.alertdetailHadoopService.insert(alertdetailHadoop);
                    log.info("esperResult insert success !!");
                }
              /*  Date date = esperListener.alertdetailHadoopService.selectCreateTimeDesc(alertdetailHadoop);
                if (date != null) {
                    cal.setTime(date);
                    long cTime = Calendar.getInstance().getTimeInMillis() - cal.getTimeInMillis();
                    long mTime = (cTime / CommonConstants.NUMBER_ONE_THOUSAND) / CommonConstants.NUMBER_SIXTY;
                    if (mTime > Integer.valueOf(time)) {
                        esperListener.alertdetailHadoopService.insert(alertdetailHadoop);
                    }
                } else {
                    esperListener.alertdetailHadoopService.insert(alertdetailHadoop);
                }*/
            } else {
                esperListener.alertdetailHadoopService.insert(alertdetailHadoop);
                log.info("esperResult insert success !!");
            }
            alertdetailHadoop=null;
          //  eventBeans =null;
        /*    System.out.println(String.format
                    ("匹配成功，匹配到的cmd为：%s, dst为：%s,src为：%s,user为：%s,时间：%s,原始内容：%s,备用：%s",
                            eventBeans[0].get("cmd"),
                            eventBeans[0].get("dst"),
                            eventBeans[0].get("src"),
                            eventBeans[0].get("user"),
                            System.currentTimeMillis(),
                            eventBeans[0].getUnderlying(),
                            eventBeans[0].get("pilicy_id")
                    ));*/
        }
    }
}
