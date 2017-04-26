package com.fzd.dao.impl;

import com.fzd.dao.HibernateDao;
import com.fzd.dao.PageResults;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by FZD on 2017/3/16.
 */
@Repository("hibernateImpl")
@Transactional
public class HibernateImpl<T, ID extends Serializable> implements HibernateDao<T,ID>{

    @Autowired
    private SessionFactory sessionFactory;
    protected Class<T> entityClass;

    public HibernateImpl() {

    }

    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    /**
     * <???????>
     * <???????????>
     * @param t ??????
     *
     */
    @Override
    public Integer save(T t) {
       return (Integer) this.getSession().save(t);
    }

    /**
     * <?????????????>
     * @param t ???
     */
    @Override

    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate(t);
    }

    /**
     * <load>
     * <????????load????>
     * @param id ????id
     * @return ????????????
     */
    @Override
    public T load(ID id) {
        T load = (T) this.getSession().load(getEntityClass(), id);
        return load;
    }

    /**
     * <get>
     * <?????get????>
     * @param id ????id
     * @return ????????????
     */
    @Override
    public T get(ID id) {
        T load = (T) this.getSession().get(getEntityClass(), id);
        return load;
    }

    /**
     * <contains>
     * @param t ???
     * @return ??????
     */
    @Override
    public boolean contains(T t) {
        return this.getSession().contains(t);
    }

    /**
     * <delete>
     * <??????е?t????>
     * @param t ???
     */
    @Override
    public void delete(T t) {
        this.getSession().delete(t);
    }

    /**
     * <????ID???????>
     * @param Id ???id
     * @return ?????????
     */
    @Override
    public boolean deleteById(ID Id) {
        T t = get(Id);
        if(t == null){
            return false;
        }
        delete(t);
        return true;
    }

    /**
     * <???????>
     * @param entities ????Collection????
     */
    @Override
    public void deleteAll(Collection<T> entities) {
        for(Object entity : entities) {
            this.getSession().delete(entity);
        }
    }

    /**
     * <???Hql???>
     * @param hqlString hql
     * @param values ????????????
     */
    @Override
    public void queryHql(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }

    /**
     * <???Sql???>
     * @param sqlString sql
     * @param values ????????????
     */
    @Override
    public void querySql(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }

    /**
     * <????HQL??????Ψ????>
     * @param hqlString HQL???
     * @param values ??????????Object????
     * @return ??????
     */
    @Override
    public T getByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    /**
     * <????SQL??????Ψ????>
     * @param sqlString SQL???
     * @param values ??????????Object????
     * @return ??????
     */
    @Override
    public T getBySQL(String sqlString, Object... values) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    /**
     * <????HQL???????????list>
     * @param hqlString HQL???
     * @param values ??????????Object????
     * @return ??????????List????
     */
    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        Query query = this.getSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    /**
     * <????SQL???????????list>
     * @param sqlString HQL???
     * @param values ??????????Object????
     * @return ??????????List????
     */
    @Override
    public List<T> getListBySQL(String sqlString, Object... values ) {
        Query query = this.getSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

   /* *//**
     * ??sql?????List
     * @param sql
     * @param map
     * @param values
     * @return List
     *//*
    @Override
    public List findListBySql(final String sql, final RowMapper map, final Object... values) {
        final List list = new ArrayList();
        // ???JDBC??????????????
        Work jdbcWork = new Work()
        {
            public void execute(Connection connection)
                    throws SQLException
            {

                PreparedStatement ps = null;
                ResultSet rs = null;
                try
                {
                    ps = connection.prepareStatement(sql);
                    for (int i = 0; i < values.length; i++)
                    {
                        setParameter(ps, i, values[i]);

                    }

                    rs = ps.executeQuery();
                    int index = 0;
                    while (rs.next())
                    {
                        Object obj = map.mapRow(rs, index++);
                        list.add(obj);

                    }
                }
                finally
                {
                    if (rs != null)
                    {
                        rs.close();

                    }
                    if (ps != null)
                    {
                        ps.close();
                    }
                }
            }
        };
        this.getSession().doWork(jdbcWork);
        return list;
    }*/

    /**
     * <refresh>
     * @param t ???
     */
    @Override
    public void refresh(T t) {
        this.getSession().refresh(t);
    }

    /**
     * <update>
     * @param t ???
     */
    @Override
    public void update(T t) {
        this.getSession().update(t);
    }

    /**
     * <????HQL????????>
     * @param hql HQL???
     * @param values ??????????Object????
     * @return ???????
     */
    @Override
    public Long countByHql(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return (Long) query.uniqueResult();
    }

    /**
     * <HQL??????>
     * @param hql HQL???
     * @param countHql ????????????HQL???
     * @param pageNo ????
     * @param pageSize ????????
     * @param values ????Object???????
     * @return PageResults??????????????????????????????????List????
     */
    @Override
    public PageResults<T> findPageByFetchedHql(String hql, String countHql,
                                               int pageNo, int pageSize, Object... values) {
        PageResults<T> retValue = new PageResults<T>();
        Query query = this.getSession().createQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        int currentPage = pageNo > 1 ? pageNo : 1;
        retValue.setCurrentPage(currentPage);
        retValue.setPageSize(pageSize);
        if (countHql == null)
        {
            ScrollableResults results = query.scroll();
            results.last();
            retValue.setTotalCount(results.getRowNumber() + 1);// ??????????
        }
        else
        {
            Long count = countByHql(countHql, values);
            retValue.setTotalCount(count.intValue());
        }
        retValue.resetPageNo();
        List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
        if (itemList == null)
        {
            itemList = new ArrayList<T>();
        }
        retValue.setResults(itemList);

        return retValue;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @return session
     */
    public Session getSession() {
        //?????????????????CurrentSession
        return sessionFactory.getCurrentSession();
    }

    /**
     *
     * ????????????????
     *
     * @param ps
     * @param pos ??λ??????????0???
     * @param data
     * @throws SQLException
     * @see [????#????????#???]
     */
    private void setParameter(PreparedStatement ps, int pos, Object data)
            throws SQLException
    {
        if (data == null)
        {
            ps.setNull(pos + 1, Types.VARCHAR);
            return;
        }
        Class dataCls = data.getClass();
        if (String.class.equals(dataCls))
        {
            ps.setString(pos + 1, (String)data);
        }
        else if (boolean.class.equals(dataCls))
        {
            ps.setBoolean(pos + 1, ((Boolean)data));
        }
        else if (int.class.equals(dataCls))
        {
            ps.setInt(pos + 1, (Integer)data);
        }
        else if (double.class.equals(dataCls))
        {
            ps.setDouble(pos + 1, (Double)data);
        }
        else if (Date.class.equals(dataCls))
        {
            Date val = (Date)data;
            ps.setTimestamp(pos + 1, new Timestamp(val.getTime()));
        }
        else if (BigDecimal.class.equals(dataCls))
        {
            ps.setBigDecimal(pos + 1, (BigDecimal)data);
        }
        else
        {
            // δ?????
            ps.setObject(pos + 1, data);
        }

    }

    @Override
    public void merge(T t) {
        getSession().merge(t);
    }

    @Override
    public List<Object[]> findListByhql(String hql, Object... values) {
        Query query = getSession().createQuery(hql);
        if(values != null || values.length != 0){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }

        return query.list();
    }
}
