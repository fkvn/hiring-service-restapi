package hiringProcess.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hiringProcess.model.core.Department;
import hiringProcess.model.core.dao.DepartmentDao;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentDao deptDao;

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

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(@PathVariable Long id) {

		deptDao.remove(id);
	}
}
