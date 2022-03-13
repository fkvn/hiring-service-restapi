package hiringProcess.model.core.dao;

import java.util.List;

import hiringProcess.model.core.Department;


public interface DepartmentDao {
	
	// get all departments
	List<Department> getDepts();

	// get a department by id
	Department getDept(Long id);

	// save a department for either insert or update
	Department saveDept(Department dept);

	// remove a department
	void remove(Long id);
}
