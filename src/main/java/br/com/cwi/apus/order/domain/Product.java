package br.com.cwi.apus.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private int quantity;
    private int volume;
    private BigDecimal price;
}
