package com.curso.services;

import com.curso.domains.Jogador;
import com.curso.domains.Venda;
import com.curso.domains.Vendedor;
import com.curso.domains.dtos.VendaDTO;
import com.curso.domains.dtos.VendedorDTO;
import com.curso.domains.enums.Edicao;
import com.curso.domains.enums.FormaPagameno;
import com.curso.repositories.VendaRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepo;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private JogadorService jogadorService;

    public Venda findById(UUID id){
        Optional<Venda> obj = vendaRepo.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Chamado não encontrado! id:" + id));
    }

    public List<VendaDTO> findAll(){
        return vendaRepo.findAll().stream()
                .map(obj -> new VendaDTO(obj)).collect(Collectors.toList());
    }

    private Venda newVenda(VendaDTO obj){
        Vendedor vendedor = vendedorService.findById(obj.getVendedor());
        Jogador jogador = jogadorService.findById(obj.getJogador());

        Venda os = new Venda();
        if(obj.getId() != null){
            os.setId(obj.getId());
        }

        os.setVendedor(vendedor);
        os.setJogador(jogador);
        os.setEdicao(Edicao.toEnum(obj.getEdicao()));
        os.setFormaPagameno(FormaPagameno.toEnum(obj.getFormaPagameno()));
        os.setNomejogo(obj.getNomejogo());
        os.setDescricao(obj.getDescricao());
        return os;
    }

    public Venda create(VendaDTO objDto){
        return vendaRepo.save(newVenda(objDto));
    }

    public Venda update(UUID id, VendaDTO objDto){
        objDto.setId(id);
        Venda oldObj = findById(id);
        oldObj = newVenda(objDto);
        return vendaRepo.save(oldObj);
    }

}
