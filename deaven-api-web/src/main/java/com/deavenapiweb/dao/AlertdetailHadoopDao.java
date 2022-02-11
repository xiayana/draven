package com.deavenapiweb.dao;

import com.deavenapiweb.entity.AlertdetailHadoop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Ueba告警日志(AlertdetailHadoop)表数据库访问层
 *
 * @author xy
 * @since 2022-02-10 18:15:19
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
     * 统计总行数
     *
     * @param alertdetailHadoop 查询条件
     * @return 总行数
     */
    long count(AlertdetailHadoop alertdetailHadoop);

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
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AlertdetailHadoop> entities);

    /**
     * 修改数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 影响行数
     */
    int update(AlertdetailHadoop alertdetailHadoop);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<AlertdetailHadoop> queryAll(AlertdetailHadoop alertdetailHadoop);
}

