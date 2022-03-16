package com.lab8.engine.controller;

import com.lab8.engine.entity.AlertdetailHadoop;
import com.lab8.engine.service.AlertdetailHadoopService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Ueba告警日志(AlertdetailHadoop)表控制层
 *
 * @author makejava
 * @since 2022-02-07 15:56:09
 */
@RestController
@RequestMapping("alertdetailHadoop")
public class AlertdetailHadoopController {
    /**
     * 服务对象
     */
    @Autowired
    private AlertdetailHadoopService alertdetailHadoopService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public AlertdetailHadoop selectOne(Long id) {
        return this.alertdetailHadoopService.queryById(id);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param alertdetailHadoop 主键
     * @return 单条数据
     */
    @PostMapping("/insert")
    public AlertdetailHadoop insert(@RequestBody AlertdetailHadoop alertdetailHadoop) {
        return this.alertdetailHadoopService.insert(alertdetailHadoop);
    }
}
