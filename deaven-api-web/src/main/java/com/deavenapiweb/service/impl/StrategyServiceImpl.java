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
     * @param dravenMetadata 实例对象
     * @return 实例对象
     */
    @Override
    public DravenMetadata insert(DravenMetadata dravenMetadata) {
        dravenMetadata.setEsperSql(dealWith(dravenMetadata));
        log.info("esperSql:[{}]",dravenMetadata.getEsperSql());
        this.strategyDao.insert(dravenMetadata);
        if (dravenMetadata.getId() !=null){
            try {
                getAddListener(dravenMetadata.getId(),dravenMetadata.getEsperSql());
            }catch (Exception e){
                log.error("调用异常 ：" +e.getMessage(),e);
            }
        }
        return dravenMetadata;
    }

    /**
     * 将字段处理组合成策略sql
     * @param dravenMetadata
     * @return
     */
    public String dealWith(DravenMetadata dravenMetadata){
        StringBuffer sb =new StringBuffer();
        sb.append("select cmd,dst,user,src,pid,ppid,time,ip,");
        sb.append("'"+dravenMetadata.getPolicyId()+"' as id ");
        sb.append("from mobillocaltion  ");
        sb.append("where 1 = 1 ");
        if (dravenMetadata !=null) {
            if (null != dravenMetadata.getCmd() || !dravenMetadata.getCmd().equals("")) {
                sb.append(" and " + dravenMetadata.getCmd() + "");
            }
            if (dravenMetadata.getUser() != null || !dravenMetadata.getUser().equals("")) {
                sb.append(" and " + dravenMetadata.getUser() + "");
            }
            if (dravenMetadata.getDst() != null || !dravenMetadata.getDst().equals("")) {
                sb.append(" and " + dravenMetadata.getDst() + "");
            }
            if (dravenMetadata.getSrc() != null || !dravenMetadata.getSrc().equals("")) {
                sb.append(" and " + dravenMetadata.getSrc() + "");
            }
            if (dravenMetadata.getPid() != null || !dravenMetadata.getPid().equals("")) {
                sb.append(" and " + dravenMetadata.getPid() + "");
            }
            if (dravenMetadata.getPpid() != null || !dravenMetadata.getPpid().equals("")) {
                sb.append(" and " + dravenMetadata.getPpid() + "");
            }
            if (dravenMetadata.getTime() != null || !dravenMetadata.getTime().equals("")) {
                sb.append(" and " + dravenMetadata.getTime() + "");
            }
            if (dravenMetadata.getIp() != null || !dravenMetadata.getIp().equals("")) {
                sb.append(" and " + dravenMetadata.getIp() + "");
            }
        }
        return sb.toString();
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
     * @param dravenMetadata 实例对象
     * @return 实例对象
     */
    @Override
    public int update(DravenMetadata dravenMetadata) {
        return strategyDao.update(dravenMetadata);
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
            DravenMetadata dravenMetadata = new DravenMetadata();
            dravenMetadata.setId(id);
            dravenMetadata.setStatus(0);
            i += strategyDao.update(dravenMetadata);
            if (i != CommonConstants.NUMBER_ZERO){
                getRemoveListener(id);
            }
        }
        return i;
    }
    public void getRemoveListener(Integer id) {
        try {
            final String url = "http://" + servers +"/esperListener/removeListener?id="+id+"";
            log.info(">>>>>> url: {}, ip: {}, id: {}", url, servers, id);
            String string = OkHttpUtil.getInstance().getData(url);
            log.info("skipTask>>>>>> response: {}", string);
        }catch (Exception e){
            log.error("远程调用异常" + e.getMessage(), e);
        }

    }
    public void getAddListener(Integer id,String sql) {
        try {
            final String url = "http://" + servers +"/esperListener/addListener?id="+id+"&sql="+sql+"";
            log.info(">>>>>> url: {}, ip: {}, id: {}, sql: {}", url, servers, id, sql);
            String string = OkHttpUtil.getInstance().getData(url);
            log.info("skipTask>>>>>> response: {}", string);
        }catch (Exception e){
            log.error("远程调用异常" + e.getMessage(), e);
        }

    }
    @Override
    public List<DravenMetadata> queryAll(DravenMetadata dravenMetadata) {
        return strategyDao.queryAll(dravenMetadata);
    }

    @Override
    public DravenMetadata queryPid(String policyId) {
        return this.strategyDao.queryPid(policyId);
    }
}
