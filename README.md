# TODO List

### Começando pelo começo...
Seguem abaixo os requisitos minimos para rodar a aplicação:

* Java 17
* Maven v3+

### Guia de Utilização
0, Antes de tudo, vamos rodar a aplicação ou execute a classe principal `TodoListApplication` ou execute o seguinte comando no terminal:
```shell
mvn spring-boot:run
```

1. Crie um um novo usuário com o seguinte curl:
```shell
curl -v --location 'localhost:8080/usuarios' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email" : "teste@gmail.com",
    "nome": "teste3",
    "senha" : "123"  
}'
```

2. Certo, criaste um novo usuário, agora vamos efetuar o login com ele:
```shell
curl -v --location 'localhost:8080/autenticacao/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email" : "teste@gmail.com",
    "senha": "123"
}'
```

3. Mas, fez o login, mas vamos fazer o logout passando no header o token gerado no login
```shell
curl -v --location --request POST 'localhost:8080/autenticacao/logout' \
--header 'Cookie: todolist=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0OTc1MTcsImV4cCI6MTcxNjU4MzkxN30.6icVx9CaF_4gf1vJQeAsYO9gnBlZRep7Y3L_kstH6K8'
```

4. Agora, vamos criar uma nova tarefa:
```shell
curl -v --location 'localhost:8080/tarefas' \
--header 'Content-Type: application/json' \
--header 'Cookie: todolist=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0OTc1MTcsImV4cCI6MTcxNjU4MzkxN30.6icVx9CaF_4gf1vJQeAsYO9gnBlZRep7Y3L_kstH6K8' \
--data-raw '{
    "titulo" : "teste",
    "descricao" : "123",
    "dataCriacao" : "2024-05-23T12:34:56.789Z",
    "dataAtualizacao" : "2024-05-23T12:34:56.789Z",
    "status" : "PENDENTE" 
}'
```

5. Para chamar todas as tarefas:
```shell
curl -v --location 'localhost:8080/tarefas' \
--header 'Cookie: todolist=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0OTc1MTcsImV4cCI6MTcxNjU4MzkxN30.6icVx9CaF_4gf1vJQeAsYO9gnBlZRep7Y3L_kstH6K8'
```

6. Para chamar uma tarefa específica:
```shell
curl -v --location 'localhost:8080/tarefas/3' \
--header 'Cookie: todolist=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0OTc1MTcsImV4cCI6MTcxNjU4MzkxN30.6icVx9CaF_4gf1vJQeAsYO9gnBlZRep7Y3L_kstH6K8'
```

7. Para remover uma tarefa:
```shell
curl -v --location --request DELETE 'localhost:8080/tarefas/1' \                                                                                                                                                                                                                                                                                                             ─╯
--header 'Cookie: todolist=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0OTc1MTcsImV4cCI6MTcxNjU4MzkxN30.6icVx9CaF_4gf1vJQeAsYO9gnBlZRep7Y3L_kstH6K8'
```

8. Para atualizar uma tarefa:
```shell   
curl -v --location --request PUT 'localhost:8080/tarefas/2' \                                                                                                                                                                                                                                                                                                                ─╯
--header 'Content-Type: application/json' \                                                                                                                                 
--header 'Cookie: todolist=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZUBnbWFpbC5jb20iLCJpYXQiOjE3MTY0OTc1MTcsImV4cCI6MTcxNjU4MzkxN30.6icVx9CaF_4gf1vJQeAsYO9gnBlZRep7Y3L_kstH6K8' \
--data-raw '{
    "titulo" : "Tarefa de Teste",
    "descricao" : "Uma bela descrição",
    "dataCriacao" : "2024-05-23T12:34:56.789Z",
    "dataAtualizacao" : "2024-05-23T12:34:56.789Z",
    "status" : "PENDENTE" 
}'
```

### Pontos de Melhoria
* o logout não está invalidando o token corretamente, pois como é por cookies, isso depende da invalidação.
* inicialmente foi cogitado usar o BasicAuth, entretanto, com os métodos deprecated foi usado do JWT, o acabou por acrescentar um pouco mais de complexidade.