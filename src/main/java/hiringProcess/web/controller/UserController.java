package hiringProcess.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hiringProcess.exception.BadRequest;
import hiringProcess.exception.UserNotFoundException;
import hiringProcess.model.core.User;
import hiringProcess.model.core.dao.UserDao;
import hiringProcess.util.Views;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public List<User> getUsers(User user) {
		
		if (user == null) {
			System.out.println("no user");
		}
		
		else {
			System.out.println("user: " + user.getUsername());
		}
//		
		return userDao.getUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addUser(@RequestBody User user) {
		
		if (userDao.isUniqueUsername(user.getUsername())) {
			throw new BadRequest("Username already exists!");
		}
		
		String password = user.getPassword();
		if (password == null || password.isEmpty()) {
			throw new BadRequest("Password can't be null or empty!");
		}
		
		if (user.getEmail() != null && userDao.isUniqueEmail(user.getEmail())) {
			throw new BadRequest("Email already exists!");
		}
		
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		user.setPassword(hashedPassword);
		
		user = userDao.saveUser(user);
		return user.getId();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {

		return userDao.getUser(id);
	}


	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateUser(@PathVariable Long id, @RequestBody User user) {

		if (userDao.getUser(id) == null)
			throw new UserNotFoundException();

		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(hashed);
		user.setId(id);
		userDao.saveUser(user);
	}


	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateUserFields(@PathVariable Long id, @RequestBody Map<String, Object> userFields) {

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
				default:
			}
		}
		userDao.saveUser(user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {

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
