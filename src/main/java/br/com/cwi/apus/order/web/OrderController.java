package br.com.cwi.apus.order.web;


import br.com.cwi.apus.order.service.CreateOrderService;
import br.com.cwi.apus.order.web.request.CreateOrderRequest;
import br.com.cwi.apus.order.web.response.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private CreateOrderService createOrderService;

    @PostMapping
    public OrderResponse create(@RequestBody CreateOrderRequest request) {
        return createOrderService.execute(request);
    }
}
