package com.deavenapiweb.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * page分页类
 *
 * @author xy
 * @since 2022-02-09 12:03:57
 */
@Data
public class Page implements Serializable {

    private Integer pageNo;
    private Integer pageSize;
}
