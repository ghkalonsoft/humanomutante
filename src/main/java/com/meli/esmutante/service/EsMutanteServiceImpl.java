package com.meli.esmutante.service;

import org.springframework.stereotype.Service;

import com.meli.esmutante.core.GestionMatriz;
import com.meli.esmutante.dto.EstadisticaDto;
import com.meli.esmutante.model.MutanteModel;
import com.meli.esmutante.repository.IEsmutanteRepository;

@Service
public class EsMutanteServiceImpl implements IEsMutanteService {

	private IEsmutanteRepository iEsmutanteRepository;

	public EsMutanteServiceImpl(IEsmutanteRepository iEsmutanteRepository) {
		this.iEsmutanteRepository = iEsmutanteRepository;
	}

	@Override
	public Boolean isMutant(String[] dna) {
		GestionMatriz gestionMatriz = new GestionMatriz();
		char[][] matriz = gestionMatriz.nuevaMatriz(dna);
		int cantidadMutante = gestionMatriz.cantMutante(matriz);
		return saveMutant(cantidadMutante);
	}

	@Override
	public Boolean saveMutant(int cantMutante) {
		boolean esMutante = cantMutante >= 2 ? Boolean.TRUE : Boolean.FALSE;
		MutanteModel mutanteModel = new MutanteModel();
		mutanteModel.setEsMutante(esMutante);
		this.iEsmutanteRepository.save(mutanteModel);
		return esMutante;
	}

	@Override
	public EstadisticaDto getEstadistica() {
		EstadisticaDto estadisticaDto = new EstadisticaDto();
		estadisticaDto.setCountHumanDna(this.iEsmutanteRepository.cantHuman());
		estadisticaDto.setCountMutantDna(this.iEsmutanteRepository.cantMutant());
		estadisticaDto.setRatio(
				estadisticaDto.getCountMutantDna().doubleValue() / estadisticaDto.getCountHumanDna().doubleValue());
		estadisticaDto.setRatio(Double.isNaN(estadisticaDto.getRatio()) ? 0.0 : estadisticaDto.getRatio());
		return estadisticaDto;
	}

}
