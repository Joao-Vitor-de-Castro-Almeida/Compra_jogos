package com.curso.resources;

import com.curso.domains.Venda;
import com.curso.domains.dtos.VendaDTO;
import com.curso.services.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Venda", description = "API para gerenciamento de Venda")
public class VendaResouce {

    @Autowired
    private VendaService vendaService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar uma venda por id",
            description = "Realizar a busca de uma venda cadastrado por id")
    public ResponseEntity<VendaDTO> findById(@PathVariable UUID id){
        Venda obj = this.vendaService.findById(id);
        return ResponseEntity.ok().body(new VendaDTO(obj));
    }

    @GetMapping
    @Operation(summary = "Listar todos as vendas",
            description = "Retorna uma lista com todas as Vendas cadastrados")
    public ResponseEntity<List<VendaDTO>> findAll(){
        return ResponseEntity.ok().body(vendaService.findAll());
    }

    @PostMapping
    @Operation(summary = "Criar um nova venda",
            description = "Criar um nova venda com base nos dados fornecidos")
    public ResponseEntity<VendaDTO> create(@Valid @RequestBody VendaDTO objDto){
        Venda newObj = vendaService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera uma venda",
            description = "Altera uma venda existente")
    public ResponseEntity<VendaDTO> update(@PathVariable UUID id,@Valid @RequestBody VendaDTO objDto){
        Venda Obj = vendaService.update(id, objDto);
        return ResponseEntity.ok().body(new VendaDTO(Obj));
    }

}
