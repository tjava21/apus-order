package br.com.cwi.apus.order.service;

import br.com.cwi.apus.order.domain.*;
import br.com.cwi.apus.order.mapper.OrderMapper;
import br.com.cwi.apus.order.repository.OrderRepository;
import br.com.cwi.apus.order.utils.DomainUtils;
import br.com.cwi.apus.order.validator.CreateOrderValidator;
import br.com.cwi.apus.order.web.request.CreateOrderRequest;
import br.com.cwi.apus.order.web.response.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CreateOrderService {

    private OrderRepository repository;
    private OrderMapper orderMapper;
    private OrderMailService orderMailService;
    private CreateOrderValidator createOrderValidator;

    @Transactional
    public OrderResponse execute(CreateOrderRequest request) {

        createOrderValidator.valid(request);

        Order order = new Order();
        order.setCustomer(buildCustomer(request));
        order.setPayment(buildPayment(request));
        order.setShipping(buildShipping(request));
        order.setItems(buildItems(request, order));

        repository.save(order);

        orderMailService.send(order.getId().toString(), order.getCustomer().getEmail());

        return orderMapper.toOrderResponse(order);
    }

    private List<OrderItem> buildItems(CreateOrderRequest request, Order order) {
        return request.getItems().stream()
                .map(b -> OrderItem.builder()
                        .productId(DomainUtils.toExternalId(b.getId()))
                        .description(b.getDescription())
                        .quantity(b.getQuantity())
                        .price(b.getPrice())
                        .volume(b.getVolume())
                        .order(order)
                        .build())
                .collect(Collectors.toList());
    }

    private Shipping buildShipping(CreateOrderRequest request) {
        return Shipping.builder()
                .total(request.getTotalShipping())
                .time(request.getTime())
                .volume(request.getVolume())
                .zip(request.getZip())
                .address(request.getAddress())
                .externalId(request.getShippingId())
                .build();
    }

    private Payment buildPayment(CreateOrderRequest request) {
        return Payment.builder()
                .total(request.getTotal())
                .card(request.getCard())
                .externalId(request.getPaymentId())
                .build();
    }

    private Customer buildCustomer(CreateOrderRequest request) {
        return Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }
}
