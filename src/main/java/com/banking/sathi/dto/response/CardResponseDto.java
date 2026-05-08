package com.banking.sathi.dto.response;

import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.enums.CardType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardResponseDto {
    private Long id;
    private Long accountId;
    private String cardNumber;
    private CardType type;
    private CardStatus status;
    private Double creditLimit;
    private Double monthlyIncome;
    private LocalDate expiryDate;
    private LocalDateTime appliedDate;

}
