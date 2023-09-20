package diginamic.lightRh.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import diginamic.lightRh.models.LoginParams;
import diginamic.lightRh.models.ResetMailParams;
import diginamic.lightRh.services.EmployeeService;
import diginamic.lightRh.services.LoginService;
import diginamic.lightRh.services.MailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserController {
    
    private final LoginService loginService;
    private final MailService mailService;
    private final EmployeeService employeeService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginParams loginParams) {	
	String token = loginService.login(loginParams.getEmail(), loginParams.getPassword());
	try {
	    return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(token), HttpStatus.OK);
	}catch (Exception e){
	    return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
	}
    }
    
    @GetMapping("/changepassword")
    public ResponseEntity<String> resetPassword(@Email @RequestParam String email) {
	try{
	    mailService.sendPasswordChangeMail(email);
	    return new ResponseEntity<>(new ObjectMapper().writeValueAsString("mail sent"), HttpStatus.OK);
	}catch (Exception e) {
	    return new ResponseEntity<>("", HttpStatus.BAD_REQUEST); 
	}
	
    }
    
    @PostMapping("/setnewpassword")
    public ResponseEntity<String> setNewPassword(@RequestParam UUID uuid, @Valid @RequestBody ResetMailParams resetParams) {
	employeeService.setNewPassword(uuid, resetParams.getEmail(), resetParams.getPassword());
	return new ResponseEntity<>("", HttpStatus.OK);
    }
    
    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
	return new ResponseEntity<>("", HttpStatus.OK);
    }
}
