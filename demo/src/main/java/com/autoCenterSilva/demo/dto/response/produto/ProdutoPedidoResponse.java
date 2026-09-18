package com.autoCenterSilva.demo.dto.response.produto;

import com.autoCenterSilva.demo.entity.ProdutoPedido;

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
