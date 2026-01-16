package net.carlo.be.dto;

public class UserDto {
    private final int id;
    private final String email;
    private final String name;
    private final String surname;
    private final String role;

    public UserDto(String email, int id, String name, String surname, String role) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }
}
