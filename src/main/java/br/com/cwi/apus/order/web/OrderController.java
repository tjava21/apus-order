package br.com.cwi.apus.order.web;


import br.com.cwi.apus.order.external.apus.request.BasketRequest;
import br.com.cwi.apus.order.mapper.OrderMapper;
import br.com.cwi.apus.order.service.order.CreateOrderService;
import br.com.cwi.apus.order.web.response.order.OrderResponse;
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

    private OrderMapper orderMapper;

    @PostMapping
    public OrderResponse execute(@RequestBody BasketRequest request){

        return orderMapper.toOrderResponse(createOrderService.execute(request)) ;
    }
}
