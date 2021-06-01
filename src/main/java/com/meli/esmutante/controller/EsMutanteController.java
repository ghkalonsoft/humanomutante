package com.meli.esmutante.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.esmutante.dto.ADNDto;
import com.meli.esmutante.dto.EstadisticaDto;
import com.meli.esmutante.service.IEsMutanteService;

@RestController
@RequestMapping(path = "humanoMutante")
@CrossOrigin("*")
public class EsMutanteController {

	private IEsMutanteService iEsMutanteService;

	public EsMutanteController(IEsMutanteService iEsMutanteBusiness) {
		this.iEsMutanteService = iEsMutanteBusiness;
	}

	@PostMapping(path = "/mutant")
	public ResponseEntity<Boolean> isMutant(@RequestBody ADNDto aDNDto) {
		return ResponseEntity.ok(this.iEsMutanteService.isMutant(aDNDto.getDna()));
	}

	@GetMapping(path = "/stats")
	public EstadisticaDto getEstadistica() {
		return iEsMutanteService.getEstadistica();
	}

}
