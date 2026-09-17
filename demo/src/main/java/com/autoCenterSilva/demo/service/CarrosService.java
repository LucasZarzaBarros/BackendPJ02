package com.autoCenterSilva.demo.service;

import com.autoCenterSilva.demo.dto.request.CarroCreateRequest;
import com.autoCenterSilva.demo.dto.response.CarroCreateResponse;
import com.autoCenterSilva.demo.entity.Carros;
import com.autoCenterSilva.demo.repository.CarrosRepository;
import com.autoCenterSilva.demo.repository.ProdutoVaricaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CarrosService {
    private final CarrosRepository  carrosRepository;
    private final ProdutoVaricaoRepository produtoVaricaoRepository;

    public CarrosService(CarrosRepository carrosRepository, ProdutoVaricaoRepository produtoVaricaoRepository) {
        this.carrosRepository = carrosRepository;
        this.produtoVaricaoRepository = produtoVaricaoRepository;
    }

    @Transactional
    public CarroCreateResponse salvarCarro (CarroCreateRequest carroCreateRequest){
        Carros carros = new Carros();

        carros.setMarca( carroCreateRequest.getMarca() );
        carros.setModelo( carroCreateRequest.getModelo() );
        carros.setAnoFabricacao(carroCreateRequest.getAnoFabricacao());
        carros.setVersao(carroCreateRequest.getVersaoAutomovel());

        Carros carroSalvo = this.carrosRepository.save(carros);
        return CarroCreateResponse.de(carroSalvo);
    }

    @Transactional
    public void vincularCarroVariacao(Long carroId, Long produtoVariacaoId) {
        var carro = this.carrosRepository.findById(carroId).orElseThrow();
        var variacao = this.produtoVaricaoRepository.findById(produtoVariacaoId).orElseThrow();

        carro.getVariacoes().add(variacao);
    }
}
