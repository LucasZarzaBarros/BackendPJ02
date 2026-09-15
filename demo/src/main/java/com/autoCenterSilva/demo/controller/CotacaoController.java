package com.autoCenterSilva.demo.controller;

import com.autoCenterSilva.demo.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping("/produto/{id}/converter-para-reais")
    public BigDecimal converterPrecoProdutoParaReais(@PathVariable Long id) {
        return cotacaoService.converterPrecoProdutoParaDolar(id);
    }
}
