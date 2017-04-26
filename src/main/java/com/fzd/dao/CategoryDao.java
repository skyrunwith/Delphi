package com.fzd.dao;

import java.io.Serializable;

/**
 * Created by FZD on 2017/3/16.
 */
public interface CategoryDao<T, ID extends Serializable> extends HibernateDao<T, ID>{
}
