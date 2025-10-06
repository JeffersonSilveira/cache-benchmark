package com.jeffersonsilveira.cachebenchmark.repository;

import com.jeffersonsilveira.cachebenchmark.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}