package com.example.resonate.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserRequestDTO {

    @NotNull
    @Size(min = 1,max=100)
    private String name;

    @NotNull
    @Email
    @Size(min=1)
    private String email;


    @NotNull
    @Size(min=8)
    private String password;

    @NotNull
    @Past
    private LocalDate dob;

    public @NotNull @Size(min = 1, max = 100) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 1, max = 100) String name) {
        this.name = name;
    }

    public @NotNull @Email @Size(min = 1) String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email @Size(min = 1) String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 8) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 8) String password) {
        this.password = password;
    }

    public @NotNull @Past LocalDate getDob() {
        return dob;
    }

    public void setDob(@NotNull @Past LocalDate dob) {
        this.dob = dob;
    }
}
