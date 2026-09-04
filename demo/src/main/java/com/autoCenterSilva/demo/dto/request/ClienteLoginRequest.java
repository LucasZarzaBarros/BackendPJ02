package com.autoCenterSilva.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteLoginRequest {
    private String nome;
    private String telefone;
    private String senha;
}
