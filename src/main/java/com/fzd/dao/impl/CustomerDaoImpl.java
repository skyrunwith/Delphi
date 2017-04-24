package com.fzd.dao.impl;

import com.fzd.dao.CustomerDao;
import com.fzd.model.CustomerEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by FZD on 2017/3/20.
 */
@Repository("customerDao")
public class CustomerDaoImpl extends HibernateImpl<CustomerEntity, Integer> implements CustomerDao<CustomerEntity,Integer>{
}
