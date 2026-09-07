package com.autoCenterSilva.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoCreateRequest {
    private Long clienteId;
    private List<ProdutoPedidoRequest> itens;

}
