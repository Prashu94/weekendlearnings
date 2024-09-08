package io.spring.learning.repository;

import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;

public interface RewardRepository {
    public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining);
}
