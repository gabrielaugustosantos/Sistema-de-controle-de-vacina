package vacinahub.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;

    private List<Dependente> dependentes;
    private List<RegistroVacina> registros;
    // Construtor Padrão
    public Usuario() {
        this.dependentes = new ArrayList<>();
    }

    // Construtor usado pelo AuthService (Cadastro inicial não pede data de nascimento)
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dependentes = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    // Construtor Completo
    public Usuario(Long id, String nome, String email, String senha, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.dependentes = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    // Getters e Setters
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

    // Um para vários
    public List<Dependente> getDependentes() { return dependentes; }

    public void adicionarDependente(Dependente dependente) {
        this.dependentes.add(dependente);
    }

    public List<RegistroVacina> getRegistros() { return registros; }

    public void adicionarRegistro(RegistroVacina registro) {
        this.registros.add(registro);
    }
}