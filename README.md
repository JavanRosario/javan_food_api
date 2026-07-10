# JavanFood API

API REST desenvolvida em Spring Boot que simula um sistema de delivery de comida, inspirado no iFood.

## Sobre o Projeto

JavanFood é uma aplicação backend que gerencia restaurantes, cozinhas, cidades, estados, formas de pagamento, grupos de usuários e usuários. O projeto implementa operações CRUD completas com validações, tratamento de exceções e mapeamento de DTOs.

## Tecnologias

- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA / Hibernate**
- **Spring Validation (Jakarta Bean Validation)**
- **MySQL 8.0**
- **Flyway** — versionamento do banco de dados
- **MapStruct 1.6.3** — mapeamento entre entidades e DTOs
- **Lombok**
- **REST Assured** — testes de integração
- **Maven**
- **Docker**

## Endpoints

### Restaurantes `/restaurantes`
```
GET    /restaurantes                          # Lista todos
GET    /restaurantes/{restauranteId}          # Busca por ID
POST   /restaurantes                          # Cadastra novo
PUT    /restaurantes/{restauranteId}          # Atualiza
DELETE /restaurantes/{restauranteId}          # Remove
PUT    /restaurantes/{restauranteId}/ativo    # Ativa restaurante
DELETE /restaurantes/{restauranteId}/ativo    # Desativa restaurante
```

### Cozinhas `/cozinhas`
```
GET    /cozinhas                  # Lista todas
GET    /cozinhas/{cozinhaId}      # Busca por ID
POST   /cozinhas                  # Cadastra nova
PUT    /cozinhas/{cozinhaId}      # Atualiza
DELETE /cozinhas/{cozinhaId}      # Remove
```

### Cidades `/cidades`
```
GET    /cidades                   # Lista todas
GET    /cidades/{cidadeId}        # Busca por ID
POST   /cidades                   # Cadastra nova
PUT    /cidades/{cidadeId}        # Atualiza
DELETE /cidades/{cidadeId}        # Remove
```

### Estados `/estados`
```
GET    /estados                   # Lista todos
GET    /estados/{estadoId}        # Busca por ID
POST   /estados                   # Cadastra novo
PUT    /estados/{estadoId}        # Atualiza
DELETE /estados/{estadoId}        # Remove
```

### Formas de Pagamento `/pagamentos`
```
GET    /pagamentos                    # Lista todas
GET    /pagamentos/{pagamentoId}      # Busca por ID
POST   /pagamentos                    # Cadastra nova
PUT    /pagamentos/{pagamentoId}      # Atualiza
DELETE /pagamentos/{pagamentoId}      # Remove
```

### Grupos `/grupos`
```
GET    /grupos                    # Lista todos
GET    /grupos/{grupoId}          # Busca por ID
POST   /grupos                    # Cadastra novo
PUT    /grupos/{grupoId}          # Atualiza
DELETE /grupos/{grupoId}          # Remove
```

### Usuários `/usuarios`
```
GET    /usuarios                          # Lista todos (sem senha)
GET    /usuarios/{usuarioId}              # Busca por ID (sem senha)
POST   /usuarios                          # Cadastra novo
PUT    /usuarios/{usuarioId}              # Atualiza dados (sem senha)
PUT    /usuarios/{usuarioId}/senha        # Altera senha
```

## Arquitetura

```
com.javanfood.javanfood
│
├── api/
│   ├── controllers/          # 7 controllers REST
│   ├── dto/
│   │   ├── request/          # DTOs de entrada com validações
│   │   └── response/         # DTOs de saída
│   ├── exceptionhandler/     # Tratamento global de exceções
│   └── mapper/               # Mappers MapStruct por domínio
│
├── core/
│   ├── jackson/              # Configuração do Jackson
│   └── validation/           # Validações customizadas
│
└── domain/
    ├── model/                # Entidades JPA
    ├── repository/           # Repositories Spring Data
    ├── service/              # Regras de negócio
    ├── exception/            # Exceções de domínio
    └── customRepository/     # Implementações com Specifications
```

## Banco de Dados

As migrations são gerenciadas pelo **Flyway** em `src/main/resources/db/migration/`:

| Versão | Descrição |
|--------|-----------|
| V001 | Criação da tabela `cozinha` |
| V002 | Criação da tabela `cidade` |
| V003 | Criação da tabela `estado` |
| V004 | Criação das tabelas `restaurante`, `produto`, `usuario`, `grupo`, `permissao`, `forma_pagamento` e tabelas de relacionamento |
| V005 | Criação das tabelas `pedido` e `item_pedido` |
| V006 | Adição da coluna `ativo` em `restaurante` |

## Como Rodar

### Pré-requisitos
- Java 17+
- Docker

### Subindo o banco com Docker
```bash
docker-compose up -d
```

O MySQL estará disponível na porta `3306`. As migrations rodam automaticamente ao iniciar a aplicação.

### Iniciando a aplicação
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

---

🚧 **Projeto em Desenvolvimento** 🚧

### Próximas funcionalidades
- Sistema de pedidos
- Autenticação e autorização (Spring Security)
- Gerenciamento de permissões por grupo
- Sistema de avaliações
