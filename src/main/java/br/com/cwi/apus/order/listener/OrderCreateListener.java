package br.com.cwi.apus.order.listener;

import br.com.cwi.apus.order.service.CreateOrderService;
import br.com.cwi.apus.order.web.request.CreateOrderRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.cwi.apus.order.configuration.RabbitMQConfiguration.QUEUE;

@Slf4j
@AllArgsConstructor
@Component
public class OrderCreateListener {

    private CreateOrderService createOrderService;

    @RabbitListener(queues = QUEUE)
    public void consumer(CreateOrderRequest request) {

        log.info("Novo pedido recebido: {}", request);

        createOrderService.execute(request);
    }
}
