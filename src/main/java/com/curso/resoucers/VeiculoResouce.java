package com.curso.resoucers;

import com.curso.domains.Veiculo;
import com.curso.domains.dtos.VeiculoDTO;
import com.curso.services.VeiculoService;
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
@RequestMapping(value = "/veiculo")
@Tag(name = "Veiculo", description = "API para gerenciamento de Veiculo")
public class VeiculoResouce {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    @Operation(summary = "Listar todos os veiculos")
    public ResponseEntity<List<VeiculoDTO>> findAll(){
        return ResponseEntity.ok().body(veiculoService.findAll());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar um veiculo por id",
            description = "Realizar a busca de um veiculo cadastrado por id")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id){
        Veiculo obj = this.veiculoService.findById(id);
        return  ResponseEntity.ok().body(new VeiculoDTO(obj));
    }

    @GetMapping(value = "/Cpf/{Cpf}")
    @Operation(summary = "Buscar um veiculo por Cpf",
            description = "Realizar a busca de um veiculo cadastrado por Cpf")
    public ResponseEntity<VeiculoDTO> findByCPF(@PathVariable String Cpf){
        Veiculo obj = this.veiculoService.findByCPF(Cpf);
        return  ResponseEntity.ok().body(new VeiculoDTO(obj));
    }

    @PostMapping
    @Operation(summary = "Criar um novo veiculo",
            description = "Criar um novo veiculo com base nos dados fornecidos")
    public ResponseEntity<VeiculoDTO> create(@Valid @RequestBody VeiculoDTO dto){
        Veiculo veiculo = veiculoService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera um veiculo",
            description = "Altera um veiculo existente")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @Valid @RequestBody VeiculoDTO objDto){
        Veiculo Obj = veiculoService.update(id, objDto);
        return ResponseEntity.ok().body(new VeiculoDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um veiculo",
            description = "Remove um veiculo apatir do seu id")
    public ResponseEntity<VeiculoDTO> delete(@PathVariable Long id){
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
