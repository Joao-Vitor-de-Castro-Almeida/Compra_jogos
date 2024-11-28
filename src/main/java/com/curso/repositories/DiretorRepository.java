package com.curso.repositories;

import com.curso.domains.Diretor;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Entity
public interface DiretorRepository extends JpaRepository<Diretor, Integer> {

    Optional<Diretor> findByCPF(String CPF);

}
