package database_JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entities.User;

public class DAOUser extends DAO{
	@Transactional
	/**
	 * Find an user by ID
	 * @param id
	 * @return
	 */
	public User findbyId(Integer id) {
		User user = entityManager.find(User.class, id);
		return user;
	}

	public List<User> allUsers(EntityManager entityManager) {
		String jpql= "select u from User u";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		List<User> list = typedQuery.getResultList();
		
		return list;
	}
}
