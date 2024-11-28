package com.curso.resoucers;

import com.curso.domains.Jogo;
import com.curso.domains.dtos.JogoDTO;
import com.curso.services.JogoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/jogo")
public class JogoResouce {

    @Autowired
    private JogoService jogoService;

    @GetMapping
    public ResponseEntity<List<JogoDTO>> findAll(){
        return ResponseEntity.ok().body(jogoService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<JogoDTO> findById(@PathVariable Long id){
        Jogo obj = this.jogoService.findById(id);
        return  ResponseEntity.ok().body(new JogoDTO(obj));
    }

    @GetMapping(value = "/codigoBarra/{codigoBarra}")
    public ResponseEntity<JogoDTO> findBycodigoBarra(@PathVariable String codigoBarra){
        Jogo obj = this.jogoService.findBycodigoBarra(codigoBarra);
        return  ResponseEntity.ok().body(new JogoDTO(obj));
    }

    @PostMapping
    public ResponseEntity<JogoDTO> create(@Valid @RequestBody JogoDTO dto){
        Jogo livro = jogoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(livro.getIdJogo()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<JogoDTO> update(@PathVariable Long id, @Valid @RequestBody JogoDTO objDto){
        Jogo Obj = jogoService.update(id, objDto);
        return ResponseEntity.ok().body(new JogoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<JogoDTO> delete(@PathVariable Long id){
        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
