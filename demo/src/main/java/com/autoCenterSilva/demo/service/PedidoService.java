package com.autoCenterSilva.demo.service;


import com.autoCenterSilva.demo.dto.request.PedidoCreateRequest;
import com.autoCenterSilva.demo.dto.request.ProdutoPedidoRequest;
import com.autoCenterSilva.demo.dto.response.PedidoCreateResponse;
import com.autoCenterSilva.demo.entity.*;
import com.autoCenterSilva.demo.repository.ClientesRepository;
import com.autoCenterSilva.demo.repository.PedidoRepository;
import com.autoCenterSilva.demo.repository.ProdutoVaricaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService {

    @Value("${whatsapp.numero-borracharia}")
    private String numeroBorracharia;

    private PedidoRepository pedidoRepository;
    private ClientesRepository clientesRepository;
    private ProdutoVaricaoRepository produtoVaricaoRepository;

    public PedidoService(ProdutoVaricaoRepository produtoVaricaoRepository, ClientesRepository clientesRepository, PedidoRepository pedidoRepository) {
        this.produtoVaricaoRepository = produtoVaricaoRepository;
        this.clientesRepository = clientesRepository;
        this.pedidoRepository = pedidoRepository;
    }

    private String montarMensagemWhats(Pedido  pedido){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "Olá! Gostaria de confirmar seu pedido #").append(pedido.getId()).append(":\n\n");
        for (ProdutoPedido itens : pedido.getProdutoPedidos()){
            stringBuilder.append("- ")
                    .append(itens.getQuantidade()).append("x ")
                    .append(itens.getProdutoVariacao().getProduto().getNome())
                    .append(" (").append(itens.getProdutoVariacao().getMedida()).append(") - ")
                    .append("R$ ").append(itens.getProdutoVariacao().getPreco())
                    .append("\n");
        }
        stringBuilder.append("\nValor total: R$ ").append(pedido.getValorTotal());
        return   "https://wa.me/" + numeroBorracharia
                + "?text=" + URLEncoder.encode(stringBuilder.toString(), StandardCharsets.UTF_8);

    }


    @Transactional
    public PedidoCreateResponse salvar(PedidoCreateRequest pedidoCreateRequest) {
        Cliente cliente = clientesRepository.findById(pedidoCreateRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException(
                        "Cliente Não encontrado"
                ));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus("Aguardando_Confirmacao");
        pedido.setDataPedido(LocalDateTime.now());

        List<ProdutoPedido> itens = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ProdutoPedidoRequest produtoPedidoRequest : pedidoCreateRequest.getItens()) {
            ProdutoVariacao variacao = produtoVaricaoRepository.findById(produtoPedidoRequest.getProdutoVariacaoId())
                    .orElseThrow(() -> new RuntimeException("Variação não encontrada"));

            ProdutoPedido produtoPedido = new ProdutoPedido();
            produtoPedido.setProdutoVariacao(variacao);
            produtoPedido.setQuantidade(produtoPedidoRequest.getQuantidade());

            if (produtoPedido.getQuantidade() > variacao.getQuantidadeEstoque()){
                throw new RuntimeException("Estoque insuficiente para a medida " + variacao.getMedida() +
                        ". Disponível: " + variacao.getQuantidadeEstoque()
                );
            }
            produtoPedido.setQuantidade(produtoPedidoRequest.getQuantidade());
            produtoPedido.setPedido(pedido);

            total = total.add(variacao.getPreco().multiply(BigDecimal.valueOf(produtoPedido.getQuantidade())));            itens.add(produtoPedido);
            variacao.setQuantidadeEstoque(variacao.getQuantidadeEstoque() - produtoPedido.getQuantidade());
            produtoVaricaoRepository.save(variacao);
        }
        pedido.setValorTotal(total);
        pedido.setProdutoPedidos(itens);
        Pedido salvo = pedidoRepository.save(pedido);
        String linkWats = montarMensagemWhats(salvo);

        return PedidoCreateResponse.de(salvo,  linkWats);
    }

}
