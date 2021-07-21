package br.com.cwi.apus.order.web.response.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetailResponse {

    private String id;
    private String description;
    private int quantity;
    private int volume;
    private BigDecimal price;
}
