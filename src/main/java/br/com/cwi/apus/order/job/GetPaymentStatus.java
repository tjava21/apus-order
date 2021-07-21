package br.com.cwi.apus.order.job;

import br.com.cwi.apus.order.domain.Product;
import br.com.cwi.apus.order.domain.order.Order;
import br.com.cwi.apus.order.domain.order.OrderStatus;
import br.com.cwi.apus.order.external.lyra.PaymentService;
import br.com.cwi.apus.order.repository.OrderRepository;
import br.com.cwi.apus.order.repository.ProductRepository;
import br.com.cwi.apus.order.utils.DomainUtils;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GetPaymentStatus {

    private OrderRepository orderRepository;
    private PaymentService paymentService;
    private ProductRepository productRepository;

    @Scheduled(fixedRateString = "${app.job.time}")
    public void execute() {
        List<Order> orders = orderRepository.findByStatus(OrderStatus.CRIADO);

        orders.forEach(order -> {
            String status = paymentService.getStatus(order.getPayment().getExternalId());

            if (status.equals("APROVADO") || status.equals("PENDENTE")) {
                order.getItems().forEach(item -> {
                    Product product = productRepository.getById(DomainUtils.toInternalId(item.getProductId()));
                    product.setQuantity(product.getQuantity() - item.getQuantity());
                    productRepository.save(product);
                });

                order.setStatus(OrderStatus.PENDENTE);
                orderRepository.save(order);
            }
        });
    }
}