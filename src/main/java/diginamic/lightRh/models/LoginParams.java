package diginamic.lightRh.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginParams {
    @Email(message = "login must be an email adress")
    @NotBlank(message = "login can't be empty")
    private String email;
    @NotBlank(message = "password can't be empty")
    private String password;
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
