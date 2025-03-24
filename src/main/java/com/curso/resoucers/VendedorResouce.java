package com.curso.resoucers;

import com.curso.domains.Vendedor;
import com.curso.domains.dtos.VendedorDTO;
import com.curso.services.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/vendedor")
public class VendedorResouce {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> findAll(){
        return ResponseEntity.ok().body(vendedorService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorDTO> findById(@PathVariable Long id){
        Vendedor obj = this.vendedorService.findById(id);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity<VendedorDTO> findByCpf(@PathVariable String cpf){
        Vendedor obj = this.vendedorService.findByCpf(cpf);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<VendedorDTO> findByEmail(@PathVariable String email){
        Vendedor obj = this.vendedorService.findByEmail(email);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> create(@Valid @RequestBody VendedorDTO objOdto){
        Vendedor newObj = vendedorService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<VendedorDTO> update(@PathVariable Long id, @Valid @RequestBody VendedorDTO objOdto){
        Vendedor Obj = vendedorService.update(id, objOdto);
        return ResponseEntity.ok().body(new VendedorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VendedorDTO> delete(@PathVariable Long id){
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
