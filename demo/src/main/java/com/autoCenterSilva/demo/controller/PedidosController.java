package com.autoCenterSilva.demo.controller;

import com.autoCenterSilva.demo.dto.request.PedidoCreateRequest;
import com.autoCenterSilva.demo.dto.request.ProdutoPedidoRequest;
import com.autoCenterSilva.demo.dto.response.PedidoCreateResponse;
import com.autoCenterSilva.demo.dto.response.ProdutoPedidoResponse;
import com.autoCenterSilva.demo.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pedidos")
public class PedidosController {
    @Autowired
    private PedidoService pedidosService;

    @PostMapping("/criar")
    public ResponseEntity<PedidoCreateResponse> salvarPedido(@Valid @RequestBody PedidoCreateRequest pedidoCreateRequest){
        PedidoCreateResponse response = this.pedidosService.salvar(pedidoCreateRequest);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
