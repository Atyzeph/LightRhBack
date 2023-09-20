package diginamic.lightRh.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import diginamic.lightRh.enums.Profile;
import diginamic.lightRh.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="employee")
public class Employee implements UserDetails{
    
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    private String email;
    private String password;
    @Column(name="token_validity")
    private UUID passwordChangeToken;
    @Column(name="is_admin")
    private boolean isAdmin;
    @Column(name="is_manager")
    private boolean isManager;
    @Column(name="is_active")
    private boolean isActive;
    private Integer vacation;
    private Integer rtt;
    @OneToMany(mappedBy = "manager")
    private Collection<Employee> employees = new HashSet<Employee>();
    @ManyToOne()
    @JoinColumn(name = "manager_id")
    private Employee manager;
    private String department;
    @OneToMany(mappedBy = "employee")
    Collection<Absence> absences;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UUID getPasswordChangeToken() {
        return passwordChangeToken;
    }
    public void setPasswordChangeToken(UUID passwordChangeToken) {
        this.passwordChangeToken = passwordChangeToken;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public Integer getVacation() {
        return vacation;
    }
    public void setVacation(Integer vacation) {
        this.vacation = vacation;
    }
    public Integer getRtt() {
        return rtt;
    }
    public void setRtt(Integer rtt) {
        this.rtt = rtt;
    }
    public Collection<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
    public Employee getManager() {
        return manager;
    }
    public void setManager(Employee manager) {
        this.manager = manager;
    }
    public Integer getId() {
        return id;
    }
    public String getDepartment() {
	return department;
    }
    public void setDepartment(String department) {
	this.department = department;
    }
    public void setAbsences(Collection<Absence> absences) {
	this.absences = absences;
    }
    public Collection<Absence> getAbsences(){
	return absences;
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	authorities.add(new SimpleGrantedAuthority(isAdmin() ? Role.ADMIN.name() : Role.USER.name()));
	authorities.add(new SimpleGrantedAuthority(isManager() ? Profile.MANAGER.name() : Profile.EMPLOYEE.name()));	
	return authorities;
    }
    @Override
    public String getUsername() {
	return email;
    }
    @Override
    public boolean isAccountNonExpired() {
	return true;
    }
    @Override
    public boolean isAccountNonLocked() {
	return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }
    @Override
    public boolean isEnabled() {
	return isActive;
    }
}
