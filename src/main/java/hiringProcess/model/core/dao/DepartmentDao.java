package hiringProcess.model.core.dao;

import java.util.List;

import hiringProcess.model.core.Department;


public interface DepartmentDao {

	// get a department by id
	Department getDept(Long id);

	// get all departments
	List<Department> getDepts();

	// save a department for either insert or update
	Department saveDept(Department dept);

	// remove a department
	void remove(Long id);
}
