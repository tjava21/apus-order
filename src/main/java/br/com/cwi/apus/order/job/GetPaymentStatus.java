package br.com.cwi.apus.order.job;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetPaymentStatus {

//    private OrderRepository orderRepository;
//    private PaymentService paymentService;
//    private ProductRepository productRepository;
//
//    @Scheduled(fixedRateString = "${app.job.time}")
//    public void execute() {
//        List<Order> orders = orderRepository.findByStatus(OrderStatus.CRIADO);
//
//        orders.forEach(order -> {
//            String status = paymentService.getStatus(order.getPayment().getExternalId());
//
//            if (status.equals("APROVADO") || status.equals("PENDENTE")) {
//                order.getItems().forEach(item -> {
//                    Product product = productRepository.getById(DomainUtils.toInternalId(item.getProductId()));
//                    product.setQuantity(product.getQuantity() - item.getQuantity());
//                    productRepository.save(product);
//                });
//
//                order.setStatus(OrderStatus.PENDENTE);
//                orderRepository.save(order);
//            }
//        });
//    }
}