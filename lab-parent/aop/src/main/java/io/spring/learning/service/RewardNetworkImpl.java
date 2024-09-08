package io.spring.learning.service;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.dto.AccountContribution;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.RewardConfirmation;
import io.spring.learning.entity.Account;
import io.spring.learning.entity.Restaurant;
import io.spring.learning.repository.AccountRepository;
import io.spring.learning.repository.RestaurantRepository;
import io.spring.learning.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rewardNetwork")
public class RewardNetworkImpl implements RewardNetwork{
    private AccountRepository accountRepository;

    private RestaurantRepository restaurantRepository;

    private RewardRepository rewardRepository;

    /**
     * Creates a new reward network.
     * @param accountRepository the repository for loading accounts to reward
     * @param restaurantRepository the repository for loading restaurants that determine how much to reward
     * @param rewardRepository the repository for recording a record of successful reward transactions
     */
    @Autowired
    public RewardNetworkImpl(AccountRepository accountRepository, RestaurantRepository restaurantRepository,
                             RewardRepository rewardRepository) {
        this.accountRepository = accountRepository;
        this.restaurantRepository = restaurantRepository;
        this.rewardRepository = rewardRepository;
    }

    public RewardConfirmation rewardAccountFor(Dining dining) {
        Account account = accountRepository.findByCreditCard(dining.getCreditCardNumber());
        Restaurant restaurant = restaurantRepository.findByMerchantNumber(dining.getMerchantNumber());
        MonetaryAmount amount = restaurant.calculateBenefitFor(account, dining);
        AccountContribution contribution = account.makeContribution(amount);
        accountRepository.updateBeneficiaries(account);
        return rewardRepository.confirmReward(contribution, dining);
    }
}
