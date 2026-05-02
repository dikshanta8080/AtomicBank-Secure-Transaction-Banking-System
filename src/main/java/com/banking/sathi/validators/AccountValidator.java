package com.banking.sathi.validators;

import com.banking.sathi.dto.request.AccountCreationRequest;

public class AccountValidator {
    public static void validateAccountCredentials(AccountCreationRequest request) {
        if (request.getAccountType() == null) throw new IllegalArgumentException("Please provide valid Account type");
        if (request.getDob() == null) throw new IllegalArgumentException("Please provide valid date of birth");
        if (request.getGender() == null) throw new IllegalArgumentException("Please provide valid Gender");
        if (request.getCitizenship().isBlank()) {
            throw new IllegalArgumentException("Please provide valid citizenship number");
        }
        if (request.getCitizenshipIssueDate() == null) {

            throw new IllegalArgumentException("Please provide valid issue date");
        }
        if (request.getCitizenshipDistrict().isBlank()) {
            throw new IllegalArgumentException("Please provide valid issued district");
        }
        if (request.getPhone().isBlank()) throw new IllegalArgumentException("Please provide valid phone number");
        if (request.getOccupation().isBlank()) throw new IllegalArgumentException("Please provide valid occupation");
        if (request.getIncome() == null) throw new IllegalArgumentException("Please provide valid income");
        if (request.getProvince().isBlank()) throw new IllegalArgumentException("Please provide valid province");
        if (request.getDistrict().isBlank()) throw new IllegalArgumentException("Please provide valid district");
        if (request.getCity().isBlank()) throw new IllegalArgumentException("Please provide valid city");
        if (request.getWard() == null) throw new IllegalArgumentException("Please provide valid ward number");
        if (request.getTole().isBlank()) throw new IllegalArgumentException("Please provide valid tole");
        if (request.getFatherName().isBlank()) throw new IllegalArgumentException("Please provide valid father's name");
        if (request.getMotherName().isBlank()) throw new IllegalArgumentException("Please provide valid mother's name");


    }
}
