package com.xyz.repos.common;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDAO {
	public <T> T update(final T entity) ;

   <T>  Boolean deleteEntity(final T entity);

   <T> T saveEntity(final T entity);

}
