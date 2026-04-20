package com.banking.sathi.mapper.request;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.model.Family;

import java.util.function.Function;

public class FamilyMapper implements Function<AccountCreationRequest, Family> {
    @Override
    public Family apply(AccountCreationRequest request) {
        return new Family(
                request.getFatherName(),
                request.getMotherName()
        );
    }
}
