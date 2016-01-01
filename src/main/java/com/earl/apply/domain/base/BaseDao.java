package com.earl.apply.domain.base;

import java.util.List;


/**
 * Data access interface for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public interface BaseDao<T> {

	void save(T t);

	void update(T t);

	void deleteById(Long id);

	/**
	 * findById 功能跟get(int )一样
	 * 
	 * @param id
	 * @return
	 */
	T get(Long id);

	List<T> findAll();
	
	void deleteAll();

	void delete(T persistentInstance);

	


	
}