package com.curso.domains;

import com.curso.domains.dtos.DiretorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "diretor")
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_diretor")
    private Integer id;

    @NotNull
    @NotBlank

    private String nome;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String CPF;

    @JsonIgnore
    @OneToMany(mappedBy = "diretor")
    private List<Jogo> jogos = new ArrayList<>();

    public Diretor() {
    }

    public Diretor(Integer id, String nome, String CPF) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
    }

    public Diretor(DiretorDTO dto) {
        this.nome = dto.getNome();
        this.id = dto.getId();
        this.CPF = dto.getCPF();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotNull @NotBlank String nome) {
        this.nome = nome;
    }

    public @NotNull @NotBlank String getCPF() {
        return CPF;
    }

    public void setCPF(@NotNull @NotBlank String CPF) {
        this.CPF = CPF;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diretor diretor = (Diretor) o;
        return Objects.equals(id, diretor.id) && Objects.equals(nome, diretor.nome) && Objects.equals(CPF, diretor.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, CPF);
    }
}
