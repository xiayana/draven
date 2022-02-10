package com.deavenapiweb.service;

import com.deavenapiweb.entity.AlertdetailHadoop;
import java.util.List;
import com.github.pagehelper.PageInfo;

/**
 * Ueba告警日志(AlertdetailHadoop)表服务接口
 *
 * @author makejava
 * @since 2022-02-09 11:29:27
 */
public interface AlertdetailHadoopService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AlertdetailHadoop queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AlertdetailHadoop> queryAllByLimit(int offset, int limit);

    /**
     * 分页查询
     * @param alertdetailHadoop 实例对象
     * @return 分页对象
     */
    PageInfo<AlertdetailHadoop> pageuery(AlertdetailHadoop alertdetailHadoop);

    /**
     * 新增数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 实例对象
     */
    AlertdetailHadoop insert(AlertdetailHadoop alertdetailHadoop);

    /**
     * 修改数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 修改的记录数
     */
    int update(AlertdetailHadoop alertdetailHadoop);

    /**
     * 批量删除数据
     * @param ids
     * @return
     */
    int deleteBatch(List<Integer> ids);
}
