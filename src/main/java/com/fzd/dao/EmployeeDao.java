package com.fzd.dao;

import java.io.Serializable;

/**
 * Created by SRKJ on 2017/4/24.
 */
public interface EmployeeDao <T,ID extends Serializable> extends HibernateDao<T,ID>{

}
