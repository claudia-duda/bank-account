package database_JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public abstract class DAO {
	protected EntityManager entityManager;

	public DAO() {
		entityManager = getEntityManager();
	}
	protected static EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bank");
		return factory.createEntityManager();
	}
}
