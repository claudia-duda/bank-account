package database.JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entities.User;

public class UserDAO extends DAO{
	@Transactional
	
	public User findbyId(Integer id) {
		User user = entityManager.find(User.class, id);
		return user;
	}
	public User findByCPF(String CPF) {
		String jpql = "select u from User u where u.CPF = : userCPF";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		typedQuery.setParameter("userCPF", CPF);
		User user = typedQuery.getSingleResult();
		return user;
	}

	public List<User> allUsers(EntityManager entityManager) {
		String jpql= "select new entities.User(fullname,CPF,birthday,id)"
				+ " from User";
		TypedQuery<User> typedQuery = entityManager.createQuery(jpql, User.class);
		List<User> list = typedQuery.getResultList();
		
		return list;
	}
}
