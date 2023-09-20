package diginamic.lightRh.controllers;
import java.util.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import diginamic.lightRh.dtos.CreateEmployeeDto;
import diginamic.lightRh.entities.Employee;
import diginamic.lightRh.services.EmployeeService;
import diginamic.lightRh.services.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController 
@RequestMapping("/employee")
@Validated
@RequiredArgsConstructor
public class AdminController {
	
    private final EmployeeService employeeService;
    private final MailService mailService;
    
    // Create employee
    @PostMapping("admin/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeDto employeeRequest) {

        try {
            employeeService.createEmployee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail(),
                employeeRequest.isAdmin(),
                employeeRequest.isManager()
            );
            
            // Send mail for creating a new password
            mailService.sendPasswordChangeMail(employeeRequest.getEmail());           
            
            return ResponseEntity.ok("Employee created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create employee."+e);
        }
    }
    
    @PutMapping("admin/updateEmployee/{email}")
    public ResponseEntity<String> updateEmployee(@PathVariable String email, @RequestBody CreateEmployeeDto employeeRequest) {
        try {
            employeeService.updateEmployee(
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail(),
                employeeRequest.isAdmin(),
                employeeRequest.isManager()
            );

            return ResponseEntity.ok("Employee updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update employee. " + e);
        }
    }
    
    @DeleteMapping("admin/deleteEmployee/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String email) {
        try {
            employeeService.deleteEmployee(email);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete employee. " + e.getMessage());
        }
    }
    
    // Get all employees
    @GetMapping("admin/getAllEmployees")
    public ResponseEntity<Collection<Employee>> GetAllEmployees() {
        try {
            Collection<Employee> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
