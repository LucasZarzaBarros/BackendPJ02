package com.autoCenterSilva.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCreateRequest {
    private String nome;
    private String descricao;
    private String marca;
}
