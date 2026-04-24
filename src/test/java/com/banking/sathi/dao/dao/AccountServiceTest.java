package com.banking.sathi.dao.dao;

import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.dto.response.AccountCreationResponseDto;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.service.AccountService;

import java.time.LocalDate;

public class AccountServiceTest {


    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        AccountCreationRequest request = new AccountCreationRequest(
                AccountType.SAVINGS,                // accountType
                LocalDate.of(2000, 5, 15),         // dob
                "Male",                            // gender
                "1234567890",                      // citizenship
                LocalDate.of(2018, 6, 20),         // citizenshipIssueDate
                "Kathmandu",                       // citizenshipDistrict
                "9841234567",                      // phone
                "Student",                         // occupation
                50000.0,                           // income
                "Bagmati",                         // province
                "Kathmandu",                       // district
                "Kathmandu Metropolitan",          // city
                5,                                 // ward
                "Baneshwor",                       // tole
                "Ram Acharya",                     // fatherName
                "Sita Acharya"                     // motherName
        );
        AccountCreationResponseDto account = accountService.createAccount(request, 1L);
        System.out.println(account);
    }
}
