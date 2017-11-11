package dao.generic;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

public interface GenericDAO<T> {
	T create(T entity)
			throws EntityExistsException, IllegalStateException, IllegalArgumentException, TransactionRequiredException;

	T read(Serializable primaryKey) throws IllegalStateException, IllegalArgumentException;

	void update(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException;

	void deleteO(T entity)
			throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException;
}
