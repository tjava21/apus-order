package br.com.cwi.apus.order.service.order;


import br.com.cwi.apus.order.domain.order.*;
import br.com.cwi.apus.order.external.apus.request.BasketRequest;
import br.com.cwi.apus.order.repository.OrderRepository;
import br.com.cwi.apus.order.utils.DomainUtils;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.List;

@AllArgsConstructor
@Service
public class CreateOrderService {

    private OrderRepository repository;
    private JavaMailSender mailSender;

    @Transactional
    public Order execute(BasketRequest basket) {

        Order order = new Order();
        order.setCustomer(buildCustomer(basket));
        order.setPayment(buildPayment(basket));
        order.setShipping(buildShipping(basket));
     //   order.setItems(buildItems(basket, order));

        repository.save(order);
        sendMail(order);

        return order;
    }

    private List<OrderItem> buildItems(BasketRequest basket, Order order) {
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

    private Shipping buildShipping(BasketRequest basket) {
        return Shipping.builder()
                .total(basket.getTotalShipping())
                .time(basket.getTime())
                .volume(basket.getVolume())
                .zip(basket.getZip())
                .address(basket.getAddress())
                .externalId(basket.getShippingId())
                .build();
    }

    private Payment buildPayment(BasketRequest basket) {
        return Payment.builder()
                .total(basket.getTotal())
                .card(basket.getCard())
                .externalId(basket.getPaymentId())
                .build();
    }

    private Customer buildCustomer(BasketRequest basket) {
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
