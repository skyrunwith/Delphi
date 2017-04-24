package com.fzd.dao.impl;

import com.fzd.dao.SellDao;
import com.fzd.dao.UserDao;
import com.fzd.model.SellEntity;
import com.fzd.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by FZD on 2017/4/23.
 */
@Transactional
@Repository("userDao")
public class UserDaoImpl extends HibernateImpl<UserEntity,Integer> implements UserDao<UserEntity,Integer> {

    @Override
    public UserEntity findByUserName(String hql,String username) {
        Query query =  getSession().createQuery(hql);
        query.setString(0,username);
        return (UserEntity) query.uniqueResult();
    }

    @Override
    public void delByUserName(String hql, String username) {
        Query query = getSession().createQuery(hql);
        query.setString(0,username);
        query.executeUpdate();
    }
}
