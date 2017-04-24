package com.fzd.dao.impl;

import com.fzd.dao.ProducerDao;
import com.fzd.model.ProducerEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by FZD on 2017/3/20.
 */
@Repository("producerDao")
public class ProducerDaoImpl extends HibernateImpl<ProducerEntity, Integer> implements ProducerDao<ProducerEntity, Integer>{
}
