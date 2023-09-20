package diginamic.lightRh.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import diginamic.lightRh.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
	Optional<Employee> findByEmail(String email);
	
//	static Employee findEmployeeByEmail(String email) {
//		Employee employee = findByEmail(email);
//		return employee;
//	}

}
