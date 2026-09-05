package com.autoCenterSilva.demo.dto.response;

import com.autoCenterSilva.demo.entity.Produtos;

public record ProdutosCreateResponse
        (Long id,
         String nome,
         String descricao,
         String marca,
         String mensage)
{
    public static ProdutosCreateResponse de(Produtos produtos){
        return new ProdutosCreateResponse(
                produtos.getId(),
                produtos.getNome(),
                produtos.getDescricao(),
                produtos.getMarca(), "Produto cadastrado com sucesso!"
        );
    }

}
