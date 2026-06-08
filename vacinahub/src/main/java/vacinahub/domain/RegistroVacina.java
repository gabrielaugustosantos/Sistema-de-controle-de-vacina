package vacinahub.domain;

import java.time.LocalDate;

public class RegistroVacina {
    private Long id;
    private Usuario usuario;
    private Vacina vacina;
    private int doseAtual;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private DoseStatus status;

    // Construtor completo usado pelo Service
    public RegistroVacina(Usuario usuario, Vacina vacina, int doseAtual, DoseStatus status) {
        this.usuario = usuario;
        this.vacina = vacina;
        this.doseAtual = doseAtual;
        this.status = status;
        this.dataAplicacao = LocalDate.now(); 
    }

    // Getters e Setters
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Vacina getVacina() { return vacina; }
    
    public int getDoseAtual() { return doseAtual; }
    
    public LocalDate getDataAplicacao() { return dataAplicacao; }
    public void setDataAplicacao(LocalDate dataAplicacao) { this.dataAplicacao = dataAplicacao; }
    
    public LocalDate getDataProximaDose() { return dataProximaDose; }
    public void setDataProximaDose(LocalDate dataProximaDose) { this.dataProximaDose = dataProximaDose; }
    
    public DoseStatus getDoseStatus() { return status; } 
    public void setDoseStatus(DoseStatus status) { this.status = status; }
}