package com.jeffersonsilveira.cachebenchmark.controller;

import com.jeffersonsilveira.cachebenchmark.model.Produto;
import com.jeffersonsilveira.cachebenchmark.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = service.getProdutoById(id);
        return ResponseEntity.ok(produto);
    }
}