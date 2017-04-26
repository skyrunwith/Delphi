package com.fzd.dao;

import com.fzd.model.UserEntity;

import java.io.Serializable;

/**
 * Created by FZD on 2017/4/23.
 */
public interface UserDao<T,ID extends Serializable> extends HibernateDao<T,ID> {
    UserEntity findByUserName(String hql, String username);
    void delByUserName(String hql,String username);
}
