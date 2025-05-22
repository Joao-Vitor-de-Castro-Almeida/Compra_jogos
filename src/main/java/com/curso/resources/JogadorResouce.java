package com.curso.resources;

import com.curso.domains.Jogador;
import com.curso.domains.dtos.JogadorDTO;
import com.curso.services.JogadorService;
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
@RequestMapping(value = "/jogador")
@Tag(name = "Jogador", description = "API para gerenciamento de Jogador")
public class JogadorResouce {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    @Operation(summary = "Listar todos os Jogadores",
            description = "Retorna uma lista com todos os Jogadores cadastrados")
    public ResponseEntity<List<JogadorDTO>> findAll(){
        return ResponseEntity.ok().body(jogadorService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um jogador por id",
            description = "Realizar a busca de um jogador cadastrado por id")
    public ResponseEntity<JogadorDTO> findById(@PathVariable Long id){
        Jogador obj = this.jogadorService.findById(id);
        return ResponseEntity.ok().body(new JogadorDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Buscar um jogador por cpf",
            description = "Realizar a busca de um jogador cadastrado por cpf")
    public ResponseEntity<JogadorDTO> findByCpf(@PathVariable String cpf){
        Jogador obj = this.jogadorService.findByCpf(cpf);
        return ResponseEntity.ok().body(new JogadorDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Buscar um jogador por email",
            description = "Realizar a busca de um jogador cadastrado por email")
    public ResponseEntity<JogadorDTO> findByEmail(@PathVariable String email){
        Jogador obj = this.jogadorService.findByEmail(email);
        return ResponseEntity.ok().body(new JogadorDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo jogador",
            description = "Criar um novo jogador com base nos dados fornecidos")
    public ResponseEntity<JogadorDTO> create(@Valid @RequestBody JogadorDTO objOdto){
        Jogador newObj = jogadorService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um jogador",
            description = "Altera um jogador existente")
    public ResponseEntity<JogadorDTO> update(@PathVariable Long id, @Valid @RequestBody JogadorDTO objOdto){
        Jogador Obj = jogadorService.update(id, objOdto);
        return ResponseEntity.ok().body(new JogadorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um jogador",
            description = "Remove um jogador apatir do seu id")
    public ResponseEntity<JogadorDTO> delete(@PathVariable Long id){
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
