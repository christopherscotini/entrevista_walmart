package com.walmart.repository.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, ID extends Serializable> {

	T insert(T entity);

	T update(T entity);

	void delete(T entity);

	List<T> findAll();

	T findById(ID primaryKey);

}
