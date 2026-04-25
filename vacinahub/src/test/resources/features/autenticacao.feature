# language: pt

Funcionalidade: Autenticação de usuário

  Cenário: Cadastro de usuário válido
    Dado que o sistema de autenticação está iniciado
    Quando cadastro o usuário "João" com email "joao@email.com" e senha "123456"
    Então o resultado deve ser "Cadastro realizado com sucesso"

  Cenário: Login válido
    Dado que o sistema de autenticação está iniciado
    E existe um usuário "João" com email "joao@email.com" e senha "123456"
    Quando faço login com email "joao@email.com" e senha "123456"
    Então o resultado deve ser "Login realizado com sucesso"
