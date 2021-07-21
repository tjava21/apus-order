package br.com.cwi.apus.order.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Order order;

    private String productId;
    private String description;
    private int quantity;
    private int volume;
    private BigDecimal price;

}
