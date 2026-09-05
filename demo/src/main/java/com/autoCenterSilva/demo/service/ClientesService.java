package com.autoCenterSilva.demo.service;

import com.autoCenterSilva.demo.dto.request.ClienteCreatRequest;
import com.autoCenterSilva.demo.dto.response.ClienteCreateResponse;
import com.autoCenterSilva.demo.entity.Cliente;
import com.autoCenterSilva.demo.repository.ClientesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    private final ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public List<Cliente> findAll() {
        return clientesRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clientesRepository.findById(id);
    }

    @Transactional
    public ClienteCreateResponse salvarCliente(ClienteCreatRequest clienteCreatRequest){
        if (!clienteCreatRequest.getSenha().equals(clienteCreatRequest.getConfirmarSenha())) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }
        Cliente cliente = new Cliente();
        cliente.setNome(clienteCreatRequest.getNome());
        cliente.setTelefone(clienteCreatRequest.getTelefone());
        cliente.setSenha( clienteCreatRequest.getSenha());
        cliente.setStatus(true);

        Cliente clienteSalvo = this.clientesRepository.save(cliente);
        return ClienteCreateResponse.de(clienteSalvo);
    }

    @Transactional
    public ClienteLoginResponse validarLogin(ClienteLoginRequest clienteLoginRequest) {
        Cliente cliente = this.clientesRepository.findByTelefone(clienteLoginRequest.getTelefone())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Telefone informado está incorreto"));
        if (!cliente.getStatus()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Conta desativada");
        }
        if (!cliente.getSenha().equals(clienteLoginRequest.getSenha())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha está incorreta");
        }
        if (!cliente.getNome().equals(clienteLoginRequest.getNome())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nome inserido está incorreto");
        }
        return new ClienteLoginResponse(cliente.getNome(),  "Login realizado com sucesso!");
    }


}
