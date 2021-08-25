package database_JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import entities.Account;


public class DAOAccount extends DAO{
		//edit or save the account into the database
	/**
	 * 
	 * @param account
	 * @return the 
	 * @throws Exception
	 */
		@Transactional
		public Account save(Account account) throws Exception{
			entityManager.getTransaction().begin();
			entityManager.merge(account);

			entityManager.getTransaction().commit();
			
			return account;
		}
		//delete the user and account by account id
		@Transactional
		public void remove(Integer id) {
			entityManager.getTransaction().begin();
			Account account = entityManager.find(Account.class, id);
			entityManager.remove(account);
			entityManager.getTransaction().commit();
		
		}
		public List<Account> allAccounts(EntityManager entityManager) {
			String jpql= "select a from Account a";
			TypedQuery<Account> typedQuery = entityManager.createQuery(jpql, Account.class);
			List<Account> list = typedQuery.getResultList();
			
			return list;
		}
		
		@Transactional
		public Account findAccount(Integer id) {
			Account acc = null;
			acc = entityManager.find(Account.class, id);
			
			return acc;
		}
		
}