# Twitter Clone API

Este é um projeto de **API REST** para um sistema de criação de Tweets e gerenciamento de usuários, inspirado no Twitter. A API permite o registro e autenticação de usuários, além de funcionalidades como criação, leitura e exclusão de Tweets. O projeto é construído utilizando **Java Spring Boot** com autenticação baseada em **JWT (JSON Web Token)**.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security** (para autenticação e autorização com JWT)
- **PostgreSQL** (banco de dados relacional)
- **Swagger** (documentação da API)
- **OAuth2 com JWT**
- **Mockito** (para testes unitários)

## Funcionalidades

- **Registro de usuários**: Usuários podem se registrar fornecendo um nickname, nome, email e senha.
- **Login de usuários**: Usuários autenticados recebem um token JWT para acessar rotas protegidas.
- **Criação de Tweets**: Usuários autenticados podem criar Tweets.
- **Listagem de Tweets**: Qualquer usuário autenticado pode listar os Tweets.
- **Exclusão de Tweets**: Usuários podem deletar seus próprios Tweets.

## Pré-requisitos

- **Java 21**
- **Maven**
- **PostgreSQL** (banco de dados rodando localmente ou em container)

## Configuração do Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/usuario/projeto-twitter-clone.git
   cd projeto-twitter-clone

2. Configure o banco de dados PostgreSQL. Altere as propriedades de conexão no arquivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/twitter_clone
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

3. Gere as chaves RSA para o JWT e configure no application.properties:
   jwt.public.key=path_to_public_key
   jwt.private.key=path_to_private_key

4. Execute a aplicação:

  mvn spring-boot:run

Documentação da API
A documentação da API é gerada automaticamente pelo Swagger e pode ser acessada através do seguinte link:

http://localhost:8080/swagger-ui/index.html

Endpoints Principais
Autenticação
POST /register: Registra um novo usuário.
POST /login: Realiza login com Basic Authentication e retorna o token JWT.
Tweets
POST /tweet: Cria um novo tweet (requer Bearer Token).
GET /tweet: Retorna todos os tweets (requer Bearer Token).
DELETE /tweet/{tweet_id}/{user_id}: Deleta um tweet por ID (requer Bearer Token).
Autenticação e Autorização
A API utiliza JWT (Bearer Token) para autenticação. Após realizar o login, você receberá um token JWT, que deverá ser incluído no cabeçalho Authorization de todas as requisições subsequentes.

Exemplo de cabeçalho:

Authorization: Bearer <seu-token-jwt>

Exemplo de Fluxo de Autenticação
Login: Use o endpoint /login com Basic Auth (fornecendo username e password) para obter o token JWT.
Autorize: Utilize o token JWT retornado em todas as requisições subsequentes nos endpoints protegidos.