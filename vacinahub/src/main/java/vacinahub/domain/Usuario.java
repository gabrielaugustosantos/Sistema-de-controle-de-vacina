package vacinahub.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Entidade central que representa o titular da conta no sistema.
 * Agrupa dados de autenticação e seus respectivos dependentes.
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    @Column(unique = true)
    private String email;
    
    private String senha;
    private LocalDate dataNascimento;
    private String genero;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private List<Dependente> dependentes = new ArrayList<>();

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Usuario() {
        this.dependentes = new ArrayList<>();
    }

    /**
     * Construtor para o fluxo de cadastro inicial do AuthService.
     */
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dependentes = new ArrayList<>();
    }

    /**
     * Construtor completo do titular.
     */
    public Usuario(Long id, String nome, String email, String senha, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.dependentes = new ArrayList<>();
    }

    /**
     * Calcula a idade do usuário de forma dinâmica.
     * @return int Idade em anos.
     */
    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    /**
     * Adiciona um dependente à lista do usuário, encapsulando a regra da coleção.
     * @param dependente Instância do dependente a ser vinculado.
     */
    public void adicionarDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }

    // --- Getters e Setters ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public List<Dependente> getDependentes() { return dependentes; }
}