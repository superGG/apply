package com.earl.apply.domain.base;

import java.util.List;

public interface BaseService<T> {

	Boolean save(T model);

	Boolean update(T t);
	

	Boolean deleteById(Long id);

	List<T> findAll();
	


	T get(Long id);
	
	
}
