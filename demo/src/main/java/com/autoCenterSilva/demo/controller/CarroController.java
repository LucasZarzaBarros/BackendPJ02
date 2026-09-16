package com.autoCenterSilva.demo.controller;

import com.autoCenterSilva.demo.dto.request.CarroCreateRequest;
import com.autoCenterSilva.demo.dto.response.CarroCreateResponse;
import com.autoCenterSilva.demo.service.CarrosService;
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
@RequestMapping("api/carro")
public class CarroController {

    @Autowired
    private CarrosService carrosService;

    @PostMapping("/criar")
    public ResponseEntity<CarroCreateResponse> criar(@Valid @RequestBody CarroCreateRequest carroCreateRequest){
        CarroCreateResponse response = this.carrosService.salvarCarro(carroCreateRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
