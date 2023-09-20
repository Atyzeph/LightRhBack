package diginamic.lightRh.dtos;

public class CreateEmployeeDto {
    String firstName;
    String lastName;
    String email;
    boolean isAdmin;
    boolean isManager;
    
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
}
