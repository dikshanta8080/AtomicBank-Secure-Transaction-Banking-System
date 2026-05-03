package com.banking.sathi.model;

import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private UserStatus userStatus;
    private LocalDateTime created;
    private LocalDateTime updated;

    public User(String name, String email, String password, Role role, UserStatus userStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
    }

    public User() {
    }

    public User(Long id, String name, String email, String password, Role role, UserStatus userStatus, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
        this.created = created;
        this.updated = updated;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, Role role, UserStatus userStatus, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.userStatus = userStatus;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userStatus=" + userStatus +
                ", created=" + created +
                ", updated=" + updated +
                '}';
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}