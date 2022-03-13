package hiringProcess.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import hiringProcess.model.core.Department;
import hiringProcess.model.core.User;
import hiringProcess.model.core.dao.DepartmentDao;
import hiringProcess.model.core.dao.UserDao;
import hiringProcess.util.Views;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentDao deptDao;
	
	@Autowired
	private UserDao userDao;

	@GetMapping
	public List<Department> getDepartments() {

		List<Department> dept = deptDao.getDepts();
		return dept;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long addDepartment(@RequestBody Department dept) {

		dept = deptDao.saveDept(dept);
		return dept.getId();
	}

	@GetMapping("/{id}")
	public Department getDepartment(@PathVariable Long id) {

		return deptDao.getDept(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateDepartment(@PathVariable Long id, @RequestBody Department dept) {

		dept.setId(id);
		dept = deptDao.saveDept(dept);
	}
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateDeptFields(@PathVariable Long id, @RequestBody Map<String, Object> deptFields)
			throws Exception {

		if (deptDao.getDept(id) == null)
			throw new Exception("Not Found Department");

		Department dept = deptDao.getDept(id);

		for (String key : deptFields.keySet()) {
			switch (key) {
				case "name":
					dept.setName((String) deptFields.get(key));
					break;
				case "chair":
					Long uId = Long.valueOf((Integer) deptFields.get(key));
					dept.setChair(userDao.getUser(uId));
					break;
				default:
			}
		}
		deptDao.saveDept(dept);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(@PathVariable Long id) {

		deptDao.remove(id);
	}

// deparment users
	
	@GetMapping("/{id}/staff")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@JsonView(Views.Public.class)
	public List<User> getDeptUsers(@PathVariable Long id) {
		Department dept = deptDao.getDept(id);
		return dept.getStaff();
//		return (long) -1;
	}
	
	@PostMapping("/{id}/staff")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addUserDept(@PathVariable Long id, @RequestBody Map<String, Object> deptFields) {
		
		Department dept = deptDao.getDept(id);
		
		@SuppressWarnings("unchecked")
		List<Integer> staffIds = (List<Integer>) deptFields.get("staff");
		
		for (int i = 0; i < staffIds.size(); i++) {
			User user = userDao.getUser(Long.valueOf(staffIds.get(i)));
			dept.addStaff(user);
		}
		
		dept = deptDao.saveDept(dept);
		
		return dept.getId();
	}
	
	@DeleteMapping("/{id}/users/{uId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Long removeUserDept(@PathVariable Long id, @PathVariable Long uId) {
		Department dept = deptDao.getDept(id);
		User user = userDao.getUser(uId);
		
		user.setDept(null);
		userDao.saveUser(user);
		
		dept.getStaff().remove(user);
		dept = deptDao.saveDept(dept);
		
		return dept.getId();
	}
	

}
