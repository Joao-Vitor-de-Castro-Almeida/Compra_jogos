package com.curso.domains;

import com.curso.domains.dtos.JogadorDTO;
import com.curso.domains.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "jogador")
public class Jogador extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "jogador")
    private List<Venda> vendas = new ArrayList<>();

    public Jogador(Long id, String primeiroNome, String segundoNome, String CPF, String email, String senha) {
        super(id, primeiroNome, segundoNome, CPF, email, senha);
        addTipoPessoa(TipoPessoa.JOGADOR);
    }

    public Jogador(JogadorDTO obj){
        this.id = obj.getId();
        this.primeiroNome = obj.getPrimeiroNome();
        this.segundoNome = obj.getSegundoNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.createAt = obj.getCreatedAt();
        this.tipoPessoa = obj.getTipoPessoa().stream()
                .map(x -> x.getId()).collect(Collectors.toSet());
        addTipoPessoa(TipoPessoa.JOGADOR);
    }

    public Jogador() {
        super();
        addTipoPessoa(TipoPessoa.JOGADOR);
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
