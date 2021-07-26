# language: pt
Funcionalidade: Gerenciamento de um usuario
  Algum context de negócio
  Histórias do Jira
  Qualquer coisa que faça sentido pro negócio

  Cenario: Cria um usuario na loja
    Quando faço um POST para /v3/user com os seguinte valores:
      | id         | 10            |
      | username   | miu           |
      | firstName  | Miu           |
      | lastName   | Santos        |
      | email      | miu@email.com |
      | password   | 12345         |
      | phone      | 12345         |
      | userStatus | 1             |
    Então quando faço um GET para /v3/user/miu, o usuário criado é retornado

  Cenario: Cria usuario na loja refletindo o negócio
    Quando crio um usuário
    Então o usuário é salvo no sistema