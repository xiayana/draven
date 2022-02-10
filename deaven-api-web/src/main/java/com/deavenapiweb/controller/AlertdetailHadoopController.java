package com.deavenapiweb.controller;

import com.deavenapiweb.common.ResponseCode;
import com.deavenapiweb.dto.ResDto;
import com.deavenapiweb.entity.AlertdetailHadoop;
import com.deavenapiweb.exception.ServiceException;
import com.deavenapiweb.service.AlertdetailHadoopService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Ueba告警日志(AlertdetailHadoop)表控制层
 *
 * @author makejava
 * @since 2022-02-09 11:29:26
 */
@RestController
@RequestMapping("alertdetailHadoop")
@Api(value = "AlertdetailHadoopController", tags = "Ueba告警日志(AlertdetailHadoop)表控制层")
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
    @ApiOperation(value = "通过主键查询单条数据", httpMethod = "GET")
    @GetMapping("/selectOne")
    public AlertdetailHadoop selectOne(Long id) {
        return this.alertdetailHadoopService.queryById(id);
    }

    /**
     * 分页查询数据
     *
     * @param alertdetailHadoop 查询对象
     * @return 分页数据
     */
    @ApiOperation(value = "分页查询数据", httpMethod = "POST")
    @PostMapping("/pageQuery")
    public ResDto<PageInfo<AlertdetailHadoop>> pageQuery(@RequestBody @Validated AlertdetailHadoop alertdetailHadoop) {
        if (alertdetailHadoop.getPageNo() == null) {
            throw new ServiceException(ResponseCode.PARAMETER_IS_NULL, "pageNo");
        }
        if (alertdetailHadoop.getPageSize() == null) {
            throw new ServiceException(ResponseCode.PARAMETER_IS_NULL, "pageSize");
        }
        PageInfo<AlertdetailHadoop> pageuery = alertdetailHadoopService.pageuery(alertdetailHadoop);
        return new ResDto<>(pageuery);
    }

    /**
     * 删除数据
     *
     * @param param ID数组
     * @return 分页数据
     */
    @ApiOperation(value = "删除数据", httpMethod = "POST")
    @PostMapping("/delete")
    public ResDto<Integer> delete(@RequestBody Map<String, List<Integer>> param) {
        if (CollectionUtils.isEmpty(param.get("ids"))) {
            throw new ServiceException(ResponseCode.PARAMETER_IS_NULL, "ids");
        }
        int num = alertdetailHadoopService.deleteBatch(param.get("ids"));
        return new ResDto<>(num);
    }


}
