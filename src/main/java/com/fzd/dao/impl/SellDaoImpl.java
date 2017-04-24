package com.fzd.dao.impl;

import com.fzd.dao.SellDao;
import com.fzd.model.SellEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by FZD on 2017/3/20.
 */
@Repository("sellDao")
public class SellDaoImpl extends HibernateImpl<SellEntity,Integer> implements SellDao<SellEntity,Integer>{
    @Override
    public BigDecimal findBySellTime(String hql,int goodsId, Date beginTime, Date endTime) {
        Query query = getSession().createQuery(hql);
        query.setInteger(0,goodsId);
        query.setTimestamp(1,beginTime);
        query.setTimestamp(2,endTime);
        return (BigDecimal) query.uniqueResult();
    }

    @Override
    public BigDecimal sumPayoutByGoodsId(String hql, int goodsId) {
        Query query = getSession().createQuery(hql);
        query.setInteger(0,goodsId);
        return (BigDecimal) query.uniqueResult();
    }
}
