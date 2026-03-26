# 💉 VacinApp - Gestão de Calendário Vacinal

**Resumo do Projeto:** O VacinApp é uma aplicação focada em gerenciar o calendário vacinal de forma digital. O sistema permite o cadastro de usuários, disponibiliza um catálogo oficial de vacinas, registra doses aplicadas e calcula automaticamente a data de retorno para novas doses, ajudando o usuário a manter sua imunização em dia.

## 🎯 Problema e Público-Alvo
* **Problema que resolve:** A perda do cartão físico e o esquecimento do esquema vacinal, que frequentemente gera atrasos na imunização devido à confusão sobre intervalos de doses.
* **Público-alvo:** Jovens adultos, pais e responsáveis que precisam centralizar e gerenciar o histórico de vacinas próprio e de seus dependentes de forma segura.

## ✨ Funcionalidades
1. **Gestão de Usuários:** Cadastro e validação de perfis.
2. **Catálogo de Vacinas:** Consulta a uma lista padronizada de vacinas oficiais e seus intervalos.
3. **Registro de Imunização:** Cadastro de doses tomadas vinculadas ao catálogo oficial.
4. **Cálculo de Próxima Dose (Regra Central):** Projeção automática da data da próxima dose com base no histórico e nas regras de intervalo da vacina.
5. **Localização:** Busca de pontos de vacinação (UBS) próximos.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Gerenciador de Dependências:** Maven
* **Testes Automatizados:** JUnit 5 (Unitários) e Cucumber (BDD)
* **Arquitetura:** Camadas (Domain, Service, Infra, UI)

## 🚀 Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone [https://github.com/seu-usuario/vacinapp.git](https://github.com/seu-usuario/vacinapp.git)