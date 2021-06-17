package com.meli.esmutante.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meli.esmutante.model.MutanteModel;

@Repository
public interface IEsmutanteRepository extends CrudRepository<MutanteModel, Long> {

	@Query(value = "SELECT COUNT(*) FROM mutantes", nativeQuery = true)
	Long cantHuman();

	@Query(value = "SELECT COUNT(*) FROM mutantes WHERE es_mutante", nativeQuery = true)
	Long cantMutant();

}
