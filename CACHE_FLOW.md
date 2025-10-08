# Fluxo de Cache para ProdutoService

Este documento descreve o fluxo de cache aplicado no serviço ProdutoService.

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

Qualquer dúvida, contate o Jefferson Silveira.
