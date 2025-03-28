package com.curso.domains.dtos;

import com.curso.domains.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VeiculoDTO {

    private Long id;

    @NotNull(message = "O campo titulo não pode ser nulo")
    @NotBlank(message = "campo titulo não pode estar vazio")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAquisicao;

    @NotNull(message = "O campo valorAquisicai não pode ser nulo")
    private double valorAquisicai;

    @NotNull(message = "O campo nome Proprietario não pode ser nulo")
    @NotBlank(message = "campo nome Proprietario não pode estar vazio")
    private String nomeProprietario;

    @NotNull(message = "O campo CPF não pode ser nulo")
    @NotBlank(message = "campo CPF não pode estar vazio")
    private String cpf;

    public VeiculoDTO() {
    }

    public VeiculoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.descricao = veiculo.getDescricao();
        this.dataAquisicao = veiculo.getDataAquisicao();
        this.valorAquisicai = veiculo.getValorAquisicai();
        this.nomeProprietario = veiculo.getNomeProprietario();
        this.cpf = veiculo.getCpf();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo titulo não pode ser nulo") @NotBlank(message = "campo titulo não pode estar vazio") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo titulo não pode ser nulo") @NotBlank(message = "campo titulo não pode estar vazio") String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    @NotNull(message = "O campo valorAquisicai não pode ser nulo")
    public double getValorAquisicai() {
        return valorAquisicai;
    }

    public void setValorAquisicai(@NotNull(message = "O campo valorAquisicai não pode ser nulo") double valorAquisicai) {
        this.valorAquisicai = valorAquisicai;
    }

    public @NotNull(message = "O campo nome Proprietario não pode ser nulo") @NotBlank(message = "campo nome Proprietario não pode estar vazio") String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(@NotNull(message = "O campo nome Proprietario não pode ser nulo") @NotBlank(message = "campo nome Proprietario não pode estar vazio") String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public @NotNull(message = "O campo CPF não pode ser nulo") @NotBlank(message = "campo CPF não pode estar vazio") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "O campo CPF não pode ser nulo") @NotBlank(message = "campo CPF não pode estar vazio") String cpf) {
        this.cpf = cpf;
    }
}
