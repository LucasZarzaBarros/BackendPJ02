package com.autoCenterSilva.demo.controller;


import com.autoCenterSilva.demo.dto.request.ClienteAtualizaRequest;
import com.autoCenterSilva.demo.dto.request.ClienteCreatRequest;
import com.autoCenterSilva.demo.dto.request.ClienteLoginRequest;
import com.autoCenterSilva.demo.dto.response.ClienteAtualizaResponse;
import com.autoCenterSilva.demo.dto.response.ClienteBuscaResponse;
import com.autoCenterSilva.demo.dto.response.ClienteCreateResponse;
import com.autoCenterSilva.demo.dto.response.ClienteLoginResponse;
import com.autoCenterSilva.demo.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clientes")
@CrossOrigin(origins = {"http://localhost:4200", "http://10.212.215.204:4200"})
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @PostMapping("/criar")
    public ResponseEntity<ClienteCreateResponse> salvar(@Valid @RequestBody ClienteCreatRequest clienteCreatRequest){
        ClienteCreateResponse response = this.clientesService.salvarCliente(clienteCreatRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteLoginResponse> login(@Valid @RequestBody ClienteLoginRequest clienteLoginRequest){
        ClienteLoginResponse clienteLogin = this.clientesService.validarLogin(clienteLoginRequest);
        return new ResponseEntity<>(clienteLogin, HttpStatus.OK);
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<ClienteAtualizaResponse> atualizar(@Valid @PathVariable Long id, @RequestBody ClienteAtualizaRequest clienteAtualizRequest){
        ClienteAtualizaResponse response = this.clientesService.atualizarSenha(id, clienteAtualizRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/busca/{id}")
    public ResponseEntity<ClienteBuscaResponse>Busca(@Valid @PathVariable Long id){
        ClienteBuscaResponse response = this.clientesService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
