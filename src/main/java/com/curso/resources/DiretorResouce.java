package com.curso.resources;

import com.curso.domains.Diretor;
import com.curso.domains.dtos.DiretorDTO;
import com.curso.services.DiretorService;
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
@RequestMapping(value = "/diretor")
@Tag(name = "Diretor", description = "API para gerenciamento de Diretores")
public class DiretorResouce {

    @Autowired
    private DiretorService diretorService;

    @GetMapping
    @Operation(summary = "Listar todos os Diretores")
    public ResponseEntity<List<DiretorDTO>> findAll(){
        return ResponseEntity.ok().body(diretorService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um diretor por id",
            description = "Realizar a busca de um diretor cadastrado por id")
    public ResponseEntity<DiretorDTO> findById(@PathVariable int id){
        Diretor obj = this.diretorService.findById(id);
        return  ResponseEntity.ok().body(new DiretorDTO(obj));
    }

    @GetMapping(value = "/CPF/{CPF}")
    @Operation(summary = "Buscar um diretor por cnpj",
            description = "Realizar a busca de um diretor cadastrado por cnpj")
    public ResponseEntity<DiretorDTO> findByCnpj(@PathVariable String CPF){
        Diretor obj = this.diretorService.findByCPF(CPF);
        return  ResponseEntity.ok().body(new DiretorDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo diretor",
            description = "Criar um novo diretor com base nos dados fornecidos")
    public ResponseEntity<DiretorDTO> create(@Valid @RequestBody DiretorDTO dto){
        Diretor autor = diretorService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um diretor",
            description = "Altera um diretor existente")
    public ResponseEntity<DiretorDTO> update(@PathVariable Integer id, @Valid @RequestBody DiretorDTO objDto){
        Diretor Obj = diretorService.update(id, objDto);
        return ResponseEntity.ok().body(new DiretorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um diretor",
            description = "Remove um diretor apatir do seu id")
    public ResponseEntity<DiretorDTO> delete(@PathVariable Integer id){
        diretorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
