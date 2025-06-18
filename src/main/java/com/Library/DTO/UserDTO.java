package com.Library.DTO;


import jakarta.validation.constraints.NotBlank;

public class UserDTO {


    @NotBlank
    private String role;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public @NotBlank String getRole() {
        return role;
    }

    public void setRole(@NotBlank String role) {
        this.role = role;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}
