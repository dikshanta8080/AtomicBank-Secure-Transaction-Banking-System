package com.banking.sathi.repository;

import com.banking.sathi.model.Card;

public interface CardRepository {
    int saveCard(Card card);

    int deleteCard(Long cardId);

    Card findById(Long cardId);
}
