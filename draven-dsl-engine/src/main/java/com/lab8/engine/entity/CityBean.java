package com.lab8.engine.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**

 * 城市信息

 *

 * @ClassName: CityBean

 * @date: 2018-12-26 下午12:33:18

 */

public class CityBean implements Serializable {

    private static final long serialVersionUID = 4443714978780088961L;

    /** 城市名称 */

    String CITY_NAME;

    /** 电话区号 */

    String AREA_CODE;

    /** 邮政编码 */

    String POSTAL_CODE;

    /** 行政区划代码 */

    String AREA_NUMBER;

    /** 城市描述 */

    String DESCRIBE;

    @JSONField(name = "CITY_NAME")

    public String getCITY_NAME() {

        return CITY_NAME;

    }

    public void setCITY_NAME(String cITY_NAME) {

            CITY_NAME = cITY_NAME;

    }

    @JSONField(name = "AREA_CODE")

    public String getAREA_CODE() {

        return AREA_CODE;

    }

    public void setAREA_CODE(String aREA_CODE) {

            AREA_CODE = aREA_CODE;

    }

    @JSONField(name = "POSTAL_CODE")

    public String getPOSTAL_CODE() {

        return POSTAL_CODE;

    }

    public void setPOSTAL_CODE(String pOSTAL_CODE) {

            POSTAL_CODE = pOSTAL_CODE;

    }

    @JSONField(name = "AREA_NUMBER")

    public String getAREA_NUMBER() {

        return AREA_NUMBER;

    }

    public void setAREA_NUMBER(String aREA_NUMBER) {

            AREA_NUMBER = aREA_NUMBER;

    }

    @JSONField(name = "DESCRIBE")

    public String getDESCRIBE() {

        return DESCRIBE;

    }

    public void setDESCRIBE(String dESCRIBE) {

            DESCRIBE = dESCRIBE;

    }

    @Override
    public String toString() {

        return "CityBean [CITY_NAME=" + CITY_NAME

                +",AREA_CODE=" + AREA_CODE

                +",POSTAL_CODE=" + POSTAL_CODE

                +",AREA_NUMBER=" + AREA_NUMBER

                +",DESCRIBE=" + DESCRIBE

                +"]";

    }

}