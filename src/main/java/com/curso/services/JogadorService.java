package com.curso.services;

import com.curso.domains.Jogador;
import com.curso.domains.dtos.JogadorDTO;
import com.curso.repositories.JogadorRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepo;

    @Autowired
    private PasswordEncoder encoder;

    public List<JogadorDTO> findAll(){
        return jogadorRepo.findAll().stream()
                .map(obj -> new JogadorDTO(obj)).collect(Collectors.toList());
    }

    public Jogador findById(Long id){
        Optional<Jogador> obj = jogadorRepo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id));
    }

    public Jogador findByCpf(String cpf){
        Optional<Jogador> obj = jogadorRepo.findByCpf(cpf);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! CPF:" + cpf));
    }

    public Jogador findByEmail(String email){
        Optional<Jogador> obj = jogadorRepo.findByEmail(email);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! E-mail:" + email));
    }

    public Jogador create(JogadorDTO objDto){
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        ValidaPorCPFeEmail(objDto);
        Jogador newObj = new Jogador(objDto);
        return jogadorRepo.save(newObj);
    }

    public Jogador update(Long id, JogadorDTO objDto){
        objDto.setId(id);
        Jogador oldObj = findById(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Jogador(objDto);
        return jogadorRepo.save(oldObj);
    }

    public void delete(Long id){
        Jogador obj = findById(id);
        if(obj.getVendas().size()>0){
            throw new DataIntegrityViolationException("Vendedor não pode ser deletado pois possui ordems de venda");
        }
        jogadorRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(JogadorDTO objDto){
        Optional<Jogador> obj = jogadorRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já está cadastrdo no sistema");
        }

        Optional<Jogador> obj2 = jogadorRepo.findByEmail(objDto.getEmail());
        if(obj2.isPresent() && obj2.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("E-mail já está cadastrdo no sistema");
        }

    }

}
