package io.spring.learning.dto;

import java.io.Serializable;

public class RewardConfirmation implements Serializable {

    private String confirmationNumber;

    private AccountContribution accountContribution;

    public RewardConfirmation(String confirmationNumber, AccountContribution accountContribution) {
        this.confirmationNumber = confirmationNumber;
        this.accountContribution = accountContribution;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public AccountContribution getAccountContribution() {
        return accountContribution;
    }

    @Override
    public String toString() {
        return "RewardConfirmation{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", accountContribution=" + accountContribution +
                '}';
    }
}
