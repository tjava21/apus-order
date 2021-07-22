package br.com.cwi.apus.order.service;

import br.com.cwi.apus.order.domain.*;
import br.com.cwi.apus.order.mapper.OrderMapper;
import br.com.cwi.apus.order.repository.OrderRepository;
import br.com.cwi.apus.order.utils.DomainUtils;
import br.com.cwi.apus.order.web.request.CreateOrderRequest;
import br.com.cwi.apus.order.web.response.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CreateOrderService {

    private OrderRepository repository;
    private JavaMailSender mailSender;
    private OrderMapper orderMapper;

    @Transactional
    public OrderResponse execute(CreateOrderRequest basket) {

        Order order = new Order();
        order.setCustomer(buildCustomer(basket));
        order.setPayment(buildPayment(basket));
        order.setShipping(buildShipping(basket));
        order.setItems(buildItems(basket, order));

        repository.save(order);
        sendMail(order);

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

    private Shipping buildShipping(CreateOrderRequest basket) {
        return Shipping.builder()
                .total(basket.getTotalShipping())
                .time(basket.getTime())
                .volume(basket.getVolume())
                .zip(basket.getZip())
                .address(basket.getAddress())
                .externalId(basket.getShippingId())
                .build();
    }

    private Payment buildPayment(CreateOrderRequest basket) {
        return Payment.builder()
                .total(basket.getTotal())
                .card(basket.getCard())
                .externalId(basket.getPaymentId())
                .build();
    }

    private Customer buildCustomer(CreateOrderRequest basket) {
        return Customer.builder()
                .name(basket.getName())
                .email(basket.getEmail())
                .build();
    }

    private void sendMail(Order order) {
        String pedido = order.getId().toString();
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(order.getCustomer().getEmail());
        email.setSubject("Pedido " + pedido);
        email.setText("Seu pedido " + pedido + " foi Criado com sucesso");

        mailSender.send(email);
    }
}
