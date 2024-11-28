package com.curso.repositories;


import com.curso.domains.Publicadora;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Entity
public interface PublicadoraRepository extends JpaRepository<Publicadora, Integer> {

    Optional<Publicadora> findByCnpj(String cnpj);

}
