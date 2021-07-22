package br.com.cwi.apus.order.external.lyra.response;

import lombok.Data;

import static java.util.Objects.isNull;

@Data
public class PaymentStatusResponse {

    private static final String PAGAMENTO_APROVADO = "APROVADO";

    private String id;
    private String status;

    public boolean isApproved() {
        if (isNull(this.status)) {
            return false;
        }

        return this.status.equals(PAGAMENTO_APROVADO);
    }
}
