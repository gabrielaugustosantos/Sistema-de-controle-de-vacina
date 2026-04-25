package vacinahub;

import java.time.LocalDate;

/**
 * Classe que representa um membro da família (dependente) vinculado a um Usuário principal.
 */
public class Dependente {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String parentesco; // Ex: Filho, Filha, Mãe, Pai

    // Construtor padrão (vazio)
    public Dependente() {
    }

    // Construtor completo para facilitar a criação
    public Dependente(Long id, String nome, LocalDate dataNascimento, String parentesco) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.parentesco = parentesco;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
}