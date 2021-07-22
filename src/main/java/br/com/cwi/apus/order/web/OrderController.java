package br.com.cwi.apus.order.web;

import br.com.cwi.apus.order.service.CreateOrderService;
import br.com.cwi.apus.order.service.OrderMailService;
import br.com.cwi.apus.order.web.request.CreateOrderRequest;
import br.com.cwi.apus.order.web.response.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Random;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private CreateOrderService createOrderService;
    private OrderMailService orderMailService;

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        return createOrderService.execute(request);
    }

    @GetMapping("/mail")
    public void create(@RequestParam String to) {
        orderMailService.send(Objects.toString(new Random().nextInt(1000)), to);
    }
}
