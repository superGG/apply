package com.earl.apply.domain.base;

import java.util.List;

import com.earl.apply.domain.user.UserDao;

/**
 * @author Administrator
 * 
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	
	@SuppressWarnings("rawtypes")
	protected BaseDao baseDao;
	
	protected UserDao userDao;
	
	
	public BaseServiceImpl() {

	}

	@SuppressWarnings("unchecked")
	public Boolean save(T model) {
		try {
			baseDao.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Boolean update(T t) {
		try {
			baseDao.update(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deleteById(Long id) {
		try {
			baseDao.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		return (T) baseDao.get(id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return baseDao.findAll();
	}

}
