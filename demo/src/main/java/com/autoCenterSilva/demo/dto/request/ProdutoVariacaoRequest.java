package com.autoCenterSilva.demo.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVariacaoRequest {
    private String medida;
    private Double preco;
    private Integer quantidadeEstoque;
}
