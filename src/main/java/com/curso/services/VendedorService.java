package com.curso.services;

import com.curso.domains.Vendedor;
import com.curso.domains.dtos.VendedorDTO;
import com.curso.repositories.VendedorRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepo;

    public List<VendedorDTO> findAll(){
        return vendedorRepo.findAll().stream()
                .map(obj -> new VendedorDTO(obj)).collect(Collectors.toList());
    }

    public Vendedor findById(Long id){
        Optional<Vendedor> obj = vendedorRepo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public Vendedor findByCpf(String cpf){
        Optional<Vendedor> obj = vendedorRepo.findByCpf(cpf);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! CPF:" + cpf));
    }

    public Vendedor findByEmail(String email){
        Optional<Vendedor> obj = vendedorRepo.findByEmail(email);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! E-mail:" + email));
    }

    public Vendedor create(VendedorDTO objDto){
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Vendedor newObj = new Vendedor(objDto);
        return vendedorRepo.save(newObj);
    }

    public Vendedor update(Long id, VendedorDTO objDto){
        objDto.setId(id);
        Vendedor oldObj = findById(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Vendedor(objDto);
        return vendedorRepo.save(oldObj);
    }

    public void delete(Long id){
        Vendedor obj = findById(id);
        if(obj.getVendas().size()>0){
            throw new DataIntegrityViolationException("Vendedor não pode ser deletado pois possui ordems de venda");
        }
        vendedorRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(VendedorDTO objDto){
        Optional<Vendedor> obj = vendedorRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já está cadastrdo no sistema");
        }

        Optional<Vendedor> obj2 = vendedorRepo.findByEmail(objDto.getEmail());
        if(obj2.isPresent() && obj2.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("E-mail já está cadastrdo no sistema");
        }

    }

}
