package com.curso.services;

import com.curso.domains.Publicadora;
import com.curso.domains.dtos.PublicadoraDTO;
import com.curso.repositories.PublicadoraRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicadoraService {

    @Autowired
    private PublicadoraRepository publicadoraRepo;

    public List<PublicadoraDTO> findAll(){

        return publicadoraRepo.findAll().stream()
                .map(obj -> new PublicadoraDTO(obj))
                .collect(Collectors.toList());
    }

    public Publicadora findById(int id){
        Optional<Publicadora> obj = publicadoraRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("publicadora não encontrada! Id:" + id));
    }

    public Publicadora findByCnpj(String cnpj) {
        Optional<Publicadora> obj = publicadoraRepo.findByCnpj(cnpj);
        return obj.orElseThrow(() -> new ObjectNotFoundException("publicadora não encontrada! cnpj:" + cnpj));
    }

    public Publicadora create(PublicadoraDTO dto){
        dto.setId(null);
        ValidaEditora(dto);
        Publicadora obj = new Publicadora(dto);
        return publicadoraRepo.save(obj);
    }

    private void ValidaEditora(PublicadoraDTO dto){
        Optional<Publicadora> obj = publicadoraRepo.findByCnpj(dto.getCnpj());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Cnpj já cadastrado");
        }
    }

    public Publicadora update(Integer id,PublicadoraDTO objDto){
        objDto.setId(id);
        Publicadora oldObj = findById(id);
        oldObj = new Publicadora(objDto);
        return publicadoraRepo.save(oldObj);
    }

    public void delete(Integer id){
        Publicadora obj = findById(id);
        if(obj.getJogos().size()>0){
            throw new DataIntegrityViolationException("Publicadora não pode ser excluido pois possui Jogos vinculado");
        }
        publicadoraRepo.deleteById(id);
    }

}
