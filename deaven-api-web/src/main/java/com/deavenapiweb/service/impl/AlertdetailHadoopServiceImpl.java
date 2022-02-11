package com.deavenapiweb.service.impl;

import com.deavenapiweb.entity.AlertdetailHadoop;
import com.deavenapiweb.dao.AlertdetailHadoopDao;
import com.deavenapiweb.service.AlertdetailHadoopService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * Ueba告警日志(AlertdetailHadoop)表服务实现类
 *
 * @author makejava
 * @since 2022-02-09 11:29:27
 */
@Service
public class AlertdetailHadoopServiceImpl implements AlertdetailHadoopService {
    @Resource
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

    @Override
    public List<AlertdetailHadoop> queryAllByLimit(int offset, int limit) {
        return null;
    }


    /**
     * 分页查询
     * @param alertdetailHadoop 实例对象
     * @return 分页对象
     */
    @Override
    public PageInfo<AlertdetailHadoop> pageuery(AlertdetailHadoop alertdetailHadoop) {
        PageHelper.startPage(alertdetailHadoop.getPageNo(), alertdetailHadoop.getPageSize());
        List<AlertdetailHadoop> list = alertdetailHadoopDao.queryAll(alertdetailHadoop);
        return new PageInfo<>(list);
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
            AlertdetailHadoop alertdetailHadoop = new AlertdetailHadoop();
            alertdetailHadoop.setId(id);
            i += alertdetailHadoopDao.update(alertdetailHadoop);
        }
        return i;
    }
}
