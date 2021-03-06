package com.lab8.engine.service;

import com.lab8.engine.entity.DravenMetadata;

import java.util.List;

/**
 * (Test1)表服务接口
 *
 * @author makejava
 * @since 2022-02-04 18:49:02
 */
public interface StrategyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DravenMetadata queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DravenMetadata> queryAllByLimit(int offset, int limit);


    /**
     * 新增数据
     *
     * @param test1 实例对象
     * @return 实例对象
     */
    DravenMetadata insert(DravenMetadata test1);

    /**
     * 修改数据
     *
     * @param test1 实例对象
     * @return 修改的记录数
     */
    int update(DravenMetadata test1);

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    int deleteBatch(List<Integer> ids);

    /**
     * 查询所有规则
     *
     * @return List
     */
    List<DravenMetadata> queryAll(DravenMetadata test1);
}
