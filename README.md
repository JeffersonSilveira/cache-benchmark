# cache-benchmark

Projeto Spring Boot para microserviço com cache Redis e benchmark simples.

## Visão Geral

Este projeto demonstra como usar o Redis como cache para acelerar o acesso a dados em um microserviço Spring Boot.  
Utiliza um banco em memória H2 para armazenar 10.000 produtos. O cache é ativado para reduzir latência em leituras repetidas.

---

## Estrutura de Pacotes

- `controller` — Endpoints REST
- `service` — Lógica de negócio e cache com `@Cacheable`
- `repository` — Persistência JPA
- `model` — Entidades JPA
- `config` — Configurações específicas (ex: Redis)

---

## Tecnologias

- Java 17
- Spring Boot 3.1.2
- Spring Data JPA (H2 in-memory)
- Spring Cache + Redis
- Docker (Redis via docker-compose)
- Lombok

---

## Requisitos

- Docker instalado (para rodar Redis via docker-compose)
- Maven 3.8+
- Java 17+

---

## Rodando o projeto

### 1. Rodar Redis via Docker

```bash
docker-compose up -d
```

### 2. Buildar projeto

```bash
mvn clean package
```

### 3. Rodar aplicação

```bash
java -jar target/cache-benchmark-1.0.0.jar
```

###  ou para desenvolvimento

```bash
mvn spring-boot:run
```

### Testando Cache

```bash
http://localhost:8080/produtos/{id}
```

A primeira chamada para um id específico demora ~100ms (simulação de latência).

Chamadas subsequentes são instantâneas, pois os dados são servidos do cache Redis.

---

# Fluxo de Cache para ProdutoService

Este documento descreve o fluxo de cache aplicado no serviço `ProdutoService`.

## Métodos cacheados

- `getProdutoById(Long id)`
    - Usa `@Cacheable` para armazenar produtos no cache Redis.
    - A primeira chamada busca no banco e armazena no cache.
    - Chamadas subsequentes retornam do cache.

- `updateProduto(Produto produto)`
    - Usa `@CachePut` para atualizar o cache após salvar no banco.

- `deleteProduto(Long id)`
    - Usa `@CacheEvict` para remover o produto do cache após remoção no banco.

## Configuração do Redis Cache

- TTL configurado para 10 minutos via `RedisCacheConfig`.
- Logs adicionados para monitorar chamadas ao cache.

## Benefícios

- Redução de latência no acesso aos dados.
- Diminuição da carga no banco de dados.
- Consistência entre cache e banco através de update/delete.

## Requisitos

- Redis rodando na porta 6379.
- Spring Boot com dependências de cache e Redis configuradas.

---
### Bonus: Já estava  esquecendo, acesse via navegador a url abaixo

```bash
http://localhost:8080/actuator/prometheus
```

### Procure por
```nginx
produto_service_calls_total
```


### Acessar
- Prometheus: http://localhost:9090
- -Grafana: http://localhost:3000

### No Grafana
- Adicione o Prometheus como data source(http://prometheus:9090)
- Crie dashboard para produto_service_calls_total, jvm_memory_used_bytes.
