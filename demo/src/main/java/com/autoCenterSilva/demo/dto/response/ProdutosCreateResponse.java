package com.autoCenterSilva.demo.dto.response;

import com.autoCenterSilva.demo.entity.Produtos;

public record ProdutosCreateResponse(Long id,
                                     String nome,
                                     String marca,
                                     String modelo,
                                     String mensage)
{
    public static ProdutosCreateResponse de(Produtos produtos){
        return new ProdutosCreateResponse(
                produtos.getId(),
                produtos.getNome(),
                produtos.getMarca(),
                produtos.getModelo(), "Produto cadastrado com sucesso!"
        );
    }
}
