package io.spring.learning.repository;

import io.spring.learning.entity.Restaurant;

public interface RestaurantRepository {

    public String getInfo();

    public Restaurant findByMerchantNumber(String merchantNumber);

    public Long getRestaurantCount();

}
