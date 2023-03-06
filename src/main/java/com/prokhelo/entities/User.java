package com.prokhelo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is Required")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Name is Required Field")
    private String name;

    @Column(nullable = false)
    private int age;

    

    public User(){
        super();
    }

    public User(Long id, @NotBlank(message = "Email is Required") String email,
            @NotBlank(message = "Password cannot be empty") String password,
            @NotBlank(message = "Name is Required Field") String name, int age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
}
