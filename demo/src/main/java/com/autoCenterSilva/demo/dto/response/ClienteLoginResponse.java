package com.autoCenterSilva.demo.dto.response;

import com.autoCenterSilva.demo.entity.Cliente;

public record ClienteLoginResponse(String nome, String mensagem)
{
    public static ClienteLoginResponse de(Cliente cliente) {
        return new ClienteLoginResponse(
                cliente.getNome(),
                "Bem vindo: " + cliente.getNome() + " - Login realizado com sucesso!"
        );
    }
}
