package br.com.cwi.apus.order.mapper;

import br.com.cwi.apus.order.domain.Product;
import br.com.cwi.apus.order.utils.DomainUtils;
import br.com.cwi.apus.order.web.response.product.ProductDetailResponse;
import br.com.cwi.apus.order.web.response.product.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toProductResponse(Product entity) {
        ProductResponse response = new ProductResponse();
        response.setId(DomainUtils.toExternalId(entity.getId()));
        response.setDescription(entity.getDescription());
        return response;
    }

    public ProductDetailResponse toProductDetailResponse(Product entity) {
        ProductDetailResponse response = new ProductDetailResponse();
        response.setId(DomainUtils.toExternalId(entity.getId()));
        response.setDescription(entity.getDescription());
        response.setVolume(entity.getVolume());
        response.setQuantity(entity.getQuantity());
        response.setPrice(entity.getPrice());
        return response;
    }
}
