package com.autoCenterSilva.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoCreateRequest {
    @NotNull(message = "Id do Cliente obrigatório")
    private Long clienteId;
    private List<ProdutoPedidoRequest> itens;
}
