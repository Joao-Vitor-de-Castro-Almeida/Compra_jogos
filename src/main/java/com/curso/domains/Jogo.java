package com.curso.domains;

import com.curso.domains.dtos.JogoDTO;
import com.curso.domains.enums.Completude;
import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Jogo")
    private Long idJogo;

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull @NotBlank
    @Column(unique = true)
    private String codigoBarra;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorCompra;

    @ManyToOne
    @JoinColumn(name = "iddiretor")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "idpublicadora")
    private Publicadora publicadora;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status")
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "completude")
    private Completude completude;

    public Jogo() {
        this.valorCompra = BigDecimal.ZERO;
        this.status = Status.JOGANDO;
        this.completude = Completude.cem;
    }

    public Jogo(Long idJogo, String titulo, String codigoBarra, LocalDate dataCompra, BigDecimal valorCompra,
                Diretor diretor, Publicadora publicadora, Status status, Completude completude) {
        this.idJogo = idJogo;
        this.titulo = titulo;
        this.codigoBarra = codigoBarra;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
        this.diretor = diretor;
        this.publicadora = publicadora;
        this.status = status;
        this.completude = completude;
    }

    public Jogo(JogoDTO dto) {
        this.idJogo = dto.getIdJogo();
        this.titulo = dto.getTitulo();
        this.dataCompra = dto.getDataCompra();
        this.codigoBarra = dto.getCodigoBarra();
        this.valorCompra = dto.getValorCompra();
        this.diretor = new Diretor();
        this.diretor.setId(dto.getDiretor());
        this.publicadora = new Publicadora();
        this.publicadora.setId(dto.getPublicadora());
        this.status = Status.toEnum(dto.getStatus());
        this.completude = Completude.toEnum(dto.getCompletude());
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(Long idJogo) {
        this.idJogo = idJogo;
    }

    public @NotNull @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotNull @NotBlank String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(@NotNull @NotBlank String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public @NotNull @Digits(integer = 15, fraction = 3) BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(@NotNull @Digits(integer = 15, fraction = 3) BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Publicadora getPublicadora() {
        return publicadora;
    }

    public void setPublicadora(Publicadora publicadora) {
        this.publicadora = publicadora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Completude getCompletude() {
        return completude;
    }

    public void setCompletude(Completude completude) {
        this.completude = completude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return Objects.equals(idJogo, jogo.idJogo) && Objects.equals(titulo, jogo.titulo) && Objects.equals(codigoBarra, jogo.codigoBarra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJogo, titulo, codigoBarra);
    }
}
