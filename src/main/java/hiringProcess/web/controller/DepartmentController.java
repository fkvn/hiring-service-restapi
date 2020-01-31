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
	public Integer addDepartment(@RequestBody Department dept) {

		dept = deptDao.saveDept(dept);
		return dept.getId();
	}

	@GetMapping("/{deptId}")
	public Department getDepartment(@PathVariable Integer deptId) {

		return deptDao.getDept(deptId);
	}

	@PutMapping("/{deptId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateDepartment(@PathVariable Integer deptId, @RequestBody Department dept) {

		dept.setId(deptId);
		dept = deptDao.saveDept(dept);
	}

	@DeleteMapping("/{deptId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDepartment(@PathVariable Integer deptId) {

		deptDao.remove(deptId);
	}
}
