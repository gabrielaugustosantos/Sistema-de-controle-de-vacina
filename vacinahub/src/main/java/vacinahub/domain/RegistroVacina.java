package vacinahub.domain;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_registro_vacina")
public class RegistroVacina {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Vínculo com o dono da vacina

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;
    private int doseAtual;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    
    @Enumerated(EnumType.STRING)
    private DoseStatus status;

    // O JPA construtor vazio para conseguir criar a tabela
    public RegistroVacina() {}

    // Construtor completo usado pelo Service
    public RegistroVacina(Usuario usuario, Vacina vacina, int doseAtual, DoseStatus status) {
        this.usuario = usuario;
        this.vacina = vacina;
        this.doseAtual = doseAtual;
        this.status = status;
        this.dataAplicacao = LocalDate.now(); 
    }

    // Getters e Setters
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