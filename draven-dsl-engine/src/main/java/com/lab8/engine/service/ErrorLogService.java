package com.lab8.engine.service;

import com.lab8.engine.entity.ErrorLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (ErrorLog)表服务接口
 *
 * @author xy
 * @since 2022-03-21 17:51:14
 */
public interface ErrorLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ErrorLog queryById(Integer id);



    /**
     * 新增数据
     *
     * @param errorLog 实例对象
     * @return 实例对象
     */
    ErrorLog insert(ErrorLog errorLog);

    /**
     * 修改数据
     *
     * @param errorLog 实例对象
     * @return 实例对象
     */
    ErrorLog update(ErrorLog errorLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    List<ErrorLog> queryAll(ErrorLog errorLog);

}
