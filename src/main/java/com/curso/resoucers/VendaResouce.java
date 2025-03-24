package com.curso.resoucers;

import com.curso.domains.Venda;
import com.curso.domains.dtos.VendaDTO;
import com.curso.services.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/venda")
public class VendaResouce {

    @Autowired
    private VendaService vendaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable UUID id){
        Venda obj = this.vendaService.findById(id);
        return ResponseEntity.ok().body(new VendaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll(){
        return ResponseEntity.ok().body(vendaService.findAll());
    }

    @PostMapping
    public ResponseEntity<VendaDTO> create(@Valid @RequestBody VendaDTO objDto){
        Venda newObj = vendaService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<VendaDTO> update(@PathVariable UUID id,@Valid @RequestBody VendaDTO objDto){
        Venda Obj = vendaService.update(id, objDto);
        return ResponseEntity.ok().body(new VendaDTO(Obj));
    }

}
