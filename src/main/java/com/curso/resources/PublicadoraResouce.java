package com.curso.resources;

import com.curso.domains.Publicadora;
import com.curso.domains.dtos.PublicadoraDTO;
import com.curso.services.PublicadoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/publicadora")
@Tag(name = "Publicadora", description = "API para gerenciamento de Publicadoras")
public class PublicadoraResouce {

    @Autowired
    private PublicadoraService publicadoraService;

    @GetMapping
    @Operation(summary = "Listar todos as publicadores")
    public ResponseEntity<List<PublicadoraDTO>> findAll(){
        return ResponseEntity.ok().body(publicadoraService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar uma publicadora por id",
            description = "Realizar a busca de uma publicadora cadastrado por id")
    public ResponseEntity<PublicadoraDTO> findById(@PathVariable int id){
        Publicadora obj = this.publicadoraService.findById(id);
        return  ResponseEntity.ok().body(new PublicadoraDTO(obj));
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    @Operation(summary = "Buscar uma publicadora por cnpj",
            description = "Realizar a busca de uma publicadora cadastrado por cnpj")
    public ResponseEntity<PublicadoraDTO> findByCnpj(@PathVariable String cnpj){
        Publicadora obj = this.publicadoraService.findByCnpj(cnpj);
        return  ResponseEntity.ok().body(new PublicadoraDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um nova publicadora",
            description = "Criar um nova publicadora com base nos dados fornecidos")
    public ResponseEntity<PublicadoraDTO> create(@Valid @RequestBody PublicadoraDTO dto){
        Publicadora publicadora = publicadoraService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(publicadora.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera uma publicadora",
            description = "Altera uma publicadora existente")
    public ResponseEntity<PublicadoraDTO> update(@PathVariable Integer id, @Valid @RequestBody PublicadoraDTO objDto){
        Publicadora Obj = publicadoraService.update(id, objDto);
        return ResponseEntity.ok().body(new PublicadoraDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar uma publicadora",
            description = "Remove uma publicadora apatir do seu id")
    public ResponseEntity<PublicadoraDTO> delete(@PathVariable Integer id){
        publicadoraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
