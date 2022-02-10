package com.deavenapiweb.service.impl;

import com.deavenapiweb.constants.CommonConstants;
import com.deavenapiweb.dao.StrategyDao;
import com.deavenapiweb.entity.AlertdetailHadoop;
import com.deavenapiweb.entity.DravenMetadata;
import com.deavenapiweb.service.StrategyService;
import com.deavenapiweb.utils.OkHttpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * (Test1)表服务实现类
 *
 * @author xy
 * @since 2022-02-04 18:49:03
 */
@Slf4j
@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    private StrategyDao strategyDao;
    @Value("${dsl.engine.server}")
    private String servers;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DravenMetadata queryById(Integer id) {
        return this.strategyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<DravenMetadata> queryAllByLimit(int offset, int limit) {
        return this.strategyDao.queryAllByLimit(offset, limit);
    }


    /**
     * 新增数据
     *
     * @param test1 实例对象
     * @return 实例对象
     */
    @Override
    public DravenMetadata insert(DravenMetadata test1) {
        this.strategyDao.insert(test1);
        return test1;
    }
    /**
     * 分页查询
     * @param dravenMetadata 实例对象
     * @return 分页对象
     */
    @Override
    public PageInfo<DravenMetadata> pageuery(DravenMetadata dravenMetadata) {
        PageHelper.startPage(dravenMetadata.getPageNo(), dravenMetadata.getPageSize());
        List<DravenMetadata> list = strategyDao.queryAll(dravenMetadata);
        return new PageInfo<>(list);
    }
    /**
     * 修改数据
     *
     * @param test1 实例对象
     * @return 实例对象
     */
    @Override
    public int update(DravenMetadata test1) {
        return strategyDao.update(test1);
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
            DravenMetadata test1 = new DravenMetadata();
            test1.setId(id);
            test1.setStatus(0);
            i += strategyDao.update(test1);
            if (i != CommonConstants.NUMBER_ZERO){
                getResources(id);
            }
        }

        return i;
    }
    public void getResources(Integer id) {
        try {
            final String url = "http://" + servers +"/esperListener/removeListener?id="+id+"";
            log.info(">>>>>> url: {}, ip: {}, id: {}", url, servers, id);
            String string = OkHttpUtil.getInstance().getData(url);
            log.info("skipTask>>>>>> response: {}", string);
        }catch (Exception e){
            log.error("远程调用异常" + e.getMessage(), e);
        }

    }
    @Override
    public List<DravenMetadata> queryAll(DravenMetadata test1) {
        return strategyDao.queryAll(test1);
    }
}
