package com.curso.services;

import com.curso.domains.Veiculo;
import com.curso.domains.dtos.VeiculoDTO;
import com.curso.repositories.VeiculoRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepo;

    public List<VeiculoDTO> findAll(){

        return veiculoRepo.findAll().stream()
                .map(obj -> new VeiculoDTO(obj))
                .collect(Collectors.toList());
    }

    public Veiculo findById(Long id){
        Optional<Veiculo> obj = veiculoRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrada! Id:" + id));
    }

    public Veiculo findByCPF(String Cpf) {
        Optional<Veiculo> obj = veiculoRepo.findByCpf(Cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrada! cpf Proprietario:" + Cpf));
    }

    public Veiculo create(VeiculoDTO dto){
        dto.setId(null);
        ValidaEditora(dto);
        Veiculo obj = new Veiculo(dto);
        return veiculoRepo.save(obj);
    }

    private void ValidaEditora(VeiculoDTO dto){
        Optional<Veiculo> obj = veiculoRepo.findByCpf(dto.getCpf());
        if(obj.isPresent() && obj.get().getId() != dto.getId()){
            throw new DataIntegrityViolationException("Cpf já cadastrado");
        }
    }

    public Veiculo update(Long id,VeiculoDTO objDto){
        objDto.setId(id);
        Veiculo oldObj = findById(id);
        oldObj = new Veiculo(objDto);
        return veiculoRepo.save(oldObj);
    }

    public void delete(Long id){
        Veiculo obj = findById(id);
        veiculoRepo.deleteById(id);
    }
}
