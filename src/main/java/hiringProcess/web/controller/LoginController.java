package hiringProcess.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hiringProcess.exception.BadRequest;
import hiringProcess.jwt.JWT;
import hiringProcess.model.core.User;
import hiringProcess.model.core.dao.UserDao;
import com.google.common.base.Strings;


@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private JWT jwt;

	@PostMapping
	public String login(@RequestBody User user) {
		
		User dbUser = userDao.getUserByUsername(user.getUsername());
		
		if (user == null || dbUser == null) {
			throw new BadRequest(
					"User Not Found. Please make sure your username and password are correct");
		}

		if (Strings.isNullOrEmpty(user.getUsername()) || Strings.isNullOrEmpty(user.getPassword())) {
			throw new BadRequest(
					"Invalid username or password. Username and password must be NOT NULL and NOT EMPTY");
		}

		String dbUserHashedPwd =  dbUser.getPassword();
		if (!BCrypt.checkpw(user.getPassword(), dbUserHashedPwd)) {
			throw new BadRequest("Your password is incorrect");
		}

		Map<String, Object> claims = new HashMap<>();
		claims.put("user", user);
		return jwt.generateJWT(claims);
	}
}
