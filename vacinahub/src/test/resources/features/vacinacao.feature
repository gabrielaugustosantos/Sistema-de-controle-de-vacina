# language: pt

Funcionalidade: Vacinacao

  Cenario: Cadastro de dependente com sucesso
    Dado que existe um usuario principal para vacinacao
    Quando cadastro o dependente "Ana" nascido em "2020-05-10" com parentesco "Filha"
    Entao o usuario deve ter 1 dependente cadastrado
    E o nome do dependente cadastrado deve ser "Ana"

  Cenario: Calculo de proxima dose valido
    Dado que existe a vacina "Febre Amarela" com 2 doses e intervalo de 120 meses
    E registrei a dose 1 aplicada em "2026-06-07"
    Quando calculo a proxima dose da vacina
    Entao a proxima dose deve ser a dose 2
    E a data da proxima dose deve ser "2036-06-07"
    E o status da proxima dose deve ser "PENDENTE"

  Cenario: Vacina com esquema completo nao gera proxima dose
    Dado que existe a vacina "COVID-19" com 2 doses e intervalo de 6 meses
    E registrei a dose 2 aplicada em "2026-06-07"
    Quando calculo a proxima dose da vacina
    Entao nenhuma proxima dose deve ser agendada
