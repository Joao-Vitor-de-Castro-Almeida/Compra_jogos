package com.curso.domains.dtos;

import com.curso.domains.Diretor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiretorDTO {

    private Integer id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotBlank(message = "campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O campo CPF não pode ser nulo")
    @NotBlank(message = "campo CPF não pode estar vazio")
    private String CPF;

    public DiretorDTO() {
    }

    public DiretorDTO(Diretor diretor) {
        this.id = diretor.getId();
        this.nome = diretor.getNome();
        this.CPF = diretor.getCPF();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "campo nome não pode estar vazio") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O campo nome não pode ser nulo") @NotBlank(message = "campo nome não pode estar vazio") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O campo CPF não pode ser nulo") @NotBlank(message = "campo CPF não pode estar vazio") String getCPF() {
        return CPF;
    }

    public void setCPF(@NotNull(message = "O campo CPF não pode ser nulo") @NotBlank(message = "campo CPF não pode estar vazio") String CPF) {
        this.CPF = CPF;
    }
}
