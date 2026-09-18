package com.autoCenterSilva.demo.service;

import com.autoCenterSilva.demo.dto.request.ClienteAtualizaRequest;
import com.autoCenterSilva.demo.dto.request.ClienteCreatRequest;
import com.autoCenterSilva.demo.dto.request.ClienteLoginRequest;
import com.autoCenterSilva.demo.dto.response.ClienteAtualizaResponse;
import com.autoCenterSilva.demo.dto.response.ClienteBuscaResponse;
import com.autoCenterSilva.demo.dto.response.ClienteCreateResponse;
import com.autoCenterSilva.demo.dto.response.ClienteLoginResponse;
import com.autoCenterSilva.demo.entity.Cliente;
import com.autoCenterSilva.demo.entity.PerfilUsuario;
import com.autoCenterSilva.demo.exception.ResourceNotFoundException;
import com.autoCenterSilva.demo.repository.ClientesRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {
    private final ClientesRepository clientesRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientesService.class);

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public List<Cliente> findAll() {
        return clientesRepository.findAll();
    }

    public ClienteBuscaResponse findById(Long id) {
        Cliente cliente = clientesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado"));
        return ClienteBuscaResponse.de(cliente);
    }

    @Transactional
    public ClienteCreateResponse salvarCliente(ClienteCreatRequest clienteCreatRequest){
        logger.info("Adicionando um Cliente no Sistema: nome={}, telefone={}", clienteCreatRequest.getNome(), clienteCreatRequest.getTelefone());
        if (!clienteCreatRequest.getSenha().equals(clienteCreatRequest.getConfirmarSenha())) {
            logger.error("Senhas informadas não são iguais para o cliente: nome={}", clienteCreatRequest.getNome());
            throw new IllegalArgumentException("As senhas não coincidem");
        }
        Cliente cliente = new Cliente();
        cliente.setNome(clienteCreatRequest.getNome());
        cliente.setPerfil(PerfilUsuario.CLIENTE);
        cliente.setSenha( clienteCreatRequest.getSenha());
        cliente.setTelefone(clienteCreatRequest.getTelefone());

        Cliente clienteSalvo = this.clientesRepository.save(cliente);
        return ClienteCreateResponse.de(clienteSalvo);
    }

    @Transactional
    public ClienteLoginResponse validarLogin(ClienteLoginRequest clienteLoginRequest) {
        Cliente cliente = this.clientesRepository.findByTelefone(clienteLoginRequest.getTelefone())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Telefone informado está incorreto"));
        if (!cliente.getPerfil().equals(PerfilUsuario.CLIENTE)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Conta desativada");
        }
        if (!cliente.getSenha().equals(clienteLoginRequest.getSenha())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha está incorreta");
        }
        if (!cliente.getNome().equals(clienteLoginRequest.getNome())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nome inserido está incorreto");
        }
        return ClienteLoginResponse.de(cliente);
    }

    @Transactional
    public ClienteAtualizaResponse atualizarSenha(Long id, ClienteAtualizaRequest clienteAtualizaRequest){
        Cliente cliente = clientesRepository.findById((id)).orElseThrow(()  ->  new RuntimeException("Cliente não encontrado"));

        if (!cliente.getNome().equals(clienteAtualizaRequest.getNome())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nome está incorreto");
        }
        cliente.setSenha(clienteAtualizaRequest.getSenha());

        Cliente clienteAtualizado = this.clientesRepository.save(cliente);
        return ClienteAtualizaResponse.de(clienteAtualizado);
    }
}
