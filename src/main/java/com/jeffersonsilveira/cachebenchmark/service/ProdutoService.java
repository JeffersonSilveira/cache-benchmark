package com.jeffersonsilveira.cachebenchmark.service;

import com.jeffersonsilveira.cachebenchmark.exception.ProdutoNotFoundException;
import com.jeffersonsilveira.cachebenchmark.model.Produto;
import com.jeffersonsilveira.cachebenchmark.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    @Cacheable(value = "produto", key = "#id")
    public Produto getProdutoById(Long id) {
        log.info("Buscando produto no banco com id: {}", id);
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

    @CachePut(value = "produto", key = "#produto.id")
    public Produto updateProduto(Produto produto) {
        log.info("Atualizando produto no banco e cache com id: {}", produto.getId());
        if (!repository.existsById(produto.getId())) {
            throw new ProdutoNotFoundException(produto.getId());
        }
        return repository.save(produto);
    }

    @CacheEvict(value = "produto", key = "#id")
    public void deleteProduto(Long id) {
        log.info("Removendo produto do banco e cache com id: {}", id);
        if (!repository.existsById(id)) {
            throw new ProdutoNotFoundException(id);
        }
        repository.deleteById(id);
    }
}