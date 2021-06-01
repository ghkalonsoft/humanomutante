package com.meli.esmutante.service;

import org.springframework.stereotype.Service;

import com.meli.esmutante.dto.EstadisticaDto;
import com.meli.esmutante.model.MutanteModel;
import com.meli.esmutante.repository.IEsmutanteRepository;

import net.bytebuddy.matcher.IsNamedMatcher;

@Service
public class EsMutanteServiceImpl implements IEsMutanteService {

	static final String CARACTERES_VALIDOS = "ATCG";
	private IEsmutanteRepository iEsmutanteRepository;

	public EsMutanteServiceImpl(IEsmutanteRepository iEsmutanteRepository) {
		this.iEsmutanteRepository = iEsmutanteRepository;
	}

	@Override
	public Boolean isMutant(String[] dna) {
		char[][] matriz = nuevaMatriz(dna);
		int cantidadMutante = cantMutante(matriz);
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

	/*
	 * Crear matriz a travez del array dado
	 */
	private char[][] nuevaMatriz(String[] cadena) {
		char[][] matriz = new char[cadena.length][cadena.length];
		for (int i = 0; i < cadena.length; i++) {
			matriz[i] = cadena[i].toCharArray();
		}
		return matriz;
	}

	/*
	 * Recorriodo matriz
	 */
	private int cantMutante(char[][] matriz) {
		int cantidadMutante = verificarFilas(matriz);
		cantidadMutante += verificarColumnas(matriz);
		cantidadMutante += verificarDiagonal1(matriz);
		cantidadMutante += verificarDiagonal2(matriz);
		return cantidadMutante;
	}

	private int verificarFilas(char[][] matriz) {
		int cantidadMutante = 0;
		for (int i = 0; i < matriz.length; i++) {
			cantidadMutante += validarSecuencia(matriz[i]) ? 1 : 0;
		}
		return cantidadMutante;
	}

	private int verificarColumnas(char[][] matriz) {
		int cantidadMutante = 0;
		String cadena;
		for (int i = 0; i < matriz.length; i++) {
			cadena = "";
			for (int j = 0; j < matriz.length; j++) {
				cadena += matriz[j][i];
			}
			cantidadMutante += validarSecuencia(cadena.toCharArray()) ? 1 : 0;
		}
		return cantidadMutante;
	}

	private int verificarDiagonal1(char[][] matriz) {
		int cantidadMutante = 0;
		String cadena;
		for (int i = 0; i < (matriz.length * 2) - 1; i++) {
			cadena = "";
			if (matriz.length - 1 - i >= 0) {
				for (int j = matriz.length - 1; j >= matriz.length - 1 - i; j--) {
					cadena += matriz[j - (matriz.length - 1 - i)][j];
				}
				if (cadena.length() >= 4)
					cantidadMutante += validarSecuencia(cadena.toCharArray()) ? 1 : 0;
			} else {
				for (int j = matriz.length - 1; j >= i - (matriz.length - 1); j--) {
					cadena += matriz[j][j - (i - (matriz.length - 1))];
				}
				if (cadena.length() >= 4)
					cantidadMutante += validarSecuencia(cadena.toCharArray()) ? 1 : 0;
			}
		}
		return cantidadMutante;
	}

	private int verificarDiagonal2(char[][] matriz) {
		int cantidadMutante = 0;
		String cadena;
		for (int i = 0; i < (matriz.length * 2) - 1; i++) {
			cadena = "";
			if (matriz.length - 1 - i >= 0) {
				for (int j = 0; j <= i; j++) {
					cadena += matriz[i - j][j];
				}
				if (cadena.length() >= 4)
					cantidadMutante += validarSecuencia(cadena.toCharArray()) ? 1 : 0;
			} else {
				for (int j = matriz.length - 1; j >= i - (matriz.length - 1); j--) {
					cadena += matriz[j][i - j];
				}
				if (cadena.length() >= 4)
					cantidadMutante += validarSecuencia(cadena.toCharArray()) ? 1 : 0;
			}

		}
		return cantidadMutante;
	}

	/*
	 * Método que valida la existencia de una secuencia
	 */
	public boolean validarSecuencia(char[] cadena) {
		int cont = 0;
		while (cont < cadena.length) {
			int secuencia = 0;
			for (int j = 0; j < cadena.length; j++) {
				if (cadena[cont] != cadena[j]) {
					secuencia = 0;
				} else {
					secuencia += 1;
					if (secuencia == 4 && CARACTERES_VALIDOS.contains(String.valueOf(cadena[j]))) {
						return true;
					}
				}
			}

			cont++;

		}
		return false;
	}

}
