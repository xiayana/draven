package com.lab8.engine.service;

/**
 * @author xy
 */
public interface EsperService {

    void addEsperListener(int id, String esperSql);

    void removeEsperListener(int id);
}
