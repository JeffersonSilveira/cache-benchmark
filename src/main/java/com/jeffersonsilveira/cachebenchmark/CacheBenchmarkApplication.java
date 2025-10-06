package com.jeffersonsilveira.cachebenchmark;


import com.jeffersonsilveira.cachebenchmark.model.Produto;
import com.jeffersonsilveira.cachebenchmark.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class CacheBenchmarkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheBenchmarkApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ProdutoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Produto> produtos = new ArrayList<>();
                for (long i = 1; i <= 10000; i++) {
                    produtos.add(new Produto(i, "Produto " + i, BigDecimal.valueOf(Math.random() * 100)));
                }
                repository.saveAll(produtos);
                System.out.println("✅ 10.000 produtos inseridos no banco.");
            }
        };
    }
}