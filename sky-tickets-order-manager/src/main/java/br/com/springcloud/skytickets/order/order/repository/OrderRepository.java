package br.com.springcloud.skytickets.order.order.repository;

import br.com.springcloud.skytickets.order.order.repository.domain.Order;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class OrderRepository {

    private static final Logger log = getLogger(OrderRepository.class);

    public Order save(Order order) {
        log.info("Order saved: {}", order);
        return order;
    }
}
