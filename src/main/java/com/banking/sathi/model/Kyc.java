package com.banking.sathi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Kyc {
    private Long id;
    private Long userId;

    private LocalDate dob;
    private String gender;

    private String citizenship;
    private LocalDate issue;
    private String district;

    private String phone;
    private String occupation;
    private Double income;

    private String status;
    private String reason;

    private LocalDateTime created;
    private LocalDateTime updated;
}