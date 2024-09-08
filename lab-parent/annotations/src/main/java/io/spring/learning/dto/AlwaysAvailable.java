package io.spring.learning.dto;

import io.spring.learning.entity.Account;

public class AlwaysAvailable implements BenefitAvailabilityPolicy{
    public static final BenefitAvailabilityPolicy INSTANCE = new AlwaysAvailable();

    @Override
    public boolean isBenefitAvailableFor(Account account, Dining dining) {
        return true;
    }

    public String toString(){
        return "AlwaysAvailble";
    }

}
