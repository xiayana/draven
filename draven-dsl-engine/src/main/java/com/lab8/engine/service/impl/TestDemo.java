package com.lab8.engine.service.impl;

import com.lab8.engine.annotation.CheckValidateParam;
import com.lab8.engine.entity.ResultData;
import com.lab8.engine.service.EsperService;
import org.springframework.stereotype.Service;

@Service
public class TestDemo  implements EsperService {

    @Override
    @CheckValidateParam(key="#id")
    public ResultData addEsperListener(int id, String esperSql) {
        return null;
    }

    @Override
    public void removeEsperListener(int id) {

    }
}
