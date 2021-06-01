package com.meli.esmutante.service;

import com.meli.esmutante.dto.EstadisticaDto;

public interface IEsMutanteService {
	public Boolean isMutant(String[] dna);

	public Boolean saveMutant(int cantMutante);

	public EstadisticaDto getEstadistica();
}
