package com.fzd.dao.impl;

import com.fzd.dao.GoodsDao;
import com.fzd.model.GoodsEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by FZD on 2017/3/17.
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends HibernateImpl<GoodsEntity, Integer> implements GoodsDao<GoodsEntity, Integer>{

}
