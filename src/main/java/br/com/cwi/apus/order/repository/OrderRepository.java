package br.com.cwi.apus.order.repository;

import br.com.cwi.apus.order.domain.order.Order;
import br.com.cwi.apus.order.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByStatus (OrderStatus status);
}
