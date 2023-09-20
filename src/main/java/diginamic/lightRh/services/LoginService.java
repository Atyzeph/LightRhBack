package diginamic.lightRh.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import diginamic.lightRh.entities.Employee;
import diginamic.lightRh.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    
    private final JwtService jwtService;
    private final EmployeeService employeeService;

    public String login (String email, String password) throws UsernameNotFoundException {
	Employee employee = employeeService.getEmployeeByEmail(email);
	if(employee!=null && BCrypt.checkpw(password, employee.getPassword())) {
	    Map<String, Object> claims = new HashMap<String, Object>();
	    claims.put("firstName", employee.getFirstName());
	    claims.put("lastName", employee.getLastName());
	    claims.put("authorities", employee.getAuthorities());
	    return jwtService.generateToken(employee, claims);
	}
	throw new UsernameNotFoundException("No match for this email and password");
    }
}
