package com.autoCenterSilva.demo.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVariacaoRequest {
    private String largura;
    private String perfil;
    private String aro;
    private String indiceCarga;
    private Double preco;
    private Integer quantidadeEstoque;
}
