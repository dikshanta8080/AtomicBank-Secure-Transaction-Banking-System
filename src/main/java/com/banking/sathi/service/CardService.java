package com.banking.sathi.service;

import com.banking.sathi.dao.AccountDao;
import com.banking.sathi.dao.CardDao;
import com.banking.sathi.dao.KycDao;
import com.banking.sathi.dao.UserDao;
import com.banking.sathi.dto.request.CardRequestDto;
import com.banking.sathi.dto.response.CardResponseDto;
import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.exceptions.AccountDoesNotExistsException;
import com.banking.sathi.exceptions.KycDoesnotExistsException;
import com.banking.sathi.exceptions.NotEligibleException;
import com.banking.sathi.exceptions.UserDoesnotExistsException;
import com.banking.sathi.model.Account;
import com.banking.sathi.model.Card;
import com.banking.sathi.model.Kyc;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.repository.CardRepository;
import com.banking.sathi.repository.KycRepository;
import com.banking.sathi.repository.UserRepository;
import com.banking.sathi.utils.CardNumberGenerator;
import com.banking.sathi.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CardService {
    private static final Logger logger = Logger.getLogger(CardService.class.getName());
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final KycRepository kycRepository;

    public CardService() {
        this.cardRepository = new CardDao();
        this.accountRepository = new AccountDao();
        this.userRepository = new UserDao();
        this.kycRepository = new KycDao();
    }

    private boolean checkEligibilityForRequestedLimit(Double annualIncome, Double requestedCreditLimit) {
        if (annualIncome == null || annualIncome <= 0 || requestedCreditLimit == null || requestedCreditLimit <= 0) {
            return false;
        }
        return requestedCreditLimit <= ((annualIncome) / 12) * 4;
    }

    public List<CardResponseDto> getPendingApprovalCards() {
        List<CardResponseDto> pendingApprovalCards = cardRepository.getPendingApprovalCards();

        return pendingApprovalCards.stream().map(pendingApprovalCard -> {
            Double annualIncome = kycRepository.findKycIncomeByAccount(pendingApprovalCard.getAccountId());
            pendingApprovalCard.setMonthlyIncome(annualIncome != null ? annualIncome / 12 : 0);
            return pendingApprovalCard;
        }).collect(Collectors.toList());
    }

    public boolean saveCard(CardRequestDto cardRequestDto, Long userId) {
        Connection con = null;

        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);

            if (userId == null || userId <= 0) {
                throw new UserDoesnotExistsException("User does not exists");
            }

            if (cardRequestDto.getCreditLimit() == null || cardRequestDto.getCreditLimit() <= 0) {
                throw new IllegalArgumentException("Invalid requested credit limit");
            }

            Kyc kyc = kycRepository.findByUserId(userId, con)
                    .orElseThrow(() -> new KycDoesnotExistsException("Kyc does not exists"));

            Account account = accountRepository.findByUserId(userId, con)
                    .orElseThrow(() -> new AccountDoesNotExistsException("Account does not exists"));

            String cardNumber = CardNumberGenerator.generateCardNumber();

            String cvv = (cardNumber != null && cardNumber.length() >= 3)
                    ? cardNumber.substring(cardNumber.length() - 3)
                    : "000";

            if (!checkEligibilityForRequestedLimit(kyc.getIncome(), cardRequestDto.getCreditLimit())) {
                throw new NotEligibleException("You are not eligible for the requested credit limit");
            }

            Card card = new Card();
            card.setAccountId(account.getId());
            card.setCardNumber(cardNumber);
            card.setCvv(cvv);
            card.setType(cardRequestDto.getType());
            card.setStatus(CardStatus.PENDING);
            card.setCreditLimit(cardRequestDto.getCreditLimit());
            card.setExpiryDate(LocalDate.now().plusYears(5));

            int rowsInserted = cardRepository.saveCard(card, con);
            con.commit();

            return rowsInserted > 0;

        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Failed to roll back the transaction", ex);
                }
            }
            logger.log(Level.SEVERE, "Error while saving card", e);
            throw new RuntimeException(e);

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Failed to close the connection", e);
                }
            }
        }
    }

    Optional<CardResponseDto> findPendingCard(Long cardId) {
        CardResponseDto pendingCard = cardRepository.findPendingCard(cardId)
                .orElseThrow(() -> new RuntimeException("card does not exists"));

        Double annualIncome = kycRepository.findKycIncomeByAccount(pendingCard.getAccountId());
        pendingCard.setMonthlyIncome(annualIncome != null ? annualIncome / 12 : 0);

        return Optional.of(pendingCard);
    }
}