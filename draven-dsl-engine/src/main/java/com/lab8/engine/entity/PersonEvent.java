package com.lab8.engine.entity;

import lombok.Data;

@Data
public class PersonEvent {
    private Integer id;
    private String sql;
    private String uuid;
}
