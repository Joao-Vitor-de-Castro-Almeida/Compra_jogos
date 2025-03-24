package com.curso.resoucers;

import com.curso.domains.Jogador;
import com.curso.domains.dtos.JogadorDTO;
import com.curso.domains.dtos.JogoDTO;
import com.curso.services.JogadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/jogador")
public class JogadorResouce {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<List<JogadorDTO>> findAll(){
        return ResponseEntity.ok().body(jogadorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JogadorDTO> findById(@PathVariable Long id){
        Jogador obj = this.jogadorService.findById(id);
        return ResponseEntity.ok().body(new JogadorDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<JogadorDTO> findByCpf(@PathVariable String cpf){
        Jogador obj = this.jogadorService.findByCpf(cpf);
        return ResponseEntity.ok().body(new JogadorDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<JogadorDTO> findByEmail(@PathVariable String email){
        Jogador obj = this.jogadorService.findByEmail(email);
        return ResponseEntity.ok().body(new JogadorDTO(obj));
    }

    @PostMapping
    public ResponseEntity<JogadorDTO> create(@Valid @RequestBody JogadorDTO objOdto){
        Jogador newObj = jogadorService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<JogadorDTO> update(@PathVariable Long id, @Valid @RequestBody JogadorDTO objOdto){
        Jogador Obj = jogadorService.update(id, objOdto);
        return ResponseEntity.ok().body(new JogadorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<JogadorDTO> delete(@PathVariable Long id){
        jogadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
