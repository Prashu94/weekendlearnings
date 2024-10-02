package io.spring.learning.data;

import io.spring.learning.tacos.TacoOrder;
import io.spring.learning.tacos.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
