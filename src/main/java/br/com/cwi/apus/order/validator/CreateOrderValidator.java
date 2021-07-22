package br.com.cwi.apus.order.validator;

import br.com.cwi.apus.order.exception.ValidationException;
import br.com.cwi.apus.order.web.request.CreateOrderRequest;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class CreateOrderValidator {

    public void valid(CreateOrderRequest request) {

        if (isNull(request.getId())) {
            throw new ValidationException("id não pode ser nulo");
        }
    }
}
