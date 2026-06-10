package vacinahub.domain;

import java.time.LocalDate;
import java.time.Period;
import jakarta.persistence.*;

/**
 * Entidade que representa um membro da família vinculado a um Usuário principal.
 */
@Entity
@Table(name = "tb_dependente")
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private LocalDate dataNascimento;
    private String parentesco; // Ex: Filho, Filha, Mãe, Pai
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Dependente() {
    }

    /**
     * Construtor completo para facilitar a criação de instâncias.
     */
    public Dependente(Long id, String nome, LocalDate dataNascimento, String parentesco) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.parentesco = parentesco;
    }

    /**
     * Calcula a idade do dependente de forma dinâmica com base na data atual.
     * @return int Idade em anos (retorna 0 se a data de nascimento for nula).
     */
    public int getIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    // --- Getters e Setters ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}