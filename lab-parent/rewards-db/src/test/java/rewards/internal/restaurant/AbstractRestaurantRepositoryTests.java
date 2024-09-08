package rewards.internal.restaurant;

import io.spring.learning.common.money.Percentage;
import io.spring.learning.dto.AlwaysAvailable;
import io.spring.learning.entity.Restaurant;
import io.spring.learning.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractRestaurantRepositoryTests {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Test
    public abstract void testProfile();

    @Test
    @Transactional
    public final void findRestaurantByMerchantNumber() {
        Restaurant restaurant = restaurantRepository
                .findByMerchantNumber("1234567890");
        assertNotNull(restaurant, "the restaurant should never be null");
        assertEquals("1234567890",
                restaurant.getNumber(), "the merchant number is wrong");
        assertEquals("AppleBees", restaurant.getName(), "the name is wrong");
        assertEquals(
                Percentage.valueOf("8%"), restaurant.getBenefitPercentage(), "the benefitPercentage is wrong");
        assertEquals(
                AlwaysAvailable.INSTANCE,
                restaurant.getBenefitAvailabilityPolicy(), "the benefit availability policy is wrong");
    }

}
