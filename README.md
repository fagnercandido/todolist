# TODO List

### Começando pelo começo...
Seguem abaixos os requisitos minimos para rodar a aplicação:

* Java 17
* Maven v3+

### Guia de Utilização
1. Crie um um novo usuário com o seguinte curl:
```shell
curl --location 'localhost:8080/usuarios' \
--header 'Content-Type: application/json' \
--header 'Cookie: Set-Cookie=123456789' \
--data-raw '{
    "email" : "teste@gmail.com",
    "nome": "teste3",
    "senha" : "123"  
}'
```

2. Certo, criaste um novo usuário, agora vamos efetuar o login com ele:
```shell
curl --location 'localhost:8080/autenticacao/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email" : "teste@gmail.com",
    "senha": "123"
}'
```

3. Mas, fez o login, mas vamos fazer o logout:
```shell
curl --location --request POST 'localhost:8080/autenticacao/logout' \
--header 'bezkoder: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0ODUyNjUsImV4cCI6MTcxNjU3MTY2NX0.ig2TvPtjKqF_QfKNASQ6siQEQWUpLVY49JXArXXvPkc' \
```

4. Agora, vamos criar uma nova tarefa:
```shell