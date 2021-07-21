package br.com.cwi.apus.order.web;

import br.com.cwi.apus.order.web.response.product.ProductDetailResponse;
import br.com.cwi.apus.order.web.response.product.ProductResponse;
import br.com.cwi.apus.order.service.product.FindProductByIdService;
import br.com.cwi.apus.order.service.product.FindProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private FindProductsService findProductsService;
    private FindProductByIdService findProductByIdService;

    @GetMapping
    public List<ProductResponse> list() {
        return findProductsService.execute();
    }

    @GetMapping("/{id}")
    public ProductDetailResponse detail(@PathVariable("id") String id) {
        return findProductByIdService.execute(id);
    }
}
