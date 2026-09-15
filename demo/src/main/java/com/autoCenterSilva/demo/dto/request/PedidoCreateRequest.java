package com.autoCenterSilva.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoCreateRequest {
    @NotBlank(message = "Id do Cliente obrigatório")
    private Long clienteId;
    private List<ProdutoPedidoRequest> itens;
}
