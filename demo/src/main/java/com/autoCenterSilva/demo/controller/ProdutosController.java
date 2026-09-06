package com.autoCenterSilva.demo.controller;

import com.autoCenterSilva.demo.dto.request.ProdutoCreateRequest;
import com.autoCenterSilva.demo.dto.response.ProdutosCreateResponse;
import com.autoCenterSilva.demo.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @PostMapping("/criar")
    public ResponseEntity<ProdutosCreateResponse> salvarProduto(@RequestBody ProdutoCreateRequest produtoCreateRequest){
        ProdutosCreateResponse response = this.produtosService.salvarProduto(produtoCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
