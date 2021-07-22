package br.com.cwi.apus.order.external.lyra;

import br.com.cwi.apus.order.external.lyra.response.PaymentStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public PaymentStatusResponse getStatus(String id) {

        try {

            String url = this.url + id + "/status";

            PaymentStatusResponse response = restTemplate
                    .getForObject(url, PaymentStatusResponse.class);

            return response;

        } catch (Exception e) {
            log.error("Erro ao consultar situação do pagamento (LYRA)", e);
            return new PaymentStatusResponse();
        }
    }
}
