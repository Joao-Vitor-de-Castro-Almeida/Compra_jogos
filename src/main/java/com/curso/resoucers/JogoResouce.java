package com.curso.resoucers;

import com.curso.domains.Jogo;
import com.curso.domains.dtos.JogoDTO;
import com.curso.services.JogoService;
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
@RequestMapping(value = "/jogo")
@Tag(name = "Jogo", description = "API para gerenciamento de Jogos")
public class JogoResouce {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    @Operation(summary = "Listar todos os Jogos")
    public ResponseEntity<List<JogoDTO>> findAll(){
        return ResponseEntity.ok().body(jogoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um jogo por id",
            description = "Realizar a busca de um jogo cadastrado por id")
    public ResponseEntity<JogoDTO> findById(@PathVariable Long id){
        Jogo obj = this.jogoService.findById(id);
        return  ResponseEntity.ok().body(new JogoDTO(obj));
    }

    @GetMapping(value = "/codigoBarra/{codigoBarra}")
    @Operation(summary = "Buscar um jogo por codigoBarra",
            description = "Realizar a busca de um jogo cadastrado por codigoBarra")
    public ResponseEntity<JogoDTO> findBycodigoBarra(@PathVariable String codigoBarra){
        Jogo obj = this.jogoService.findBycodigoBarra(codigoBarra);
        return  ResponseEntity.ok().body(new JogoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo jogo",
            description = "Criar um novo jogo com base nos dados fornecidos")
    public ResponseEntity<JogoDTO> create(@Valid @RequestBody JogoDTO dto){
        Jogo livro = jogoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(livro.getIdJogo()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um jogo",
            description = "Altera um jogo existente")
    public ResponseEntity<JogoDTO> update(@PathVariable Long id, @Valid @RequestBody JogoDTO objDto){
        Jogo Obj = jogoService.update(id, objDto);
        return ResponseEntity.ok().body(new JogoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um jogo",
            description = "Remove um jogo apatir do seu id")
    public ResponseEntity<JogoDTO> delete(@PathVariable Long id){
        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
