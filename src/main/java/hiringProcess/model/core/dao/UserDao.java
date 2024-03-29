package hiringProcess.model.core.dao;

import java.util.List;

import hiringProcess.model.core.User;


public interface UserDao {

	List<User> getUsers();
	
	User getUser(Long id);

	User getUserByUsername(String username);

	User getUserByEmail(String email);

	List<User> searchUsers(String text);

	User saveUser(User user);

	void removeUser(Long id);
	
	boolean isUniqueUsername(String username);
	
	boolean isUniqueEmail(String email);
}
