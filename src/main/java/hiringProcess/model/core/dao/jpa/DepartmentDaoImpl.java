package hiringProcess.model.core.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hiringProcess.model.core.Department;
import hiringProcess.model.core.dao.DepartmentDao;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

	// entity will mainly hold and process data to be persisted with database
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Department getDept(Long id) {

		// find a department and return it back by its id
		return entityManager.find(Department.class, id);
	}

	@Override
	public List<Department> getDepts() {

		// get all departments by custom query
		return entityManager.createQuery("from Department", Department.class).getResultList();
	}

	@Override
	@Transactional
	public Department saveDept(Department dept) {

		// save and return a department after inserting or updating
		return entityManager.merge(dept);
	}

	@Override
	@Transactional
	public void remove(Long id) {

		// get a dept from database, then remove it from database
		Department dept = entityManager.find(Department.class, id);
		entityManager.remove(dept);
	}

}
