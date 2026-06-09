package vacinahub.domain;

import java.time.LocalDate;
import java.time.Period; // <-- Importante para calcular a idade
import jakarta.persistence.*;

@Entity
@Table(name = "tb_dependente")
/**
 * Classe que representa um membro da família (dependente) vinculado a um Usuário principal.
 */
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String parentesco; // Ex: Filho, Filha, Mãe, Pai
    
    // AQUI ESTÁ A CORREÇÃO: As anotações que ensinam o banco a ligar as tabelas!
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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

    // Método para a tela calcular a idade automaticamente sem dar erro
    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    // Getters e Setters
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}