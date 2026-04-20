package com.banking.sathi.mapper.request;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.model.Address;

import java.util.function.Function;

public class AddressMapper implements Function<AccountCreationRequest, Address> {
    @Override
    public Address apply(AccountCreationRequest request) {
        return new Address(
                request.getProvince(),
                request.getDistrict(),
                request.getCity(),
                request.getWard(),
                request.getTole()
        );
    }
}
