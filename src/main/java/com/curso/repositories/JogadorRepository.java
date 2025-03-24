package com.curso.repositories;

import com.curso.domains.Jogador;
import com.curso.domains.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    Optional<Jogador> findByCpf(String cpf);
    Optional<Jogador> findByEmail(String email);

}
