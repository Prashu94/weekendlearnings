package io.spring.learning.dto;

import io.spring.learning.entity.Account;

public class NeverAvailable implements BenefitAvailabilityPolicy{

    public static final BenefitAvailabilityPolicy INSTANCE = new NeverAvailable();

    @Override
    public boolean isBenefitAvailableFor(Account account, Dining dining) {
        return false;
    }

    public String toString(){
        return "neverAvailable";
    }
}
