package com.lab8.engine.service.impl;

import com.lab8.engine.entity.AlertdetailHadoop;
import com.lab8.engine.dao.AlertdetailHadoopDao;
import com.lab8.engine.service.AlertdetailHadoopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Ueba告警日志(AlertdetailHadoop)表服务实现类
 *
 * @author makejava
 * @since 2022-02-07 15:56:09
 */
@Service
public class AlertdetailHadoopServiceImpl implements AlertdetailHadoopService {
    @Autowired
    private AlertdetailHadoopDao alertdetailHadoopDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AlertdetailHadoop queryById(Long id) {
        return this.alertdetailHadoopDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AlertdetailHadoop> queryAllByLimit(int offset, int limit) {
        return this.alertdetailHadoopDao.queryAllByLimit(offset, limit);
    }


    /**
     * 新增数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 实例对象
     */
    @Override
    public AlertdetailHadoop insert(AlertdetailHadoop alertdetailHadoop) {
        this.alertdetailHadoopDao.insert(alertdetailHadoop);
        return alertdetailHadoop;
    }

    /**
     * 修改数据
     *
     * @param alertdetailHadoop 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AlertdetailHadoop alertdetailHadoop) {
        return alertdetailHadoopDao.update(alertdetailHadoop);
    }

    /**
     * 批量删除数据
     *
     * @param ids ID数组
     * @return 修改的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteBatch(List<Integer> ids) {
        int i = 0;
        for (Integer id : ids) {
            AlertdetailHadoop alertdetailHadoop =  AlertdetailHadoop.builder().id(id).build();
            i += alertdetailHadoopDao.update(alertdetailHadoop);
        }
        return i;
    }
}
