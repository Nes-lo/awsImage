package com.codingdojo.projectexam.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El campo first name no debe estar vacio ")
    @Size(min=5, max = 30, message = "el campo first name debe ser mayor a 5 caracteres")
    private String name;
    @NotEmpty(message = "El campo email no debe estar vacio")
    @Email(message = "valide el formato del email")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "El campo contraseña no debe estar vacio")
    @Size(min = 8, max = 200, message = "La contraseña debe tener almenos 8 caracteres")
    private String password;
    @NotEmpty(message = "El campo confirmar contraseña no debe estar vacio")
    @Transient
    private String confirmPassword;
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    @OneToMany(mappedBy = "creator",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> creatorTask;

    @OneToMany(mappedBy = "assigne",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private  List<Task> assigneTask;

    public List<Task> getCreatorTask() {
        return creatorTask;
    }

    public void setCreatorTask(List<Task> creatorTask) {
        this.creatorTask = creatorTask;
    }

    public List<Task> getAssigneTask() {
        return assigneTask;
    }

    public void setAssigneTask(List<Task> assigneTask) {
        this.assigneTask = assigneTask;
    }

    @PrePersist
    @DateTimeFormat(pattern="yyyy-MM-dd")
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    @DateTimeFormat(pattern="yyyy-MM-dd")
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
