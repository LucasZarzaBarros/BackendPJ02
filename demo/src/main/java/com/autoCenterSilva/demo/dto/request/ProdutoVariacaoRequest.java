package com.autoCenterSilva.demo.dto.request;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoVariacaoRequest {
    @Min(value = 80, message = "Latgura minima é 80")
    @Max(value = 600, message = "Valor maximo é 600")
    @NotBlank(message = "Largura é obrigatório!")
    private Integer largura;

    @Min(value = 20, message = "Valor minino é 20")
    @Max(value = 95, message = "Valor maximo é 95")
    @NotBlank(message = "Perfil é obrigatório!")
    private Integer perfil;

    @Min(value = 8, message = "Valor minimo é 8")
    @Max(value = 32, message = "Valor maximo é 32")
    @NotBlank(message = "Aro é obrigatório!")
    private Integer aro;

    @Min(value = 60, message = "Valor minimo é 60")
    @Max(value = 120, message = "Valor maximo é 120")
    @NotBlank(message = "Indice de Carga é obrigatório!")
    private String indiceCarga;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private Double preco;

    @Min(value = 4, message = "Quantidade de estoque minimo é 4")
    private Integer quantidadeEstoque;
}
