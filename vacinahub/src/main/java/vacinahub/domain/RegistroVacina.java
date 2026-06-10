package vacinahub.domain;

import java.time.LocalDate;
import jakarta.persistence.*;

/**
 * Entidade associativa que registra a aplicação de uma vacina para um usuário específico.
 * Registra o histórico e projeta datas futuras do ciclo vacinal.
 */
@Entity
@Table(name = "tb_registro_vacina")
public class RegistroVacina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; 

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;
    
    private int doseAtual;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    
    @Enumerated(EnumType.STRING)
    private DoseStatus status;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public RegistroVacina() {}

    /**
     * Construtor utilizado pelos Services na criação de uma nova dose.
     */
    public RegistroVacina(Usuario usuario, Vacina vacina, int doseAtual, DoseStatus status) {
        this.usuario = usuario;
        this.vacina = vacina;
        this.doseAtual = doseAtual;
        this.status = status;
        this.dataAplicacao = LocalDate.now(); // Valor padrão, pode ser sobrescrito pelos Services
    }

    // --- Getters e Setters ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Vacina getVacina() { return vacina; }
    public void setVacina(Vacina vacina) { this.vacina = vacina; }
    
    public int getDoseAtual() { return doseAtual; }
    public void setDoseAtual(int doseAtual) { this.doseAtual = doseAtual; }
    
    public LocalDate getDataAplicacao() { return dataAplicacao; }
    public void setDataAplicacao(LocalDate dataAplicacao) { this.dataAplicacao = dataAplicacao; }
    
    public LocalDate getDataProximaDose() { return dataProximaDose; }
    public void setDataProximaDose(LocalDate dataProximaDose) { this.dataProximaDose = dataProximaDose; }
    
    public DoseStatus getDoseStatus() { return status; } 
    public void setDoseStatus(DoseStatus status) { this.status = status; }
}