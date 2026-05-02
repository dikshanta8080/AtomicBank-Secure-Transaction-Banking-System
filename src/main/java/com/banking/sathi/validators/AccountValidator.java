package com.banking.sathi.validators;

import com.banking.sathi.dto.request.AccountCreationRequest;

public class AccountValidator {
    public static void validateAccountCredentials(AccountCreationRequest request) {
        if (request.getAccountType() == null) throw new IllegalArgumentException("Please provide valid Account type");
        if (request.getDob() == null) throw new IllegalArgumentException("Please provide valid date of birth");
        if (request.getGender() == null) throw new IllegalArgumentException("Please provide valid Gender");
        if (request.getCitizenship().isBlank())
            throw new IllegalArgumentException("Please provide valid citizenship number");
        if (request.getCitizenshipIssueDate() == null)
            throw new IllegalArgumentException("Please provide valid issue date");
        if (request.getCitizenshipDistrict().isBlank())
            throw new IllegalArgumentException("Please provide valid issued district");


    }
}
