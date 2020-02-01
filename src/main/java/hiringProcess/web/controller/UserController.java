package hiringProcess.web.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hiringProcess.exception.UserNotFoundException;
import hiringProcess.model.core.User;
import hiringProcess.model.core.dao.UserDao;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<User> getUsers() {

		return userDao.getUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer addUser(@RequestBody User user) {

		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hashed);
		user = userDao.saveUser(user);
		return user.getId();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Integer id) {

		return userDao.getUser(id);
	}


	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateUser(@PathVariable Integer id, @RequestBody User user) {

		if (userDao.getUser(id) == null)
			throw new UserNotFoundException();

		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hashed);
		user.setId(id);
		userDao.saveUser(user);
	}


	@SuppressWarnings("unchecked")
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateUserFields(@PathVariable Integer id,
			@RequestBody Map<String, Object> userFields) {

		if (userDao.getUser(id) == null)
			throw new UserNotFoundException();

		User user = userDao.getUser(id);

		for (String key : userFields.keySet()) {
			switch (key) {
				case "firstName":
					user.setFirstName((String) userFields.get(key));
					break;
				case "lastName":
					user.setLastName((String) userFields.get(key));
					break;
				case "middleName":
					user.setMiddleName((String) userFields.get(key));
					break;
				case "passowrd":
					String password = (String) userFields.get(key);
					String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
					user.setPassword(hashed);
					break;
				case "username":
					user.setUsername((String) userFields.get(key));
					break;
				case "phone":
					user.setPhone((String) userFields.get(key));
					break;
				case "enabled":
					if ((Integer) userFields.get(key) == 1)
						user.setEnabled(true);
					else
						user.setEnabled(false);
					break;
				case "roles":
					user.setRoles((Set<String>) userFields.get(key));
					break;
				default:
			}
		}
		userDao.saveUser(user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer id) {

		userDao.removeUser(id);
	}

	@GetMapping("/search")
	public List<User> search(@RequestParam(required = false) String queryText) {
		
		List<User> users = null;
		if (StringUtils.hasText(queryText)) {
			users = userDao.searchUsers(queryText);
		}
		return users;
	}
}
