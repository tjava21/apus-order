package br.com.cwi.apus.order.job;

import br.com.cwi.apus.order.external.apus.ApusService;
import br.com.cwi.apus.order.external.lyra.PaymentService;
import br.com.cwi.apus.order.external.lyra.response.PaymentStatusResponse;
import br.com.cwi.apus.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static br.com.cwi.apus.order.domain.OrderStatus.CRIADO;
import static br.com.cwi.apus.order.domain.OrderStatus.PENDENTE;

@Slf4j
@AllArgsConstructor
@Component
public class GetPaymentStatus {

    private OrderRepository orderRepository;
    private PaymentService paymentService;
    private ApusService apusService;

    @Scheduled(fixedRateString = "${app.job.time}")
    @Transactional
    public void execute() {

        log.info("Executando job de consulta de situação de pagamentos");

        orderRepository.findByStatus(CRIADO).forEach(order -> {

            PaymentStatusResponse paymentStatus = paymentService.getStatus(order.getPayment().getExternalId());

            if (paymentStatus.isApproved()) {

                log.info("Atualizando estoque com os itens do pedido: {}", order.getId());
                order.getItems().forEach(i -> apusService.updateStock(i.getProductId(), i.getQuantity()));

                log.info("Atualizando situação de pagamento do pedido: {}", order.getId());
                order.setStatus(PENDENTE);
                orderRepository.save(order);
            }
        });
    }
}