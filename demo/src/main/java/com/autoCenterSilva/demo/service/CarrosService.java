package com.autoCenterSilva.demo.service;

import com.autoCenterSilva.demo.dto.request.CarroCreateRequest;
import com.autoCenterSilva.demo.dto.response.CarroCreateResponse;
import com.autoCenterSilva.demo.entity.Carros;
import com.autoCenterSilva.demo.repository.CarrosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CarrosService {
    private final CarrosRepository  carrosRepository;

    public CarrosService(CarrosRepository carrosRepository) {
        this.carrosRepository = carrosRepository;
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
}
