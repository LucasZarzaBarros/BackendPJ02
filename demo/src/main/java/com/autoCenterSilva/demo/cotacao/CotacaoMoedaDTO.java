package com.autoCenterSilva.demo.cotacao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CotacaoMoedaDTO {
    // A API externa devolve um objeto JSON chamado "USDBRL"
    @JsonProperty("USDBRL")
    private DadosMoeda USDBRL;

    public DadosMoeda getUSDBRL() {
        return USDBRL;
    }

    public void setUSDBRL(DadosMoeda USDBRL) {
        this.USDBRL = USDBRL;
    }

    public static class DadosMoeda {
        // "bid" é o campo do JSON com o valor atual do Dólar
        @JsonProperty("bid")
        private String bid;

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }
    }
}
