package com.autoCenterSilva.demo.controller;


import com.autoCenterSilva.demo.dto.request.ClienteCreatRequest;
import com.autoCenterSilva.demo.dto.request.ClienteLoginRequest;
import com.autoCenterSilva.demo.dto.response.ClienteCreateResponse;
import com.autoCenterSilva.demo.dto.response.ClienteLoginResponse;
import com.autoCenterSilva.demo.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping("/criar")
    public ResponseEntity<ClienteCreateResponse> salvar(@RequestBody ClienteCreatRequest clienteCreatRequest){
        ClienteCreateResponse response = this.clientesService.salvarCliente(clienteCreatRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteCreateResponse> login(@RequestBody ClienteLoginRequest clienteLoginRequest){
        ClienteLoginResponse clienteLogin = this.clientesService.validarLogin(clienteLoginRequest);
        return new ResponseEntity<>(clienteLogin, HttpStatus.OK);
    }
}
