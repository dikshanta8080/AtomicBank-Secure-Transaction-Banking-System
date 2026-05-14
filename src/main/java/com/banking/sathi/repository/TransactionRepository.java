package com.banking.sathi.repository;

import com.banking.sathi.dto.response.TransactionViewDto;
import com.banking.sathi.enums.TransactionStatus;
import com.banking.sathi.model.Transaction;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {
    // Write operations — used by AccountService and TransactionService
    int saveTransaction(Transaction transaction, Connection con);

    // Read operations — used by PortalService
    List<TransactionViewDto> findByUserId(Long userId, LocalDate fromDate, LocalDate toDate);
    List<TransactionViewDto> findAll();
    List<TransactionViewDto> findFailed();
    long countByStatus(TransactionStatus status);
    long countByUserId(Long userId);
}
