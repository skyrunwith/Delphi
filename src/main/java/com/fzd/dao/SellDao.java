package com.fzd.dao;

import com.fzd.dao.impl.HibernateImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by FZD on 2017/3/20.
 */
public interface SellDao<T,ID extends Serializable> extends HibernateDao<T,ID>{

    BigDecimal findBySellTime(String hql,int goodsId, Date beginTime, Date endTime);

    BigDecimal sumPayoutByGoodsId(String hql,int goodsId);
}
