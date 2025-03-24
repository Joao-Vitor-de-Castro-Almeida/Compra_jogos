package com.curso.repositories;

import com.curso.domains.Jogador;
import com.curso.domains.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByCpf(String cpf);
    Optional<Vendedor> findByEmail(String email);

}
