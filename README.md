[README.md](https://github.com/user-attachments/files/27086160/README.md)
# 💉 VacinaHub - Sistema Web de Gestão Vacinal

---

## 📌 Resumo do Projeto

O **VacinaHub** é um sistema web desenvolvido para o gerenciamento inteligente do calendário vacinal de usuários e seus dependentes. A plataforma permite o cadastro de pessoas (como filhos, pais e outros familiares), oferecendo um acompanhamento completo da imunização, com alertas automáticos e recomendações personalizadas com base na idade e histórico vacinal.

Além disso, o sistema auxilia o usuário na localização de postos de saúde próximos e no controle das vacinas necessárias, promovendo mais organização e prevenção.

---

## 🎯 Problema e Público-Alvo

### Problema que resolve

A perda do cartão de vacinação físico, a dificuldade em acompanhar múltiplos perfis familiares e o esquecimento de prazos de vacinação, que podem comprometer a imunização adequada.

### Público-alvo

- Jovens adultos
- Pais e responsáveis
- Pessoas que cuidam de idosos
- Usuários que desejam centralizar o controle vacinal da família

---

## ✨ Funcionalidades

### 👤 Gestão de Usuários

- Cadastro e autenticação de usuários
- Cadastro de dependentes (filhos, pais, etc.)
- Gerenciamento de múltiplos perfis vinculados a uma conta

### 💉 Controle Vacinal

- Registro de vacinas aplicadas
- Histórico completo por indivíduo
- Identificação de vacinas pendentes com base na idade

### 📅 Alertas Inteligentes

- Notificações por e-mail sobre vacinas pendentes
- Lembretes automáticos de próximas doses

### 📊 Regras de Vacinação

- Sugestão de vacinas com base na faixa etária
- Cálculo automático de datas para reforço ou novas doses

### 📍 Localização de Postos de Saúde

- Busca de unidades de saúde próximas (UBS)
- Utilização de endereço cadastrado ou localização em tempo real

### 🔗 Integrações

- Integração com API externa de dados vacinais (quando disponível)
- Integração com serviços de geolocalização

---

## 🛠️ Tecnologias Utilizadas

| Categoria                 | Tecnologia                          |
|---------------------------|-------------------------------------|
| Linguagem                 | Java 17+                            |
| Gerenciador de Dependências | Maven                             |
| Testes                    | JUnit 5 e Cucumber (BDD)            |
| Arquitetura               | Camadas (Domain, Service, Infra, UI)|
| Web                       | Spring Boot (recomendado)           |
| Banco de Dados            | MySQL ou PostgreSQL                 |
| API de Mapas              | Google Maps API ou OpenStreetMap    |

---

## 🚀 Possíveis Melhorias Futuras

- Aplicativo mobile integrado
- Integração com sistemas públicos de saúde
- Dashboard com estatísticas de vacinação
- Notificações via SMS ou push
