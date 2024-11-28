package com.curso.services;

import com.curso.domains.Diretor;
import com.curso.domains.Jogo;
import com.curso.domains.Publicadora;
import com.curso.domains.dtos.JogoDTO;
import com.curso.repositories.DiretorRepository;
import com.curso.repositories.JogoRepository;
import com.curso.repositories.PublicadoraRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepo;

    @Autowired
    private DiretorRepository diretorRepo;

    @Autowired
    private PublicadoraRepository publicadoraRepo;


    public List<JogoDTO> findAll(){

        return jogoRepo.findAll().stream()
                .map(obj -> new JogoDTO(obj))
                .collect(Collectors.toList());
    }

    public Jogo findById(Long id){
        Optional<Jogo> obj = jogoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("jogo não encontrado! Id:" + id));
    }

    public Jogo findBycodigoBarra(String codigoBarra){
        Optional<Jogo> obj = jogoRepo.findBycodigoBarra(codigoBarra);
        return obj.orElseThrow(() -> new ObjectNotFoundException("jogo não encontrado! codigo de barra:" + codigoBarra));
    }

    public Jogo create(JogoDTO dto){
        dto.setIdJogo(null);
        ValidaJogo(dto);
        Jogo obj = new Jogo(dto);
        return jogoRepo.save(obj);
    }

    private void ValidaJogo(JogoDTO dto){
        Optional<Jogo> obj = jogoRepo.findBycodigoBarra(dto.getCodigoBarra());
        if(obj.isPresent() && obj.get().getIdJogo() != dto.getIdJogo()){
            throw new DataIntegrityViolationException("Codigo de barra já cadastrado");
        }

        Optional<Diretor> diretor = diretorRepo.findById(dto.getDiretor());
        if (!diretor.isPresent()){
            throw new DataIntegrityViolationException("Diretor - " + dto.getDiretor() + " não esta cadastrado!");
        }

        Optional<Publicadora> publicadora = publicadoraRepo.findById(dto.getPublicadora());
        if (!publicadora.isPresent()){
            throw new DataIntegrityViolationException("Publicadora - " + dto.getPublicadora() + " não esta cadastrado!");
        }
    }

    public Jogo update(Long id, JogoDTO objDto){
        objDto.setIdJogo(id);
        Jogo oldObj = findById(id);
        ValidaJogo(objDto);
        oldObj = new Jogo(objDto);
        return jogoRepo.save(oldObj);
    }

    public void delete(Long id){
        Jogo obj = findById(id);
        jogoRepo.deleteById(id);
    }


}
