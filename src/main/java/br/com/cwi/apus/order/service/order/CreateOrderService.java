package br.com.cwi.apus.order.service.order;


import br.com.cwi.apus.order.domain.order.*;
import br.com.cwi.apus.order.repository.OrderRepository;
import br.com.cwi.apus.order.utils.DomainUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CreateOrderService {

    private OrderRepository repository;
/*
    @Transactional
    public Order execute(Basket basket) {

        Order order = new Order();
        order.setCustomer(buildCustomer(basket));
        order.setPayment(buildPayment(basket));
        order.setShipping(buildShipping(basket));
        order.setItems(buildItems(basket, order));

        return repository.save(order);
    }
/*
    private List<OrderItem> buildItems(Basket basket, Order order) {
        return basket.getItems().stream()
                .map(b -> OrderItem.builder()
                        .productId(DomainUtils.toExternalId(b.getProduct().getId()))
                        .description(b.getProduct().getDescription())
                        .quantity(b.getQuantity())
                        .price(b.getProduct().getPrice())
                        .volume(b.getProduct().getVolume())
                        .order(order)
                        .build())
                .collect(Collectors.toList());
    }
/*
    private Shipping buildShipping(Basket basket) {
        return Shipping.builder()
                .total(basket.getTotalShipping())
                .time(basket.getTime())
                .volume(basket.getVolume())
                .zip(basket.getZip())
                .address(basket.getAddress())
                .externalId(basket.getShippingId())
                .build();
    }

    private Payment buildPayment(Basket basket) {
        return Payment.builder()
                .total(basket.getTotal())
                .card(basket.getCard())
                .externalId(basket.getPaymentId())
                .build();
    }

    private Customer buildCustomer(Basket basket) {
        return Customer.builder()
                .name(basket.getName())
                .email(basket.getEmail())
                .build();
    }*/
}
