package com.autoCenterSilva.demo.service;

import com.autoCenterSilva.demo.dto.request.ProdutoCreateRequest;
import com.autoCenterSilva.demo.dto.response.ProdutosCreateResponse;
import com.autoCenterSilva.demo.entity.Produtos;
import com.autoCenterSilva.demo.repository.ProdutosReposotory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import java.util.Optional;

@Service
public class ProdutosService {

    private final ProdutosReposotory produtosReposotory;

    public ProdutosService(ProdutosReposotory produtosReposotory) {
        this.produtosReposotory = produtosReposotory;
    }

    public Optional<Produtos> findById(Long id) {
        return produtosReposotory.findById(id);
    }

    @Transactional
    public ProdutosCreateResponse salvarProduto(ProdutoCreateRequest produtoCreateRequest){
        Produtos produto = new Produtos();

        produto.setNome(produtoCreateRequest.getNome());
        produto.setDescricao(produtoCreateRequest.getDescricao());
        produto.setMarca(produtoCreateRequest.getMarca());
        produto.setAtivo(true);

        Produtos produtoSalvo = this.produtosReposotory.save(produto);
        return ProdutosCreateResponse.de(produtoSalvo);

    }
}
