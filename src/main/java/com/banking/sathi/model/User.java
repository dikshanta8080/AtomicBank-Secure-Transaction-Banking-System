package com.banking.sathi.model;

import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private Role role;
    private UserStatus userStatus;
}
