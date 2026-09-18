package com.autoCenterSilva.demo.controller;

import com.autoCenterSilva.demo.dto.request.ProdutoMedidaPesquisaRequest;
import com.autoCenterSilva.demo.dto.request.produto.ProdutoCreateRequest;
import com.autoCenterSilva.demo.dto.request.produto.ProdutoVariacaoRequest;
import com.autoCenterSilva.demo.dto.response.produto.ProdutoVariacaoPesquisaResponse;
import com.autoCenterSilva.demo.dto.response.produto.ProdutoVariacaoResponse;
import com.autoCenterSilva.demo.dto.response.produto.ProdutoCreateResponse;
import com.autoCenterSilva.demo.service.ProdutosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @PostMapping("/criar")
    public ResponseEntity<ProdutoCreateResponse> salvarProduto(@Valid @RequestBody ProdutoCreateRequest produtoCreateRequest){
        ProdutoCreateResponse response = this.produtosService.salvarProduto(produtoCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/variacao/{id}")
    public ResponseEntity<ProdutoVariacaoResponse> salvarProdutoVariacao(@Valid @PathVariable Long id, @RequestBody ProdutoVariacaoRequest produtoVariacaoRequest){
        ProdutoVariacaoResponse response = this.produtosService.salvarVariacao(id, produtoVariacaoRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/pesquisar")
    public ResponseEntity<List<ProdutoVariacaoPesquisaResponse>> pesquisar(@Valid @RequestBody ProdutoMedidaPesquisaRequest request) {
        return ResponseEntity.ok(produtosService.pesquisarPorMedida(request));
    }


}
