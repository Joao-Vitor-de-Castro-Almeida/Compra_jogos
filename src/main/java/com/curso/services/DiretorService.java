package com.curso.services;

import com.curso.domains.Diretor;
import com.curso.domains.Publicadora;
import com.curso.domains.dtos.DiretorDTO;
import com.curso.repositories.DiretorRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepo;

    public List<DiretorDTO> findAll(){

        return diretorRepo.findAll().stream()
                .map(obj -> new DiretorDTO(obj))
                .collect(Collectors.toList());
    }

    public Diretor findById(int id){
        Optional<Diretor> obj = diretorRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Diretor não encontrado! Id:" + id));
    }

    public Diretor findByCPF(String CPF) {
        Optional<Diretor> obj = diretorRepo.findByCPF(CPF);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Diretor não encontrada! cnpj:" + CPF));
    }

    public Diretor create(DiretorDTO dto){
        dto.setId(null);
        ValidaDiretor(dto);
        Diretor obj = new Diretor(dto);
        return diretorRepo.save(obj);
    }

    private void ValidaDiretor(DiretorDTO dto){
        Optional<Diretor> obj = diretorRepo.findByCPF(dto.getCPF());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado");
        }

    }

    public Diretor update(Integer id,DiretorDTO objDto){
        objDto.setId(id);
        Diretor oldObj = findById(id);
        oldObj = new Diretor(objDto);
        return diretorRepo.save(oldObj);
    }

    public void delete(Integer id){
        Diretor obj = findById(id);
        if(obj.getJogos().size()>0){
            throw new DataIntegrityViolationException("Diretor não pode ser excluido pois possui Jogo vinculado");
        }
        diretorRepo.deleteById(id);
    }

}
