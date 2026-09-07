package com.autoCenterSilva.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPedidoRequest {
    private Long produtoVariacaoId;
    private Integer quantidade;
}
