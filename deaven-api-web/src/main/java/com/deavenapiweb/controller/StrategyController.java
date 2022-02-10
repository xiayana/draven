package com.deavenapiweb.controller;

import com.deavenapiweb.common.ResponseCode;
import com.deavenapiweb.constants.CommonConstants;
import com.deavenapiweb.dto.ResDto;
import com.deavenapiweb.entity.AlertdetailHadoop;
import com.deavenapiweb.entity.DravenMetadata;
import com.deavenapiweb.exception.ServiceException;
import com.deavenapiweb.service.StrategyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dravenMetadata")
@Api(value = "StrategyController", tags = "元数据表控制层")
public class StrategyController {
    @Autowired
    private StrategyService strategyService;
    /**
     * 分页查询数据
     *
     * @param dravenMetadata 查询对象
     * @return 分页数据
     */
    @ApiOperation(value = "分页查询数据", httpMethod = "POST")
    @PostMapping("/pageQuery")
    public ResDto<PageInfo<DravenMetadata>> pageQuery(@RequestBody @Validated DravenMetadata dravenMetadata) {
        if (dravenMetadata.getPageNo() == null) {
            throw new ServiceException(ResponseCode.PARAMETER_IS_NULL, "pageNo");
        }
        if (dravenMetadata.getPageSize() == null) {
            throw new ServiceException(ResponseCode.PARAMETER_IS_NULL, "pageSize");
        }
        PageInfo<DravenMetadata> pageuery = strategyService.pageuery(dravenMetadata);
        return new ResDto<>(pageuery);
    }
    /**
     * 新增策略
     *
     * @param dravenMetadata 新增对象参数
     * @return 状态
     */
    @ApiOperation(value = "新增策略", httpMethod = "POST")
    @PostMapping(value = "/create", produces = "application/json;charset=utf-8")
    public ResDto<DravenMetadata> insert(@RequestBody @Validated DravenMetadata dravenMetadata, HttpServletRequest request) {
        if (dravenMetadata.getPolicyId() == null || dravenMetadata.getPolicyId().isEmpty() ){
            return new ResDto<>(CommonConstants.NUMBER_TWO, CommonConstants.ERROR_PARAMETERNULL);
        }
       DravenMetadata result = strategyService.queryPid(dravenMetadata.getPolicyId());
        if (result != null){
            return new ResDto<>(CommonConstants.NUMBER_TWO, CommonConstants.ERROR_DUPLICATENAME);
        }
        DravenMetadata dataResult = strategyService.insert(dravenMetadata);
        return new ResDto<>(dataResult);
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
        int num = strategyService.deleteBatch(param.get("ids"));
        return new ResDto<>(num);
    }
}
