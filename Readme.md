[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/endsalone/backend-challange)

# Introdução

Fiz uma pesquisa de soluções que o ITAU e vi que em seu dia-a-dia o mesmo faz uso do Spring Boot e para manter dentro dos padrões decidi utiliza-lo.

Tomei a decisão de separar as camadas da aplicação em Aplication e Domain. No qual a camada de Application é responsável por manter tudo que for do contexto da aplicação, sendo para este projeto a exception e rota. Já  a camada Domain ficará responsável pela regra de negócio como visto na bibliografia DDD Domain Driven Design de Martin Fowler.

Para extrair o que temos de melhor decidi manter o projeto extremamente enxuto com a menor quantidade possível de libs, assim garantindo sua performance e facilidade de execução do mesmo além de fazer uso somente de libs oficiais como "lombok".

Para as validações decidi criar annotation para um possível reaproveitamento para outras partes do projeto em qualquer Object Value ou DTO.

Busquei manter o código legivel e desaclopado, também busquei retornar como response do request um Array de erros para que o front ou qualquer outro consumidor da API possa fazer uso do mesmo e obter um feedback do que precisamos para que as requisições da senha sejam cumpridas.


# Executando o projeto

Para baixar o projeto o projeto na maquina faça uso do github
```sh
$ git clone git@github.com:endsalone/backend-challenge.git
```

Para executar o projeto basta executar no terminal o seguinte comando dentro da pasta raiz em que o projeto foi baixado:
```sh
$ ./mvnw clean package spring-boot:repackage
$ java -jar target/password-validation-0.0.1-SNAPSHOT.jar
```

Após rodar o projeto para fazer as requisições basta seguir o contrato abaixo
```
POST /api/passwords/validation HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "password": "AbTp9!fok"
}
```

Response de sucesso esperado
```
POST /api/passwords/validation HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Status Code: 200

Response:
true
```

Response de erro esperado
```
POST /api/passwords/validation HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Status Code: 400

Response:
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos um caractere especial",
        "O campo senha deve ser preenchido",
        "A senha deve conter ao menos uma letra minuscula",
        "A senha não deve conter caracteres repetidos",
        "A senha deve conter ao menos uma letra maiúscula"
    ]
}
```

## Cenários (CURL)

### Sucesso

- Todos os requisitos contemplados

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "AbTp9!fok"
}'
```
Response:
```
true
```
### Erro
- Senha vazia

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--data-raw '{}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos um digito",
        "A senha deve conter ao menos 9 caracteres",
        "A senha deve conter ao menos um caractere especial",
        "O campo senha deve ser preenchido",
        "A senha deve conter ao menos uma letra minuscula",
        "A senha não deve conter caracteres repetidos",
        "A senha deve conter ao menos uma letra maiúscula"
    ]
}
```

- Nove ou mais caracteres

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "Ab9!f"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos 9 caracteres"
    ]
}
```


- Ao menos 1 letra minúscula

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "ABCDEFG9!HI"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos uma letra minuscula"
    ]
}
```


- Ao menos 1 letra maiúscula

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "AbTpv!fok"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos uma letra maiúscula"
    ]
}
```

- Ao menos 1 dígito

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "AbTpv!fok"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos um digito"
    ]
}
```

- Ao menos 1 caractere especial

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "AbTpv0fok"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha deve conter ao menos um caractere especial"
    ]
}
```

- Não possuir caracteres repetidos dentro do conjunto

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "AbTpv0!ook"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha não deve conter caracteres repetidos"
    ]
}
```

- Espaços em branco não devem ser considerados como caracteres válidos

Request:
```
curl --location --request POST 'http://localhost:8080/api/passwords/validation' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=B79B8E1AF094E155B7FBBE2A8C4CD79C' \
--data-raw '{
    "password": "AbTpv0! ok"
}'
```

Response:
```
{
    "code": 400,
    "errors": [
        "A senha não deve conter espaços"
    ]
}
```


Todos os testes Unitários e de Integração estão dentro da pasta de test. Optei por criar os testes de integração contemplando todos os possíveis cenários a fim de garantir a integridade do contrato de response.

![N|](https://miro.medium.com/max/256/1*k73wp-nDid53eeQ0RDGvdw.png)


