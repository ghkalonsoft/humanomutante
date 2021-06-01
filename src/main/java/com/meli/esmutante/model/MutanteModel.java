package com.meli.esmutante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mutantes")
public class MutanteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mutante")
	private Long idMutante;
	@Column(name = "es_mutante")
	private Boolean esMutante;

	public Long getIdMutante() {
		return idMutante;
	}

	public void setIdMutante(Long idMutante) {
		this.idMutante = idMutante;
	}

	public Boolean getEsMutante() {
		return esMutante;
	}

	public void setEsMutante(Boolean esMutante) {
		this.esMutante = esMutante;
	}

}
