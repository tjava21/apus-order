package br.com.cwi.apus.order.service.product;

import br.com.cwi.apus.order.domain.Product;
import br.com.cwi.apus.order.exception.NotFoundException;
import br.com.cwi.apus.order.mapper.ProductMapper;
import br.com.cwi.apus.order.repository.ProductRepository;
import br.com.cwi.apus.order.utils.DomainUtils;
import br.com.cwi.apus.order.web.response.product.ProductDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindProductByIdService {

    private ProductRepository repository;
    private ProductMapper mapper;

    public ProductDetailResponse execute(String id) {
        return mapper.toProductDetailResponse(getDomain(id));
    }

    public Product getDomain(String id) {
        return repository.findById(DomainUtils.toInternalId(id))
                .orElseThrow(() -> new NotFoundException("product"));
    }
}
