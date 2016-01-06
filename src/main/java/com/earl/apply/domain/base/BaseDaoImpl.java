package com.earl.apply.domain.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.earl.apply.helper.HibernateHelper;

/**
 * 这里用到工具类 HibernateHelper
 * 
 * @author 黄祥谦
 * @time 2015/7/16
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	@SuppressWarnings("rawtypes")
	protected Class clazz; // 用来存储BaseDaoImpl泛型T的实际类型

	Session session;

	public Session getSession() {
		return HibernateHelper.getSessionFactory().getCurrentSession();
	}



	@SuppressWarnings("rawtypes")
	public BaseDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type genType = parameterizedType.getActualTypeArguments()[0];
		System.out.println(genType + "-----baseDaoImpl");
		this.clazz = (Class) genType;
	}
	

	// 插入对象
	@Override
	/**
	 * @author Administrator
	 * 
	 * @Param  T   object
	 * @Result void
	 */
	public void save(T t) {
		System.out.println("进入save方法");
		try {
			getSession().beginTransaction();
			getSession().save(t);
			System.out.println("成功添加纪录");
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			getSession().getTransaction().commit();
		}
		System.out.println("退出save方法");
	}

	// 更新对象
	@Override
	public void update(T t) {
		System.out.println("进入update方法");
		try {
			getSession().beginTransaction();
			getSession().update(t);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} 
		System.out.println("退出update方法");
	}

	// 根据ID删除对象
	@Override
	public void deleteById(Long id) {
		System.out.println("进入dele方法");
		try {
//			getSession().beginTransaction();
			delete(get(id));
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			getSession().getTransaction().commit();
		}
		System.out.println("退出dele方法");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(Long id) {
		getSession().beginTransaction();
		T object = (T) getSession().get(clazz, id);
		getSession().getTransaction().commit();
		return object;
	}

	// 查找该表中的所有记录，
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		System.out.println("进入findAll方法");
		List<T> list = new ArrayList<T>();
		try {
			getSession().beginTransaction();
			String hql = "from " + clazz.getSimpleName();
			list = getSession().createQuery(hql).list();
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		System.out.println("退出findAll方法");
		return list;
	}

	// 删除所有对象
	@Override
	public void deleteAll() {
		getSession().beginTransaction();
		String hql = "delete from " + clazz.getName();
		getSession().createQuery(hql).executeUpdate();
		getSession().getTransaction().commit();
	}

	/**
	 * 通过对象来进行删除,软删除对象
	 */
	@Override
	public void delete(T persistentInstance) {
		getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getSession().getTransaction().commit();
		}
	}


}