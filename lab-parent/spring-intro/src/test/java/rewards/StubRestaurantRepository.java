package rewards;

import io.spring.learning.common.money.Percentage;
import io.spring.learning.entity.Restaurant;
import io.spring.learning.repository.RestaurantRepository;

import java.util.HashMap;
import java.util.Map;

public class StubRestaurantRepository implements RestaurantRepository {

    private Map<String, Restaurant> restaurantsByMerchantNumber = new HashMap<String, Restaurant>();

    public StubRestaurantRepository() {
        Restaurant restaurant = new Restaurant("1234567890", "Apple Bees");
        restaurant.setBenefitPercentage(Percentage.valueOf("8%"));
        restaurantsByMerchantNumber.put(restaurant.getNumber(), restaurant);
    }

    public Restaurant findByMerchantNumber(String merchantNumber) {
        Restaurant restaurant = (Restaurant) restaurantsByMerchantNumber.get(merchantNumber);
        if (restaurant == null) {
            throw new RuntimeException("no restaurant has been found for merchant number " + merchantNumber);
        }
        return restaurant;
    }
    @Override
    public String getInfo() {
        return "INFO";
    }
    @Override
    public Long getRestaurantCount() {
        return 0L;
    }
}
