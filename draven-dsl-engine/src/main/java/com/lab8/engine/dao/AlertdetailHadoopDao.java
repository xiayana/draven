package com.lab8.engine.dao;

import com.lab8.engine.entity.AlertdetailHadoop;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Ueba告警日志(AlertdetailHadoop)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-07 15:56:09
 */
@Mapper
public interface AlertdetailHadoopDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AlertdetailHadoop queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AlertdetailHadoop> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param alertdetailHadoop 实例对象
     * @return 对象列表
     */
    List<AlertdetailHadoop> queryAll(AlertdetailHadoop alertdetailHadoop);

    /**
     * 新增数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 影响行数
     */
    int insert(AlertdetailHadoop alertdetailHadoop);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlertdetailHadoop> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AlertdetailHadoop> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AlertdetailHadoop> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<AlertdetailHadoop> entities);

    /**
     * 修改数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 影响行数
     */
    int update(AlertdetailHadoop alertdetailHadoop);

    Date selectCreateTimeDesc(@Param("policyid") String policyid);
}

