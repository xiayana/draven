package com.lab8.engine.dao;

import com.lab8.engine.entity.ErrorLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (ErrorLog)表数据库访问层
 *
 * @author xy
 * @since 2022-03-21 17:51:13
 */
@Mapper
public interface ErrorLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ErrorLog queryById(Integer id);


    /**
     * 统计总行数
     *
     * @param errorLog 查询条件
     * @return 总行数
     */
    long count(ErrorLog errorLog);

    /**
     * 新增数据
     *
     * @param errorLog 实例对象
     * @return 影响行数
     */
    int insert(ErrorLog errorLog);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ErrorLog> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ErrorLog> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ErrorLog> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ErrorLog> entities);

    /**
     * 修改数据
     *
     * @param errorLog 实例对象
     * @return 影响行数
     */
    int update(ErrorLog errorLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
    List<ErrorLog> queryAll(ErrorLog errorLog);
}

