package com.curso.services;

import com.curso.domains.Diretor;
import com.curso.domains.Jogo;
import com.curso.domains.Publicadora;
import com.curso.domains.enums.Completude;
import com.curso.domains.enums.Status;
import com.curso.repositories.DiretorRepository;
import com.curso.repositories.JogoRepository;
import com.curso.repositories.PublicadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private DiretorRepository diretorRepo;

    @Autowired
    private PublicadoraRepository publicadoraRepo;

    @Autowired
    private JogoRepository jogoRepo;

    public void initDB() {

        Diretor diretor01 = new Diretor(null, "Julio Menakos", "23.453.535-20");
        Diretor diretor02 = new Diretor(null, "Menezes Calolo", "84.586.965-10");
        Diretor diretor03 = new Diretor(null, "Zoriu Paulo", "54.179.854-15");

        diretorRepo.save(diretor01);
        diretorRepo.save(diretor02);
        diretorRepo.save(diretor03);

        Publicadora publicadora01 = new Publicadora(null, "12.345.678-0001-00", "Rito Games");
        Publicadora publicadora02 = new Publicadora(null, "71.382.968-8456-45", "Naute Nortes");

        publicadoraRepo.save(publicadora01);
        publicadoraRepo.save(publicadora02);

        Jogo jogo01 = new Jogo(null, "Bom de Guerra", "GTIN-13",
                LocalDate.now(), new BigDecimal("48"), diretor01, publicadora01, Status.JOGANDO, Completude.cinquenta);
        Jogo jogo02 = new Jogo(null, "Sangue Ruim", "JKTR-54",
                LocalDate.now(), new BigDecimal("65"), diretor02, publicadora02, Status.NAOJOGADO, Completude.zero);
        Jogo jogo03 = new Jogo(null, "Matadores do Futuro", "LIDR-95",
                LocalDate.now(), new BigDecimal("98"), diretor03, publicadora01, Status.JOGADO, Completude.cem);

        jogoRepo.save(jogo01);
        jogoRepo.save(jogo02);
        jogoRepo.save(jogo03);

    }
}
