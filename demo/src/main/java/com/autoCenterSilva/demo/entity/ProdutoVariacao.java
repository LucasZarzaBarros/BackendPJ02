package com.autoCenterSilva.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "produtos_variacao")
public class ProdutoVariacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "medida")
    private String medida;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;

}
