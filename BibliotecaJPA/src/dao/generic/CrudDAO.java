package dao.generic;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

public class CrudDAO <T extends AbstractEntity> implements GenericDAO<T> {

	@SuppressWarnings("rawtypes")
	private transient final Class entityClass;
	
	@SuppressWarnings("rawtypes")
	public CrudDAO() {
		entityClass = (Class) ((java.lang.reflect.ParameterizedType) this
				.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@PersistenceContext(name = "Test") // Nome do banco, passar por parametro
	protected transient EntityManager manager;
	
	@Override
	public T create(T entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException,
			TransactionRequiredException {
		manager.persist(entity);
		manager.flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T read(Serializable primaryKey) throws IllegalStateException, IllegalArgumentException {
		return (T) manager.find(entityClass, primaryKey);
	}

	@Override
	public void update(T entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {
		manager.merge(entity);
		manager.flush();		
	}

	@Override
	public void deleteO(T entity)
			throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
		manager.remove(entity);
		manager.flush();
	}

}
