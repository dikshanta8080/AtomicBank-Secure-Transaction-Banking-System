package com.banking.sathi.repository;

import com.banking.sathi.model.Transaction;

import java.sql.Connection;

public interface TransactionRepository {
    boolean saveTransaction(Transaction transaction, Connection con);
}
