package com.lab8.engine.service.impl;

import com.lab8.engine.entity.Test1;
import com.lab8.engine.dao.Test1Dao;
import com.lab8.engine.service.Test1Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Test1)表服务实现类
 *
 * @author makejava
 * @since 2022-02-04 18:49:03
 */
@Service
public class Test1ServiceImpl implements Test1Service {
    @Autowired
    private Test1Dao test1Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Test1 queryById(Integer id) {
        return this.test1Dao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Test1> queryAllByLimit(int offset, int limit) {
        return this.test1Dao.queryAllByLimit(offset, limit);
    }


    /**
     * 新增数据
     *
     * @param test1 实例对象
     * @return 实例对象
     */
    @Override
    public Test1 insert(Test1 test1) {
        this.test1Dao.insert(test1);
        return test1;
    }

    /**
     * 修改数据
     *
     * @param test1 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Test1 test1) {
        return test1Dao.update(test1);
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
            Test1 test1 = new Test1();
            test1.setId(id);
            i += test1Dao.update(test1);
        }
        return i;
    }
}
