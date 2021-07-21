package br.com.cwi.apus.order.external.lyra.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentInformRequest {

    private String id;
    private BigDecimal total;
    private String card;
}
