package io.spring.learning.repository;

import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;

public class StubRewardRepository implements RewardRepository {
    public static final String TYPE = "Stub";

    int nextConfirmationNumber = 0;

    @Override
    public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining) {
        return new RewardConfirmation(confirmationNumber(), contribution);
    }

    @Override
    public String getInfo() {
        return TYPE;
    }

    private String confirmationNumber() {
        return String.valueOf(nextConfirmationNumber++);
    }
}
