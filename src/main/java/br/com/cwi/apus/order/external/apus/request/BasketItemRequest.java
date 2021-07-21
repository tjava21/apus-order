package br.com.cwi.apus.order.external.apus.request;

import br.com.cwi.apus.order.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BasketItemRequest {

    private Long id;

    private int quantity;

    private BasketRequest basket;

    private Product product;
}
