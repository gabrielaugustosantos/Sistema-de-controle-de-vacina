package vacinapp.domain;

/**
 * Classe que representa a entidade Usuário no sistema VacinApp.
 * Esta é a nossa primeira regra de negócio (Feature) da Sprint 0.
 */
public class Usuario {
    
    // Atributos que guardam as informações do usuário
    private Long id;
    private String nome;
    private String email;

    // Construtor padrão (vazio)
    public Usuario() {
    }

    // Construtor completo para facilitar a criação de novos usuários
    public Usuario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters (Métodos para acessar e alterar os atributos protegidos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}