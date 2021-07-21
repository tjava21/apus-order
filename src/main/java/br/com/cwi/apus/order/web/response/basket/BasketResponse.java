package br.com.cwi.apus.order.web.response.basket;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
@Data
public class BasketResponse {

    private String id;
    private BigDecimal total;
    private BigDecimal totalItems;
    private BigDecimal totalShipping;
    private Integer time;
    private int volume;
    private BasketShippingResponse shipping;
    private BasketCustomerResponse customer;
    private List<BasketItemResponse> items = new ArrayList<>();

    @Data
    public static class BasketItemResponse {
        private String id;
        private int quantity;
        private int volume;
        private BigDecimal price;
    }

    @Data
    public static class BasketShippingResponse {
        private String zip;
        private String address;
    }

    @Data
    public static class BasketCustomerResponse {
        private String name;
        private String email;
    }
}
