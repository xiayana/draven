package com.lab8.engine.service;

import com.lab8.engine.entity.ResultData;

/**
 * @author xy
 */
public interface EsperService {

    ResultData addEsperListener(int id, String esperSql);

    void removeEsperListener(int id);
}
