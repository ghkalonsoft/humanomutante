package com.meli.esmutante.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.meli.esmutante.model.MutanteModel;
import com.meli.esmutante.repository.IEsmutanteRepository;
import com.meli.esmutante.service.EsMutanteServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EsMutanteServiceTest {

	@InjectMocks
	EsMutanteServiceImpl esMutanteServiceImpl;

	@Mock
	private IEsmutanteRepository iEsmutanteRepository;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MutanteModel mutanteModel = new MutanteModel();
		mutanteModel.setIdMutante(1L);
		mutanteModel.setEsMutante(Boolean.TRUE);
		MockitoAnnotations.initMocks(this);
		when(iEsmutanteRepository.save(mutanteModel)).thenReturn(mutanteModel);
	}

	@Test
	public void testHumanoMutante() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		boolean esMutante = esMutanteServiceImpl.isMutant(dna);
		assertTrue(esMutante);
	}

	@Test
	public void testHumanoNoMutante() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
		boolean esMutante = esMutanteServiceImpl.isMutant(dna);
		assertFalse(esMutante);
	}

	@Test
	public void saveEsMutante() {
		boolean esMutante = esMutanteServiceImpl.saveMutant(2);
		assertTrue(esMutante);
	}

	@Test
	public void saveNoMutante() {
		boolean esMutante = esMutanteServiceImpl.saveMutant(1);
		assertFalse(esMutante);
	}

}
