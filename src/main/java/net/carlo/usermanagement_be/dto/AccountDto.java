package net.carlo.usermanagement_be.dto;

public class AccountDto {
    private int id;
    private String email;
    private String role;
    private String name;
    private String surname;
    private String phone;

    public AccountDto(){}

    public AccountDto( String email, int id, String role) {
        this.role = role;
        this.email = email;
        this.id = id;
    }

    public AccountDto(String name, String email, String surname, String phone) {
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.phone = phone;
    }

    public AccountDto(int id, String email, String role, String name, String surname, String phone) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
