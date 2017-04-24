package com.fzd.dao.impl;

import com.fzd.dao.CategoryDao;
import com.fzd.model.CategoryEntity;
import org.springframework.stereotype.Service;

/**
 * Created by FZD on 2017/3/16.
// */
@Service("categoryDao")
public class CategoryDaoImpl extends HibernateImpl<CategoryEntity, Integer> implements CategoryDao <CategoryEntity, Integer>{
}
