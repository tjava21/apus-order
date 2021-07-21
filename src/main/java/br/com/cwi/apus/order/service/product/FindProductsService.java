package br.com.cwi.apus.order.service.product;

import br.com.cwi.apus.order.mapper.ProductMapper;
import br.com.cwi.apus.order.web.response.product.ProductResponse;
import br.com.cwi.apus.order.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class FindProductsService {

    private ProductRepository repository;
    private ProductMapper mapper;

    public List<ProductResponse> execute() {
        return repository.findAll()
                .stream().map(mapper::toProductResponse)
                .collect(toList());
    }
}
