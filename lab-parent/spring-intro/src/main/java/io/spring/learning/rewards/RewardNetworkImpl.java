package io.spring.learning.rewards;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;
import io.spring.learning.entity.Account;
import io.spring.learning.entity.Restaurant;
import io.spring.learning.repository.AccountRepository;
import io.spring.learning.repository.RestaurantRepository;
import io.spring.learning.repository.RewardRepository;

public class RewardNetworkImpl implements RewardNetwork{

    private AccountRepository accountRepository;

    private RestaurantRepository restaurantRepository;

    private RewardRepository rewardRepository;

    public RewardNetworkImpl(AccountRepository accountRepository,
                             RestaurantRepository restaurantRepository,
                             RewardRepository rewardRepository){
        this.accountRepository = accountRepository;
        this.restaurantRepository = restaurantRepository;
        this.rewardRepository = rewardRepository;
    }

    @Override
    public RewardConfirmation rewardAccountFor(Dining dining) {
        Account account = accountRepository.findByCreditCard(dining.getCreditCardNumber());
        Restaurant restaurant =
                restaurantRepository.findByMerchantNumber(dining.getMerchantNumber());
        MonetaryAmount amount = restaurant.calculateBenefitFor(account, dining);
        AccountContribution contribution = account.makeContribution(amount);
        return rewardRepository.confirmReward(contribution, dining);
    }
}
