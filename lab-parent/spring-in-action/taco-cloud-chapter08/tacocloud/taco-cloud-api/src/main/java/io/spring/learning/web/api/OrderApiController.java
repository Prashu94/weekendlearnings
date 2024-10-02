package io.spring.learning.web.api;

import io.spring.learning.data.OrderRepository;
import io.spring.learning.tacos.TacoOrder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {

    private OrderRepository repository;

    public OrderApiController(OrderRepository repository){
        this.repository = repository;
    }

    @GetMapping(produces = "application/json")
    public Iterable<TacoOrder> allOrders(){
        return repository.findAll();
    }

    @PostMapping(path = "/orderId", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order){
        return repository.save(order);
    }

    @PutMapping(path = "/orderId", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order){
        order.setId(orderId);
        return repository.save(order);
    }

    @PatchMapping(path = "/orderId", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody TacoOrder patch){
        TacoOrder order = repository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return repository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
