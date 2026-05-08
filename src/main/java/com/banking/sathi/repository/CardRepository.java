package com.banking.sathi.repository;

import com.banking.sathi.dto.response.CardResponseDto;
import com.banking.sathi.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    int saveCard(Card card);

    int deleteCard(Long cardId);

    Optional<Card> findById(Long cardId);

    List<Card> findAllCards();

    List<CardResponseDto> getPendingApprovalCards();

    Optional<CardResponseDto> findPendingCard(Long cardId);

    int findNumberOfPendingApprovals();

    int findTotalNumberOfCards();

    List<Card> findCardByAccount(Long accountId);

    boolean verifyCard(Long cardId);

    boolean rejectCard(Long cardId);
}
