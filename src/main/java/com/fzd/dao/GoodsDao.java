package com.fzd.dao;

import java.io.Serializable;

/**
 * Created by FZD on 2017/3/17.
 */
public interface GoodsDao<T,ID extends Serializable> extends HibernateDao<T,ID>{

}
