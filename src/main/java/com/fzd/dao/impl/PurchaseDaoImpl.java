package com.fzd.dao.impl;

import com.fzd.dao.PurchaseDao;
import com.fzd.model.PurchasingEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by FZD on 2017/3/20.
 */
@Repository("purchaseDao")
public class PurchaseDaoImpl extends HibernateImpl<PurchasingEntity, Integer> implements PurchaseDao<PurchasingEntity, Integer>{
    @Transactional
    @Override
    public BigDecimal sumPayoutByGoodsId(String hql, int goodsId) {
        Query query = getSession().createQuery(hql);
        query.setInteger(0,goodsId);
        return (BigDecimal) query.uniqueResult();
    }
}
