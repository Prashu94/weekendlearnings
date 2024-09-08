package rewards;

import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;
import io.spring.learning.repository.RewardRepository;

import java.util.Random;

public class StubRewardRepository implements RewardRepository {
    @Override
    public String getInfo() {
        return "INFO";
    }

    public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining) {
        return new RewardConfirmation(confirmationNumber(), contribution);
    }

    private String confirmationNumber() {
        return new Random().toString();
    }
}
