package vacinapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade Usuário no sistema VacinApp.
 * Esta é a nossa primeira regra de negócio (Feature) da Sprint 0.
 */
public class Usuario {
    
    // Atributos que guardam as informações do usuário
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

    // Lista que guarda todos os dependentes vinculados a esta conta principal
    private List<Dependente> dependentes;

    // Construtor padrão (vazio)
    public Usuario() {
        // Inicializa a lista vazia para evitar erros caso o usuário ainda não tenha dependentes
        this.dependentes = new ArrayList<>();
    }

    // Construtor completo para facilitar a criação de novos usuários
    public Usuario(Long id, String nome, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.dependentes = new ArrayList<>();
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void adicionarDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }
}