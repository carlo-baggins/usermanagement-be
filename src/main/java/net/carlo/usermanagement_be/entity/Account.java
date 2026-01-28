package net.carlo.usermanagement_be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String roles;
    private String name;
    private String surname;
    private String phone;

    public Account() {
    }


    public Account(Integer id, String email, String roles, String name, String surname, String phone) {
        this.id = id;
        this.email = email;

        this.roles = roles;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
    public Account(Integer id, String email, String password, String roles, String name, String surname, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
