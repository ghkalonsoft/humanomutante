package com.meli.esmutante.core;

public class GestionMatriz {

	private static final String CARACTERES_VALIDOS = "ATCG";
	private String cadena;
	private int cantidadMutante;

	/*
	 * Crear matriz a travez del array dado
	 */
	public char[][] nuevaMatriz(String[] cadena) {
		char[][] matriz = new char[cadena.length][cadena.length];
		for (int i = 0; i < cadena.length; i++) {
			matriz[i] = cadena[i].toCharArray();
		}
		return matriz;
	}

	/*
	 * Recorriodo matriz
	 */
	public int cantMutante(char[][] matriz) {
		int numMutante = verificarFilas(matriz);
		numMutante += verificarColumnas(matriz);
		numMutante += verificarDiagonal1(matriz);
		numMutante += verificarDiagonal2(matriz);
		return numMutante;
	}

	private int verificarFilas(char[][] matriz) {
		cantidadMutante = 0;
		for (int i = 0; i < matriz.length; i++) {
			cantidadMutante += validarSecuencia(matriz[i]) ? 1 : 0;
		}
		return cantidadMutante;
	}

	private int verificarColumnas(char[][] matriz) {
		cantidadMutante = 0;
		cadena = "";
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
		cantidadMutante = 0;
		cadena = "";
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
		cantidadMutante = 0;
		cadena = "";
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
		int secuencia;
		while (cont < cadena.length) {
			secuencia = 0;
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
