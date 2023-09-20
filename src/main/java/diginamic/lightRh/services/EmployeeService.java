package diginamic.lightRh.services;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import diginamic.lightRh.dtos.EmployeeAbsences;
import diginamic.lightRh.entities.Absence;
import diginamic.lightRh.entities.Employee;
import diginamic.lightRh.exceptions.treatmentExceptions.SetTokenException;
import diginamic.lightRh.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    
    // Create employee
    @Transactional(rollbackOn = Exception.class)
    public void createEmployee(String firstName, String lastName, String email, boolean isAdmin, boolean isManager){
        Employee employee = new Employee();
            employee.setDepartment("");
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setActive(true);
            employee.setAdmin(isAdmin);
            employee.setManager(isManager);
            employee.setPasswordChangeToken(UUID.randomUUID());
            employee.setPassword(UUID.randomUUID().toString());
            employeeRepository.save(employee);
    }
    
    // Update employee
    public void updateEmployee(String firstName, String lastName, String email, boolean isAdmin, boolean isManager) throws Exception {
        // Find employee by email
        Employee existingEmployee = employeeRepository.findByEmail(email).orElseThrow(() -> new Exception("Employee not found"));

        // Update employee's attributs
        existingEmployee.setFirstName(firstName);
        existingEmployee.setLastName(lastName);
        existingEmployee.setEmail(email);
        existingEmployee.setAdmin(isAdmin);
        existingEmployee.setManager(isManager);

        // Save employee in bdd
        employeeRepository.save(existingEmployee);
    }
    
    // Delete employee
    public void deleteEmployee(String email) throws Exception {
        // Find employee by email
        Employee existingEmployee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Employee not found"));

        // Delete employee in bdd
        employeeRepository.delete(existingEmployee);
    }


    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Collection<Absence> getAbsences(String email){
	return getEmployeeByEmail(email).getAbsences();
    }
    
    public Integer getRemainingRTTDays(String email) {
	return getEmployeeByEmail(email).getRtt();
    }
    
    public Integer getRemainingPaidLeaveDays(String email) {
	return getEmployeeByEmail(email).getVacation();
    }
    
    public EmployeeAbsences getAbsencesDetailed(String email) {
	Employee employeeFound = getEmployeeByEmail(email);
	return new EmployeeAbsences(
		employeeFound.getAbsences(),
		employeeFound.getRtt(),
		employeeFound.getVacation(),
		email
		);
    }
    
    public void setTokenCheck (String email, UUID generatedUuid) throws SetTokenException{
	Employee employeeFound = getEmployeeByEmail(email);
	try {
	    employeeFound.setPasswordChangeToken(generatedUuid);
	    employeeFound = employeeRepository.save(employeeFound);
	}
	catch (Exception e) {
	    throw new SetTokenException("couldn't assign refresh token");
	}	
    }
    
    @Transactional(rollbackOn = Exception.class)
    public void setNewPassword(UUID token, String email, String password) {
	Employee employeFound = verifyTokenForEmail(email, token);
	if(employeFound != null) {
	    employeFound.setPassword(passwordEncoder.encode(password));
	    employeFound.setPasswordChangeToken(null);
	}
    }
    
    public Employee verifyTokenForEmail(String email, UUID token) {
	Employee employeeFound = getEmployeeByEmail(email);
	return token.equals(employeeFound.getPasswordChangeToken()) ? employeeFound : null;
    }
    
    public Employee getEmployeeByEmail(String email) {
	return this.employeeRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found for email : "+email));
    }
}
