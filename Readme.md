**Implementação do Desafio Java**
----
  Este repositório contém o código-fonte da API REST relativa ao desafio java (https://github.com/concretesolutions/desafio-java)

  A seguir são apresentados os endpoints implementados



sss

**CADASTRO**

  No cadastro a API retorna, em caso de sucesso, além do usuário cadastrado, um token com validade de 30 minutos

* **URL**

  http://HOST/cadastro

* **Método:**

  `POST`

* **Dados da requisição**

No body da requisição o payload precisa ser um json no seguinte formato:
```json
{
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ]
}
```

* **Resposta em caso de sucesso:**

  Em caso de sucesso, a API retorna o seguinte:

  * **Código:** 200 <br />
    **Conteúdo:**
```json
{
    "id": 1,
    "name": "João da Silva",
    "email": "joao@silva.org",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ],
    "created": "03/06/2016 14:00:00",
    "modified": "03/06/2016 14:00:00",
    "last_login": "03/06/2016 14:00:00",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvdGVzdGVAc2lsdmEub3JnLmJyIiwiZXhwIjoxNDY0NzIxNDA1fQ.bR3OnRGEy9uS0WzZpVRSSKte4sIKuqSbjfAgf4j7CmRXRz-GmCO4oVx1dR_NXwS80QvBo3akNmHx6ExxPAW0Lg"
}
```

* **Resposta em caso de erro:**

  No caso de já haver um cadastro com o mesmo e-mail, a API retorna:

  * **Código:** 409 CONFLICT <br />
    **Conteúdo:**

```json
{
    "mensagem": "E-mail já existente"
}
```


**LOGIN**

  No login a API retorna um token em caso de sucesso, que tem validade de 30 minutos

* **URL**

  http://HOST/login

* **Método:**

  `POST`

* **Dados da requisição**

  No body da requisição o payload precisa ser um json no seguinte formato:
```json
{
    "email": "joao@silva.org",
    "password": "hunter2"
}
```

* **Resposta em caso de sucesso:**

  Em caso de sucesso, a API retorna o seguinte:

  * **Código:** 200 <br />
    **Conteúdo:**
```json
{
    "id": 1,
    "name": "João da Silva",
    "email": "joao@silva.org",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ],
    "created": "03/06/2016 14:00:00",
    "modified": "03/06/2016 14:00:00",
    "last_login": "03/06/2016 15:00:00",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvdGVzdGVAc2lsdmEub3JnLmJyIiwiZXhwIjoxNDY0NzIxNDA1fQ.bR3OnRGEy9uS0WzZpVRSSKte4sIKuqSbjfAgf4j7CmRXRz-GmCO4oVx1dR_NXwS80QvBo3akNmHx6ExxPAW0Lg"
}
```

* **Resposta em caso de erro:**

  No caso de já haver alguma divergência de usuário/senha, a API retorna o seguinte:

  * **Código:** 401 UNAUTHORIZED <br />
    **Conteúdo:**

```json
{
    "mensagem": "Usuário e/ou senha inválidos"
}
```




**PERFIL**

* **URL**

  http://HOST/perfil/{id}

* **Método:**

  `GET`

* **Parâmetros da requisição**

  É necessário passar um id numérico na url da requisição

* **Dados da requisição**

  No HEADER da requisição é necessário conter o seguinte parâmetro: <br />
  Authorization: Beaker <strong>eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvdGVzdGVAc2lsdmEub3JnLmJyIiwiZXhwIjoxNDY0NzIxNDA1fQ.bR3OnRGEy9uS0WzZpVRSSKte4sIKuqSbjfAgf4j7CmRXRz-GmCO4oVx1dR_NXwS80QvBo3akNmHx6ExxPAW0Lg</strong>

  Sendo que <strong>"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvdGVzdGVAc2lsdmEub3JnLmJyIiwiZXhwIjoxNDY0NzIxNDA1fQ.bR3OnRGEy9uS0WzZpVRSSKte4sIKuqSbjfAgf4j7CmRXRz-GmCO4oVx1dR_NXwS80QvBo3akNmHx6ExxPAW0Lg"</strong> representa o token retornado pelo CADASTRO ou pelo LOGIN

* **Resposta em caso de sucesso:**

  Em caso de sucesso, a API retorna o seguinte:

  * **Código:** 200 <br />
    **Conteúdo:**
```json
{
    "id": 1,
    "name": "João da Silva",
    "email": "joao@silva.org",
    "phones": [
        {
            "number": "987654321",
            "ddd": "21"
        }
    ],
    "created": "03/06/2016 14:00:00",
    "modified": "03/06/2016 14:00:00",
    "last_login": "03/06/2016 15:00:00",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvdGVzdGVAc2lsdmEub3JnLmJyIiwiZXhwIjoxNDY0NzIxNDA1fQ.bR3OnRGEy9uS0WzZpVRSSKte4sIKuqSbjfAgf4j7CmRXRz-GmCO4oVx1dR_NXwS80QvBo3akNmHx6ExxPAW0Lg"
}
```

* **Resposta em caso de erro:**

Caso haja algum problema com o token (inexistente, expirado, assinatura incorreta), a API retorna o seguinte:

* **Código:** 401 UNAUTHORIZED <br />
**Conteúdo:**

```json
{
    "mensagem": "Não autorizado"
}
```
