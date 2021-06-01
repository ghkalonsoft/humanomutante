package com.meli.esmutante.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EstadisticaDto {

	@JsonProperty("count_mutant_dna")
	private Long countMutantDna;

	@JsonProperty("count_human_dna")
	private Long countHumanDna;

	private double ratio;

	public Long getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(Long countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public Long getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(Long countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

}
