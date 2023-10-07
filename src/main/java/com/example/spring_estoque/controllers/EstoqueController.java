package com.example.spring_estoque.controllers;

import com.example.spring_estoque.dtos.EstoqueRecordDto;
import com.example.spring_estoque.models.EstoqueModel;
import com.example.spring_estoque.repositories.EstoqueRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EstoqueController {

    @Autowired
    EstoqueRepository estoqueRepository;

    @PostMapping("/estoque")
    public ResponseEntity<EstoqueModel> salvarEstoque(@RequestBody @Valid EstoqueRecordDto estoqueRecordDto){
        var estoqueModel = new EstoqueModel();
        BeanUtils.copyProperties(estoqueRecordDto,estoqueModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueRepository.save(estoqueModel));
    }
    @GetMapping("/estoque")
    public ResponseEntity<List<EstoqueModel>> getAllEstoques(){
        List<EstoqueModel> productsList = estoqueRepository.findAll();
        if (!productsList.isEmpty()){
            for (EstoqueModel product : productsList){
                product.add(linkTo(methodOn(EstoqueController.class).getOneEstoque(product.getId_estoque())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }
    @GetMapping("/estoque/{id}")
    public ResponseEntity<Object> getOneEstoque(@PathVariable(value="id") UUID id){
        Optional<EstoqueModel> productO = estoqueRepository.findById(id);
        if (productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado");
        }
        productO.get().add(linkTo(methodOn(EstoqueController.class).getAllEstoques()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @PutMapping("/estoque/{id}")
    public ResponseEntity<Object> updateEstoque(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid EstoqueRecordDto estoqueRecordDto){
        Optional<EstoqueModel> estoque0 = estoqueRepository.findById(id);
        if (estoque0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado.");
        }
        var estoqueModel = estoque0.get();
        BeanUtils.copyProperties(estoqueRecordDto, estoqueModel);
        return ResponseEntity.status(HttpStatus.OK).body(estoqueRepository.save(estoqueModel));
    }
    @DeleteMapping("/estoque/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable (value = "id") UUID id){
        Optional<EstoqueModel> estoque0 = estoqueRepository.findById(id);
        if (estoque0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque não encontrado");
        }
        estoqueRepository.delete(estoque0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Estoque deletado com sucesso");
    }
}
