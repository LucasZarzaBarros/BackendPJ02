package com.autoCenterSilva.demo.service;

import com.autoCenterSilva.demo.dto.request.ProdutoCreateRequest;
import com.autoCenterSilva.demo.dto.request.ProdutoVariacaoRequest;
import com.autoCenterSilva.demo.dto.response.ProdutoVariacaoResponse;
import com.autoCenterSilva.demo.dto.response.ProdutosCreateResponse;
import com.autoCenterSilva.demo.entity.ProdutoVariacao;
import com.autoCenterSilva.demo.entity.Produtos;
import com.autoCenterSilva.demo.repository.ProdutoVaricaoRepository;
import com.autoCenterSilva.demo.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutosService {
    private final ProdutosRepository produtosRepository;
    private final ProdutoVaricaoRepository produtoVaricaoRepository;

    public ProdutosService(ProdutosRepository produtosReposotory, ProdutoVaricaoRepository produtoVaricaoRepository) {
        this.produtosRepository = produtosReposotory;
        this.produtoVaricaoRepository = produtoVaricaoRepository;
    }

    public Optional<Produtos> findById(Long id) {
       return produtosRepository.findById(id);
    }

    @Transactional
    public ProdutosCreateResponse salvarProduto(ProdutoCreateRequest produtoCreateRequest){
        Produtos produto = new Produtos();

        produto.setNome(produtoCreateRequest.getNome());
        produto.setDescricao(produtoCreateRequest.getDescricao());
        produto.setMarca(produtoCreateRequest.getMarca());
        produto.setAtivo(true);

        Produtos produtoSalvo = this.produtosRepository.save(produto);
        return ProdutosCreateResponse.de(produtoSalvo);

    }

    @Transactional
    public ProdutoVariacaoResponse salvarVariacao(Long id,  ProdutoVariacaoRequest  produtoVariacaoRequest){
        Produtos produtos =  produtosRepository.findById((id))
                .orElseThrow(()  ->  new RuntimeException("Produto não encontrado"));
        ProdutoVariacao variacao  =  new ProdutoVariacao();
        variacao.setMedidas(produtoVariacaoRequest.getMedidas());
        variacao.setPreco(produtoVariacaoRequest.getPreco());
        variacao.setQuantidadeEstoque(produtoVariacaoRequest.getQuantidadeEstoque());
        variacao.setProduto(produtos);
        ProdutoVariacao variacaoSalvo = this.produtoVaricaoRepository.save(variacao);
        return ProdutoVariacaoResponse.de(variacao);
    }


}
