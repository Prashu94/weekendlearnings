package io.spring.learning.dto;

import io.spring.learning.entity.Account;

public interface BenefitAvailabilityPolicy {

    public boolean isBenefitAvailableFor(Account account, Dining dining);
}
