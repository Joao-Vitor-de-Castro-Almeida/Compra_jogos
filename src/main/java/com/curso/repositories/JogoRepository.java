package com.curso.repositories;


import com.curso.domains.Jogo;
import com.curso.domains.Publicadora;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Entity
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    Optional<Jogo> findBycodigoBarra(String codigoBarra);

}
