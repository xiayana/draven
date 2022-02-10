package com.lab8.engine.controller;

import com.lab8.engine.entity.DravenMetadata;
import com.lab8.engine.service.StrategyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Test1)表控制层
 *
 * @author xy
 * @since 2022-02-04 18:48:58
 */
@RestController
@RequestMapping("test1")
public class Test1Controller {
    /**
     * 服务对象
     */
    @Autowired
    private StrategyService test1Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public DravenMetadata selectOne(Integer id) {
        return this.test1Service.queryById(id);
    }




}
