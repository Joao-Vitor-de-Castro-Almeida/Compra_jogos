package com.curso.domains.dtos;

import com.curso.domains.Venda;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class VendaDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate diaVenda = LocalDate.now();

    @NotNull(message = "O campo nome é requirido")
    private String nomejogo;

    @NotNull(message = "O campo descriçâo é requirido")
    private String descricao;

    @NotNull(message = "O campo edicao é requirido")
    private Integer edicao;

    @NotNull(message = "O campo formaPagameno é requirido")
    private Integer formaPagameno;

    @NotNull(message = "O campo desvendedorcriçâo é requirido")
    private Long vendedor;

    @NotNull(message = "O campo jogador é requirido")
    private Long jogador;

    private String nomeVendedor;
    private String nomeJogador;

    public VendaDTO() {
    }

    public VendaDTO(Venda venda) {
        this.id = venda.getId();
        this.diaVenda = venda.getDiaVenda();
        this.nomejogo = venda.getNomejogo();
        this.descricao = venda.getDescricao();
        this.edicao = venda.getEdicao().getId();
        this.formaPagameno = venda.getFormaPagameno().getId();
        this.vendedor = venda.getVendedor().getId();
        this.jogador = venda.getJogador().getId();
        this.nomeVendedor = venda.getVendedor().getPrimeiroNome() + " " + venda.getVendedor().getSegundoNome();
        this.nomeJogador = venda.getJogador().getPrimeiroNome() + " " + venda.getJogador().getSegundoNome();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDiaVenda() {
        return diaVenda;
    }

    public void setDiaVenda(LocalDate diaVenda) {
        this.diaVenda = diaVenda;
    }

    public @NotNull(message = "O campo nome é requirido") String getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(@NotNull(message = "O campo nome é requirido") String nomejogo) {
        this.nomejogo = nomejogo;
    }

    public @NotNull(message = "O campo descriçâo é requirido") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo descriçâo é requirido") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O campo edicao é requirido") Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(@NotNull(message = "O campo edicao é requirido") Integer edicao) {
        this.edicao = edicao;
    }

    public @NotNull(message = "O campo formaPagameno é requirido") Integer getFormaPagameno() {
        return formaPagameno;
    }

    public void setFormaPagameno(@NotNull(message = "O campo formaPagameno é requirido") Integer formaPagameno) {
        this.formaPagameno = formaPagameno;
    }

    public @NotNull(message = "O campo desvendedorcriçâo é requirido") Long getVendedor() {
        return vendedor;
    }

    public void setVendedor(@NotNull(message = "O campo desvendedorcriçâo é requirido") Long vendedor) {
        this.vendedor = vendedor;
    }

    public @NotNull(message = "O campo jogador é requirido") Long getJogador() {
        return jogador;
    }

    public void setJogador(@NotNull(message = "O campo jogador é requirido") Long jogador) {
        this.jogador = jogador;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }
}
