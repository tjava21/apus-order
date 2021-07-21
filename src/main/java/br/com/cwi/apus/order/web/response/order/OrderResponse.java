package br.com.cwi.apus.order.web.response.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
@Data
public class OrderResponse {

    private String id;
}
