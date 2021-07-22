package br.com.cwi.apus.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Shipping {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal total;
    private int time;
    private int volume;
    private String zip;
    private String address;
    private String externalId;

}
