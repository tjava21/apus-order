package br.com.cwi.apus.order.mapper;

import br.com.cwi.apus.order.domain.Order;
import br.com.cwi.apus.order.web.response.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order entity) {

        OrderResponse response = new OrderResponse();
        response.setId(entity.getId().toString());
        return response;
    }
}
