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