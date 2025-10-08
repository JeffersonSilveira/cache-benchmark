package com.jeffersonsilveira.cachebenchmark.service;

import com.jeffersonsilveira.cachebenchmark.exception.ProdutoNotFoundException;
import com.jeffersonsilveira.cachebenchmark.model.Produto;
import com.jeffersonsilveira.cachebenchmark.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    @Cacheable(value = "produto", key = "#id")
    public Produto getProdutoById(Long id) {
        simulateDelay();
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    private void simulateDelay() {
        try {
            Thread.sleep(100); // simula latência do banco
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}