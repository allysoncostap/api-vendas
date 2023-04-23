package com.allyson.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="client")
public class Cliente {
@Id
@GeneratedValue(strategy =  GenerationType.AUTO)
@Column(name= "id")
    private Integer id;
@Column(name = "nome", length = 100)
    private String nome;

@Column(name = "cpf", length = 11)
private String cpf;


@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
private Set<Pedido> pedidos;


}
