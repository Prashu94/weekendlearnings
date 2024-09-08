package io.spring.learning.rewards;

import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;

public interface  RewardNetwork {

    public RewardConfirmation rewardAccountFor(Dining dining);

}
