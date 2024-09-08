package io.spring.learning.repository;

import io.spring.learning.common.money.Percentage;
import io.spring.learning.dto.BenefitAvailabilityPolicy;
import io.spring.learning.dto.Dining;
import io.spring.learning.entity.Account;
import io.spring.learning.entity.Restaurant;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.util.HashMap;
import java.util.Map;

public class StubRestaurantRepository implements RestaurantRepository{
    public static final String TYPE = "Stub";

    private Map<String, Restaurant> restaurantsByMerchantNumber = new HashMap<String, Restaurant>();

    public StubRestaurantRepository() {
        Restaurant restaurant = new Restaurant("1234567890", "Apple Bees");
        restaurant.setBenefitPercentage(Percentage.valueOf("8%"));
        restaurant.setBenefitAvailabilityPolicy(new AlwaysReturnsTrue());
        restaurantsByMerchantNumber.put(restaurant.getNumber(), restaurant);
    }

    @Override
    public String getInfo() {
        return TYPE;
    }

    @Override
    public Restaurant findByMerchantNumber(String merchantNumber) {
        Restaurant restaurant = (Restaurant) restaurantsByMerchantNumber.get(merchantNumber);
        if (restaurant == null) {
            throw new ObjectRetrievalFailureException(Restaurant.class, merchantNumber);
        }
        return restaurant;
    }

    @Override
    public Long getRestaurantCount() {
        return 1L;
    }

    /**
     * A simple "dummy" benefit availability policy that always returns true. Only useful for testing--a real
     * availability policy might consider many factors such as the day of week of the dining, or the account's reward
     * history for the current month.
     */
    private static class AlwaysReturnsTrue implements BenefitAvailabilityPolicy {
        public boolean isBenefitAvailableFor(Account account, Dining dining) {
            return true;
        }
    }
}
