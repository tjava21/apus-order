package br.com.cwi.apus.order.external.lyra;

import br.com.cwi.apus.order.external.lyra.request.PaymentInformRequest;
import br.com.cwi.apus.order.external.lyra.response.PaymentInformResponse;
import br.com.cwi.apus.order.external.lyra.response.PaymentStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@Service
public class PaymentService {

    public static final String DEFAULT_STATUS = "PENDENTE";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.lyra.url}")
    private String url;

    @Value("${app.lyra.company}")
    private String company;

    public String inform(String card, BigDecimal total) {

        try {

            PaymentInformRequest request = PaymentInformRequest.builder()
                    .id(company).card(card).total(total)
                    .build();

            PaymentInformResponse response = restTemplate
                    .postForObject(url, request, PaymentInformResponse.class);

            return response.getId();

        } catch (Exception e) {
            log.error("Erro ao informar pagamento (LYRA)", e);
            return null;
        }
    }

    public String getStatus(String id) {

        try {

            String url = this.url + id + "/status";

            PaymentStatusResponse response = restTemplate
                    .getForObject(url, PaymentStatusResponse.class);

            return response.getStatus();

        } catch (Exception e) {
            log.error("Erro ao consultar situação do pagamento (LYRA)", e);
            return DEFAULT_STATUS;
        }
    }
}
