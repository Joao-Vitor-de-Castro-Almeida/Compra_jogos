package com.curso.domains;

import com.curso.domains.dtos.VeiculoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculo")
    private Long id;

    @NotNull
    @NotBlank
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAquisicao;

    @NotNull
    private double valorAquisicai;

    @NotNull
    @NotBlank
    private String nomeProprietario;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String cpf;

    public Veiculo() {
    }

    public Veiculo(Long id, String descricao, LocalDate dataAquisicao,
                   double valorAquisicai, String nomeProprietario, String cpfProprietario) {
        this.id = id;
        this.descricao = descricao;
        this.dataAquisicao = dataAquisicao;
        this.valorAquisicai = valorAquisicai;
        this.nomeProprietario = nomeProprietario;
        this.cpf = cpfProprietario;
    }

    public Veiculo(VeiculoDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.dataAquisicao = dto.getDataAquisicao();
        this.valorAquisicai = dto.getValorAquisicai();
        this.nomeProprietario = dto.getNomeProprietario();
        this.cpf = dto.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @NotBlank String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull @NotBlank String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    @NotNull
    public double getValorAquisicai() {
        return valorAquisicai;
    }

    public void setValorAquisicai(@NotNull double valorAquisicai) {
        this.valorAquisicai = valorAquisicai;
    }

    public @NotNull @NotBlank String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(@NotNull @NotBlank String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public @NotNull @NotBlank String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull @NotBlank String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id) && Objects.equals(descricao, veiculo.descricao) && Objects.equals(nomeProprietario, veiculo.nomeProprietario) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, nomeProprietario);
    }
}
