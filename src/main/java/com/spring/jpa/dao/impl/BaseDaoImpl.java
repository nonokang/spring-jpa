package com.spring.jpa.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.spring.jpa.dao.BaseDao;
import com.spring.jpa.utils.Page;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;
    
    public abstract Class<T> getEntityClass();

    protected String getClassName(){
    	return getEntityClass().getName();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		List<T> list = new ArrayList<T>();
		Query q = entityManager.createNamedQuery(getClassName());
		list = q.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(String hql, String... str){
		List<T> list = new ArrayList<T>();
		Query q = entityManager.createQuery(hql);//hql查询
		for(int i=0; i<str.length; i++){
			q.setParameter(i, str[i]);
		}
		list = q.getResultList();
		return list;
	}

	@Override
	@Transactional
	public Object save(T t) {
		Object o = entityManager.merge(t);
		return o;
	}

	@Override
	@Transactional
	public Object update(T t) {
		Object o = entityManager.merge(t);
		return o;
	}

	@Override
	public Object findById(Integer id) {
		Object o = entityManager.find(getEntityClass(), id);
		return o;
	}

	@Override
	@Transactional
	public void delete(T t) {
		entityManager.remove(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteById(Integer id) {
		T t = (T)findById(id);
		delete(t);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> pageList(String hql, Page<T> page, String... str){
		List<T> list = new ArrayList<T>();
		Query q = entityManager.createQuery(hql);//hql查询
		if(null != str && str.length != 0){
			for(int i=0; i<str.length; i++){
				q.setParameter(i, str[i]);
			}
		}
		q.setFirstResult(page.getPageNum());
		q.setMaxResults(page.getPageSize());
		list = q.getResultList();
		int totalRow = rows(hql, str);
		
		page.setList(list);
		page.setTotalRow(totalRow);
		return page;
	}
	
	protected int rows(String hql, String... str){
		Query q = entityManager.createQuery(hql);//hql查询
		if(null != str && str.length != 0){
			for(int i=0; i<str.length; i++){
				q.setParameter(i, str[i]);
			}
		}
		return q.getResultList().size();
	}
	
}
