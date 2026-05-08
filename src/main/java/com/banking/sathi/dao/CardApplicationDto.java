package com.banking.sathi.dao;

import com.banking.sathi.enums.CardType;

public class CardApplicationDto {
    private CardType type;
    private Double creditLimit;

    public CardApplicationDto(CardType type, Double creditLimit) {
        this.type = type;
        this.creditLimit = creditLimit;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
