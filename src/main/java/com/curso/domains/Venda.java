package com.curso.domains;

import com.curso.domains.enums.Edicao;
import com.curso.domains.enums.FormaPagameno;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate diaVenda = LocalDate.now();

    private String nomejogo;
    private String descricao;
    private Edicao edicao;
    private FormaPagameno formaPagameno;

    @ManyToOne
    @JoinColumn(name = "idVendedor")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "idJogador")
    private Jogador jogador;

    public Venda() {
    }

    public Venda(UUID id, String nomejogo, String descricao, Edicao edicao,
                 FormaPagameno formaPagameno, Vendedor vendedor, Jogador jogador) {
        this.id = id;
        this.nomejogo = nomejogo;
        this.descricao = descricao;
        this.edicao = edicao;
        this.formaPagameno = formaPagameno;
        this.vendedor = vendedor;
        this.jogador = jogador;
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

    public String getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(String nomejogo) {
        this.nomejogo = nomejogo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Edicao getEdicao() {
        return edicao;
    }

    public void setEdicao(Edicao edicao) {
        this.edicao = edicao;
    }

    public FormaPagameno getFormaPagameno() {
        return formaPagameno;
    }

    public void setFormaPagameno(FormaPagameno formaPagameno) {
        this.formaPagameno = formaPagameno;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
