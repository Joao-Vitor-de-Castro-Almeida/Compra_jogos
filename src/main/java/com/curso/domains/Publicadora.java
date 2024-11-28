package com.curso.domains;

import com.curso.domains.dtos.PublicadoraDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "publicadora")
public class Publicadora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_publicadora")
    private Integer id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String cnpj;

    @NotNull @NotBlank
    private String razaoSocial;

    @JsonIgnore
    @OneToMany(mappedBy = "publicadora")
    private List<Jogo> jogos = new ArrayList<>();

    public Publicadora() {
    }

    public Publicadora(Integer id, String cnpj, String razaoSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public Publicadora(PublicadoraDTO dto) {
        this.id = dto.getId();
        this.cnpj = dto.getCnpj();
        this.razaoSocial = dto.getRazaoSocial();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NotNull @NotBlank String cnpj) {
        this.cnpj = cnpj;
    }

    public @NotNull @NotBlank String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(@NotNull @NotBlank String razaoSocial) {
        this.razaoSocial = razaoSocial;
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
        Publicadora that = (Publicadora) o;
        return Objects.equals(id, that.id) && Objects.equals(cnpj, that.cnpj) && Objects.equals(razaoSocial, that.razaoSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, razaoSocial);
    }
}
