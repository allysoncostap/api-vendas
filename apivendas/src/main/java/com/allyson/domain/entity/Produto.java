package com.allyson.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "preco_unitario")
    private BigDecimal preco;


}
