package io.spring.learning.data;

import io.spring.learning.TacoOrder;

import java.util.Optional;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);

    Optional<TacoOrder> findById(Long id);
}
