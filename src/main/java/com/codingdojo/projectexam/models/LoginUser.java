package com.codingdojo.projectexam.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {

    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 8, max = 200)
    private String password;

    public LoginUser() {
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
}
