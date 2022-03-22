package com.lab8.engine.service.impl;

import com.lab8.engine.entity.ErrorLog;
import com.lab8.engine.dao.ErrorLogDao;
import com.lab8.engine.service.ErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ErrorLog)表服务实现类
 *
 * @author xy
 * @since 2022-03-21 17:51:15
 */
@Service("errorLogService")
public class ErrorLogServiceImpl implements ErrorLogService {
    @Resource
    private ErrorLogDao errorLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ErrorLog queryById(Integer id) {
        return this.errorLogDao.queryById(id);
    }


    @Override
    public  List<ErrorLog> queryAll(ErrorLog errorLog){
        return errorLogDao.queryAll(errorLog);
    }


    /**
     * 新增数据
     *
     * @param errorLog 实例对象
     * @return 实例对象
     */
    @Override
    public ErrorLog insert(ErrorLog errorLog) {
        this.errorLogDao.insert(errorLog);
        return errorLog;
    }

    /**
     * 修改数据
     *
     * @param errorLog 实例对象
     * @return 实例对象
     */
    @Override
    public ErrorLog update(ErrorLog errorLog) {
        this.errorLogDao.update(errorLog);
        return this.queryById(errorLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.errorLogDao.deleteById(id) > 0;
    }
}
