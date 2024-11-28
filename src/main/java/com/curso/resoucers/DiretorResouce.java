package com.curso.resoucers;

import com.curso.domains.Diretor;
import com.curso.domains.Publicadora;
import com.curso.domains.dtos.DiretorDTO;
import com.curso.domains.dtos.PublicadoraDTO;
import com.curso.services.DiretorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/diretor")
public class DiretorResouce {

    @Autowired
    private DiretorService diretorService;

    @GetMapping
    public ResponseEntity<List<DiretorDTO>> findAll(){
        return ResponseEntity.ok().body(diretorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DiretorDTO> findById(@PathVariable int id){
        Diretor obj = this.diretorService.findById(id);
        return  ResponseEntity.ok().body(new DiretorDTO(obj));
    }

    @GetMapping(value = "/CPF/{CPF}")
    public ResponseEntity<DiretorDTO> findByCnpj(@PathVariable String CPF){
        Diretor obj = this.diretorService.findByCPF(CPF);
        return  ResponseEntity.ok().body(new DiretorDTO(obj));
    }

    @PostMapping
    public ResponseEntity<DiretorDTO> create(@Valid @RequestBody DiretorDTO dto){
        Diretor autor = diretorService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DiretorDTO> update(@PathVariable Integer id, @Valid @RequestBody DiretorDTO objDto){
        Diretor Obj = diretorService.update(id, objDto);
        return ResponseEntity.ok().body(new DiretorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DiretorDTO> delete(@PathVariable Integer id){
        diretorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
