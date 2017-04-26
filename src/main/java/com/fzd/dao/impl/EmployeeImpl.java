package com.fzd.dao.impl;

import com.fzd.dao.EmployeeDao;
import com.fzd.model.EmployeeEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by SRKJ on 2017/4/24.
 */
@Repository("employeeDao")
public class EmployeeImpl extends HibernateImpl<EmployeeEntity, Integer> implements EmployeeDao<EmployeeEntity, Integer> {
}
