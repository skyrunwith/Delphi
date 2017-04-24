package com.fzd.dao;

import java.io.Serializable;

/**
 * Created by FZD on 2017/3/20.
 */
public interface ProducerDao<T,ID extends Serializable> extends HibernateDao<T,ID>{
}
