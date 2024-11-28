package com.curso.domains.dtos;

import com.curso.domains.Jogo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JogoDTO {

    private Long idJogo;

    @NotNull(message = "O campo titulo não pode ser nulo")
    @NotBlank(message = "campo titulo não pode estar vazio")
    private String titulo;

    @NotNull(message = "O campo codigoBarra não pode ser nulo")
    @NotBlank(message = "campo codigoBarra não pode estar vazio")
    private String codigoBarra;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @NotNull(message = "O campo valorCompra não pode ser nulo")
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorCompra;

    private int diretor;

    private int publicadora;

    private int status;

    private int completude;

    public JogoDTO() {}

    public JogoDTO(Jogo jogo) {
        this.idJogo = jogo.getIdJogo();
        this.titulo = jogo.getTitulo();
        this.codigoBarra = jogo.getCodigoBarra();
        this.dataCompra = jogo.getDataCompra();
        this.valorCompra = jogo.getValorCompra();
        this.diretor = jogo.getDiretor().getId();
        this.publicadora = jogo.getPublicadora().getId();
        this.status = jogo.getStatus().getId();
        this.completude = jogo.getCompletude().getId();
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(Long idJogo) {
        this.idJogo = idJogo;
    }

    public @NotNull(message = "O campo titulo não pode ser nulo") @NotBlank(message = "campo titulo não pode estar vazio") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull(message = "O campo titulo não pode ser nulo") @NotBlank(message = "campo titulo não pode estar vazio") String titulo) {
        this.titulo = titulo;
    }

    public @NotNull(message = "O campo codigoBarra não pode ser nulo") @NotBlank(message = "campo codigoBarra não pode estar vazio") String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(@NotNull(message = "O campo codigoBarra não pode ser nulo") @NotBlank(message = "campo codigoBarra não pode estar vazio") String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public @NotNull(message = "O campo valorCompra não pode ser nulo") @Digits(integer = 15, fraction = 3) BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(@NotNull(message = "O campo valorCompra não pode ser nulo") @Digits(integer = 15, fraction = 3) BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getDiretor() {
        return diretor;
    }

    public void setDiretor(int diretor) {
        this.diretor = diretor;
    }

    public int getPublicadora() {
        return publicadora;
    }

    public void setPublicadora(int publicadora) {
        this.publicadora = publicadora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCompletude() {
        return completude;
    }

    public void setCompletude(int completude) {
        this.completude = completude;
    }
}
