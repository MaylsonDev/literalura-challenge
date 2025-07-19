# LiterAlura - Aplicação de Gestão de Livros e Autores

## Descrição

O **LiterAlura** é uma aplicação desenvolvida em **Spring Boot** para gerenciar livros e autores, permitindo ao usuário realizar buscas e consultas sobre livros, autores e idiomas. Ela utiliza dados obtidos de uma API externa (Gutendex) para preencher o banco de dados com informações sobre livros, incluindo título, autor, resumo, idiomas, assuntos, etc.

A aplicação permite:

- Buscar livros pelo título.
- Listar livros registrados no sistema.
- Listar autores registrados.
- Listar autores vivos em um determinado ano.
- Listar livros de um determinado idioma.

## Funcionalidades

1. **Buscar livro pelo título**:
   - O usuário pode pesquisar livros através de seu título, e a aplicação busca informações detalhadas de um banco de dados externo (Gutendex).

2. **Listar livros registrados**:
   - Exibe todos os livros que foram registrados na base de dados do sistema.

3. **Listar autores registrados**:
   - Exibe todos os autores que foram registrados na base de dados.

4. **Listar autores vivos em um determinado ano**:
   - O usuário pode consultar autores que estavam vivos em um determinado ano.

5. **Listar livros por idioma**:
   - O usuário pode pesquisar por livros de um idioma específico registrado no sistema.

## Pré-requisitos

- Java 17 ou superior
- Spring Boot
- Banco de dados relacional (PostgreSQL ou outro)
- Dependências de bibliotecas como **Spring Data JPA** e **Spring Boot Web**.

## Tecnologias Usadas

- **Java 17**: Para o desenvolvimento da aplicação.
- **Spring Boot**: Framework para criação de APIs RESTful.
- **Spring Data JPA**: Para integração com o banco de dados relacional.
- **PostgreSQL**: Banco de dados usado para persistência dos dados.
- **Gutendex API**: API externa utilizada para buscar dados de livros.

## Como Rodar a Aplicação

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/LiterAlura.git

2. Configure o Banco de Dados
Crie um banco de dados PostgreSQL e configure no arquivo application.properties ou application.yml:
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true


