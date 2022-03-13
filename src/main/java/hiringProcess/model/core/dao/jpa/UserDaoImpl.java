package hiringProcess.model.core.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hiringProcess.model.core.User;
import hiringProcess.model.core.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User getUser(Long id) {

		return entityManager.find(User.class, id);
	}

	@Override
	public User getUserByUsername(String username) {

		String query = "from User where lower(username) = :username";

		List<User> users = entityManager.createQuery(query, User.class)
				.setParameter("username", username.toLowerCase()).getResultList();

		return users.size() == 0 ? null : users.get(0);

	}

	@Override
	public User getUserByEmail(String email) {

		String query = "from User where lower(email) = :email";

		List<User> users = entityManager.createQuery(query, User.class)
				.setParameter("email", email.toLowerCase()).getResultList();

		return users.size() == 0 ? null : users.get(0);
	}

	@Override
	public List<User> getUsers() {

		String query = "from User order by lastName asc, firstName asc";

		return entityManager.createQuery(query, User.class).getResultList();
	}

	@Override
	public List<User> searchUsers(String queryText) {

		queryText = queryText.toLowerCase();

		String query = "from User where lower(username) = :queryText "
				+ "or lower(firstName) = :queryText or lower(lastName) = :queryText "
				+ "or lower(firstName || ' ' || lastName) = :queryText "
				+ "or lower(email) = :queryText order by firstName asc";

		return entityManager.createQuery(query, User.class).setParameter("queryText", queryText)
				.getResultList();
	}

	@Override
	@Transactional
	public User saveUser(User user) {

		return entityManager.merge(user);
	}

	@Override
	@Transactional
	public void removeUser(Long id) {

		User user = entityManager.find(User.class, id);
		entityManager.remove(user);
	}

	@Override
	public boolean isUniqueUsername(String username) {

		String query = "from User where trim(username) = :username";

		boolean isExist = entityManager.createQuery(query, User.class)
				.setParameter("username", username.trim()).getResultList().size() > 0 ? true : false;
				
		return isExist;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		String query = "from User where trim(email) = :email";

		boolean isExist = entityManager.createQuery(query, User.class)
				.setParameter("username", email.trim()).getResultList().size() > 0 ? true : false;
				
		return isExist;
	}

}
