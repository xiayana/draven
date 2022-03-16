package com.lab8.engine.controller;

import com.lab8.engine.constants.CommonConstants;
import com.lab8.engine.entity.ResultData;
import com.lab8.engine.enume.ResponseCodeEnum;
import com.lab8.engine.service.EsperService;
import com.mysql.jdbc.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/esperListener")
public class EsperListenerController {
    @Autowired
    private EsperService esperService;

    @GetMapping("/addListener")
    public ResultData addListener(Integer id, String sql) {
        ResultData resultData = new ResultData();
        if (id == null || StringUtils.isEmpty(sql)) {
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_NULL.getCode());
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_NULL.getMessage());
            return resultData;
        }
        resultData = esperService.addEsperListener(id, sql);
        if ( resultData.getCode() != CommonConstants.NUMBER_ZERO) {
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_INCOMPLETE.getCode());
            return resultData;
        }
        log.info(">>>>>> successSQL:ID= {}, SQL= {}", id, sql);
        return resultData;
    }

    @GetMapping("/removeListener")
    public ResultData removeListener(String id) {
        ResultData resultData = new ResultData();
        if (id == null) {
            resultData.setCode(ResponseCodeEnum.ERROR_DATA_NULL.getCode());
            resultData.setMsg(ResponseCodeEnum.ERROR_DATA_NULL.getMessage());
            return resultData;
        }
        esperService.removeEsperListener(Integer.valueOf(id));
        return resultData;
    }

}
