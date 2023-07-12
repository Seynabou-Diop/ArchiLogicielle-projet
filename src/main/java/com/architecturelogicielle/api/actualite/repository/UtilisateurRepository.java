package com.architecturelogicielle.actualite.repository;

import com.architecturelogicielle.actualite.model.Administrateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends CrudRepository<Administrateur, Long> {
}
