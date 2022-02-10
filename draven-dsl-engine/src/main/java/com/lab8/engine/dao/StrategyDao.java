package com.lab8.engine.dao;

import com.lab8.engine.entity.DravenMetadata;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * (Test1)表数据库访问层
 *
 * @author xy
 * @since 2022-02-04 18:49:00
 */
@Mapper
public interface StrategyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DravenMetadata queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DravenMetadata> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param test1 实例对象
     * @return 对象列表
     */
    List<DravenMetadata> queryAll(DravenMetadata test1);

    /**
     * 新增数据
     *
     * @param test1 实例对象
     * @return 影响行数
     */
    int insert(DravenMetadata test1);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Test1> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DravenMetadata> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Test1> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<DravenMetadata> entities);

    /**
     * 修改数据
     *
     * @param test1 实例对象
     * @return 影响行数
     */
    int update(DravenMetadata test1);

}

