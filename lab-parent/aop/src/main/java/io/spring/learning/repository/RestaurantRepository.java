package io.spring.learning.repository;

import io.spring.learning.entity.Restaurant;

public interface RestaurantRepository {

    public Restaurant findByMerchantNumber(String merchantNumber);
}
