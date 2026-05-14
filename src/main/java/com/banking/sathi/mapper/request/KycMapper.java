package com.banking.sathi.mapper.request;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.enums.Gender;
import com.banking.sathi.model.Kyc;

import java.util.function.Function;

public class KycMapper implements Function<AccountCreationRequest, Kyc> {
    @Override
    public Kyc apply(AccountCreationRequest accountCreationRequest) {
        return new Kyc(
                accountCreationRequest.getDob(),
                Gender.valueOf(accountCreationRequest.getGender().toUpperCase()),
                accountCreationRequest.getCitizenship(),
                accountCreationRequest.getCitizenshipIssueDate(),
                accountCreationRequest.getCitizenshipDistrict(),
                accountCreationRequest.getPhone(),
                accountCreationRequest.getOccupation(),
                accountCreationRequest.getIncome());


    }
}


