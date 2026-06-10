# 📚 Manual de Engenharia de Software — VacinaHub (Sprint 2)

Bem-vindo à documentação técnica e guia de defesa do **VacinaHub**. Este espaço foi desenvolvido para detalhar as decisões arquiteturais, padrões de projeto e tecnologias aplicadas no desenvolvimento do nosso MVP.

---

## 🏛️ 1. A Arquitetura do Sistema: Padrão de 4 Camadas

O sistema adota o padrão de **Arquitetura em Camadas (Layered Architecture)**. A principal diretriz dessa abordagem é a **separação de responsabilidades (SoC - Separation of Concerns)**, garantindo alta coesão e baixo acoplamento.

* **`domain` (Modelo de Domínio):** Onde residem as regras de negócio puras e as entidades que representam o mundo real (`Usuario`, `Dependente`, `Vacina`, `RegistroVacina`, `DoseStatus`). Elas não conhecem detalhes de persistência ou de interface.
* **`infra` (Persistência):** Camada de infraestrutura que isola o acesso ao banco de dados. Implementa o *Repository Pattern* através do Spring Data JPA, eliminando a necessidade de escrever queries SQL manuais.
* **`service` (Lógica de Negócio / Cérebro):** Camada responsável pela inteligência do ecossistema. Executa validações cadastrais complexas (`AuthService`) e os algoritmos de projeção de datas do calendário vacinal (`VacinaService`).
* **`ui` (User Interface / Controlador):** Implementação do controlador no padrão **MVC (Model-View-Controller)**. O `WebController` intercepta as requisições HTTP, gerencia as sessões e alimenta os templates visuais.

---

## 🛠️ 2. Tecnologias e Ferramentas Corporativas

* **Spring Boot 3 & Java 17:** Plataforma base que fornece injeção de dependências, gerenciamento de ciclo de vida de componentes e um servidor Tomcat embutido pronto para produção.
* **H2 Database (In-Memory):** Banco de dados relacional que roda diretamente na memória RAM. É ideal para desenvolvimento ágil e Sprints, pois elimina o overhead de instalação de servidores pesados e garante um estado limpo a cada reinicialização da aplicação.
* **Thymeleaf (Template Engine):** Motor de renderização do lado do servidor (Server-Side Rendering). Ele interpreta atributos dinâmicos prefixados com `th:`, processa as amarrações de dados do modelo e entrega HTML limpo e responsivo para o navegador.
* **Jakarta Servlet (`HttpSession`):** Mecanismo de controle de estado do usuário (Stateful Session). Armazena as credenciais do usuário autenticado na memória do servidor para proteger as rotas internas contra acessos não autorizados.
* **Java Time API (`LocalDate` e `Period`):** Manipulação moderna de eixos temporais. Utiliza `LocalDate` para datas exatas e `Period.between` para calcular a idade de usuários e dependentes em tempo de execução, evitando dados redundantes e obsoletos.

---

## 🏷️ 3. Decifrando as Anotações (`@`) e os `@Beans`

As anotações funcionam como metadados que configuram o comportamento das nossas classes perante o ecossistema do Spring Boot e do Hibernate (JPA).

### Gerenciamento de Componentes (Inversão de Controle)
* **`@SpringBootApplication`:** Ativa três recursos fundamentais na classe principal: configuração automática, gerenciamento de propriedades e o escaneamento automático de componentes (`@ComponentScan`).
* **`@Controller`, `@Service`, `@Repository`:** Estereótipos do Spring que definem a especialização de cada classe na arquitetura, transformando-as em *Beans* gerenciados pelo framework.
* **`@Autowired`:** Realiza a **Injeção de Dependências** de forma automatizada. Reduz drasticamente o acoplamento do código ao evitar o uso manual do operador `new`.

### Mapeamento Web (Rotas MVC)
* **`@GetMapping` & `@PostMapping`:** Direcionam o tráfego das URLs para métodos Java específicos, separando requisições de leitura (GET) de postagens confidenciais de formulários (POST).
* **`@RequestParam`:** Captura os dados enviados pelos parâmetros do HTML (vidos das propriedades `name=""` dos inputs) e os converte em variáveis tipadas no back-end.

### Mapeamento Objeto-Relacional (ORM / JPA)
* **`@Entity` & `@Table`:** Mapeiam a classe Java como uma tabela física estruturada no banco de dados.
* **`@Id` & `@GeneratedValue`:** Configuram a chave primária da tabela com incremento sequencial automático gerado pelo próprio banco de dados (`IDENTITY`).
* **`@ManyToOne` & `@OneToMany`:** Amarram os relacionamentos de chaves estrangeiras entre as tabelas. No relacionamento entre Usuário e Dependentes, aplicamos o `CascadeType.ALL` e `orphanRemoval = true` para garantir que a exclusão de um usuário titular remova em cascata seus dependentes associados, preservando a integridade referencial.

### 💡 O papel do `@Bean`
Diferente das anotações estruturais que colocamos sobre classes, o `@Bean` é declarado sobre **métodos**. Ele instrui o Spring a executar aquela lógica e registrar o objeto retornado no contexto global da aplicação. No projeto, utilizamos para registrar o `CommandLineRunner initDatabase`, que executa um processo de **Data Seeding** (população inicial) inserindo o catálogo base de vacinas do PNI assim que o sistema termina de ligar.

---

## 🔄 4. Os Fluxos de Integração e Regras de Negócio

1. **Controle de Acesso com Stateful Session:** Ao enviar o formulário de login, o controlador valida as credenciais via `AuthService`. Com o sucesso, o objeto do usuário é injetado na `HttpSession` sob a chave `"usuarioLogado"`. Nas rotas protegidas (como `/dashboard`), o sistema faz uma checagem preventiva; se o atributo estiver nulo, o usuário é interceptado e redirecionado para a raiz (`/`).
2. **Automação do Ciclo Vacinal:** Quando o usuário registra uma dose tomada na interface, o `VacinaService` persiste o registro atual como `APLICADA`. Em uma operação atômica, o motor avalia os metadados da vacina. Caso existam doses subsequentes previstas, o sistema calcula o prazo exato usando a função `.plusMonths()` da API do Java e insere uma nova linha programada para o futuro com o estado `PENDENTE`.
3. **Agregação Funcional de Dados:** No carregamento do Dashboard, o controlador consome os dados do repositório e utiliza **Java Streams** com expressões Lambda para filtrar apenas os registros com estado `PENDENTE`, ordenando-os de forma cronológica (`Comparator.comparing`) para exibir na tela um card de alertas reativo focado na vacina com vencimento mais próximo.