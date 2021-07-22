package br.com.cwi.apus.order.external.apus;

import br.com.cwi.apus.order.external.apus.request.StockRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ApusService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.apus.url}")
    private String url;

    public void updateStock(String id, int quantity) {

        try {

            String url = this.url + "/stock";

            StockRequest request = StockRequest.builder()
                    .id(id)
                    .quantity(quantity)
                    .build();

            restTemplate.put(url, request);

        } catch (Exception e) {
            log.error("Erro ao atualizar estoque (APUS)", e);
        }
    }
}
