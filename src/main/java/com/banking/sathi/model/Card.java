package com.banking.sathi.model;

import com.banking.sathi.enums.CardType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Card {
    private Long id;
    private Long accountId;
    private String cardNumber;
    private CardType type;
    private CardStatus status;
    private Double creditLimit;
    private LocalDate expiryDate;
    private String cvv;
    private String rejectionReason;
    private LocalDateTime created;
    private LocalDateTime updated;
}
