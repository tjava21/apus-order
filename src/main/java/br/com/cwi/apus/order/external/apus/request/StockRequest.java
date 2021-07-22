package br.com.cwi.apus.order.external.apus.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StockRequest {

    private String id;
    private int quantity;
}
