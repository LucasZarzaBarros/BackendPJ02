package com.autoCenterSilva.demo.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVariacaoRequest {
    private String medidas;
    private Double preco;
    private Integer quantidadeEstoque;
}
