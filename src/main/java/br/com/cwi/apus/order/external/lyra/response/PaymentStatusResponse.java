package br.com.cwi.apus.order.external.lyra.response;

import lombok.Data;

@Data
public class PaymentStatusResponse {

    private String id;
    private String status;
}
