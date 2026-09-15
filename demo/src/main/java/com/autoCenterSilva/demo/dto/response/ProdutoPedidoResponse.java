package com.autoCenterSilva.demo.dto.response;

import com.autoCenterSilva.demo.entity.ProdutoPedido;

import java.util.List;

public record ProdutoPedidoResponse(String produtoNome,
                                    String medida,
                                    Integer quantidade,
                                    Double precoUnitario)
{
    public static ProdutoPedidoResponse de(ProdutoPedido produtoPedido)
    {
        return new ProdutoPedidoResponse(
                produtoPedido.getProdutoVariacao().getProduto().getNome(),
                produtoPedido.getProdutoVariacao().getMedida(),
                produtoPedido.getQuantidade(),
                produtoPedido.getPrecoUnitario()
        );
    }
}
