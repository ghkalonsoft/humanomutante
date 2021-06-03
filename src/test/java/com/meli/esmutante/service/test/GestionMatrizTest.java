package com.meli.esmutante.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meli.esmutante.core.GestionMatriz;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class GestionMatrizTest {

	GestionMatriz gestionMatriz = new GestionMatriz();

	@Test
	public void secuenciaValidadaEsMutante() {
		char[] dna = "CCCCTA".toCharArray();
		boolean esMutante = gestionMatriz.validarSecuencia(dna);
		assertTrue(esMutante);
	}

	@Test
	public void secuenciaValidadaNoMutante() {
		char[] dna = "ATGCGA".toCharArray();
		boolean esMutante = gestionMatriz.validarSecuencia(dna);
		assertFalse(esMutante);
	}

}
