package com.curso.resources;

import com.curso.domains.Vendedor;
import com.curso.domains.dtos.VendedorDTO;
import com.curso.services.VendedorService;
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
@RequestMapping(value = "/vendedor")
@Tag(name = "Vendedor", description = "API para gerenciamento de Vendedor")
public class VendedorResouce {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    @Operation(summary = "Listar todos os vendedores")
    public ResponseEntity<List<VendedorDTO>> findAll(){
        return ResponseEntity.ok().body(vendedorService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um vendedor por id",
            description = "Realizar a busca de um vendedor cadastrado por id")
    public ResponseEntity<VendedorDTO> findById(@PathVariable Long id){
        Vendedor obj = this.vendedorService.findById(id);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}")
    @Operation(summary = "Buscar um vendedor por cpf",
            description = "Realizar a busca de um vendedor cadastrado por cpf")
    public ResponseEntity<VendedorDTO> findByCpf(@PathVariable String cpf){
        Vendedor obj = this.vendedorService.findByCpf(cpf);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Buscar um vendedor por email",
            description = "Realizar a busca de um vendedor cadastrado por email")
    public ResponseEntity<VendedorDTO> findByEmail(@PathVariable String email){
        Vendedor obj = this.vendedorService.findByEmail(email);
        return ResponseEntity.ok().body(new VendedorDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo vendedor",
            description = "Criar um novo vendedor com base nos dados fornecidos")
    public ResponseEntity<VendedorDTO> create(@Valid @RequestBody VendedorDTO objOdto){
        Vendedor newObj = vendedorService.create(objOdto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um vendedor",
            description = "Altera um vendedor existente")
    public ResponseEntity<VendedorDTO> update(@PathVariable Long id, @Valid @RequestBody VendedorDTO objOdto){
        Vendedor Obj = vendedorService.update(id, objOdto);
        return ResponseEntity.ok().body(new VendedorDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um vendedor",
            description = "Remove um vendedor apatir do seu id")
    public ResponseEntity<VendedorDTO> delete(@PathVariable Long id){
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
