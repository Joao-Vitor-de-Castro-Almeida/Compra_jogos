package com.curso.resoucers;

import com.curso.domains.Publicadora;
import com.curso.domains.dtos.PublicadoraDTO;
import com.curso.services.PublicadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/publicadora")
public class PublicadoraResouce {

    @Autowired
    private PublicadoraService publicadoraService;

    @GetMapping
    public ResponseEntity<List<PublicadoraDTO>> findAll(){
        return ResponseEntity.ok().body(publicadoraService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublicadoraDTO> findById(@PathVariable int id){
        Publicadora obj = this.publicadoraService.findById(id);
        return  ResponseEntity.ok().body(new PublicadoraDTO(obj));
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<PublicadoraDTO> findByCnpj(@PathVariable String cnpj){
        Publicadora obj = this.publicadoraService.findByCnpj(cnpj);
        return  ResponseEntity.ok().body(new PublicadoraDTO(obj));
    }

    @PostMapping
    public ResponseEntity<PublicadoraDTO> create(@Valid @RequestBody PublicadoraDTO dto){
        Publicadora publicadora = publicadoraService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(publicadora.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PublicadoraDTO> update(@PathVariable Integer id, @Valid @RequestBody PublicadoraDTO objDto){
        Publicadora Obj = publicadoraService.update(id, objDto);
        return ResponseEntity.ok().body(new PublicadoraDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PublicadoraDTO> delete(@PathVariable Integer id){
        publicadoraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
