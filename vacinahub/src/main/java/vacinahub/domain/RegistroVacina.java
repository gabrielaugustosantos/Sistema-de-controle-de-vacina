package vacinahub.domain;

import java.time.LocalDate;

public class RegistroVacina {
    private Long id;
    private Vacina vacina;
    private Usuario usuario;
    private int doseAtual;
    private DoseStatus status;

    // Preenchido quando a dose for aplicada
    private LocalDate dataAplicacao;

    // Preenchido quando houver uma próxima dose prevista
    private LocalDate dataProximaDose;

    // Constructor Vazio
    public RegistroVacina() {
    }

    // Constructor completo
    public RegistroVacina(Usuario usuario, Vacina vacina, int doseAtual, DoseStatus status) {
        this.usuario = usuario;
        this.vacina = vacina;
        this.doseAtual = doseAtual;
        this.status = status;
    }

    // Getters e Setters básicos
    public Vacina getVacina() { return vacina; }
    public Usuario getUsuario() { return usuario; }
    public int getDoseAtual() { return doseAtual; }
    public LocalDate getDataAplicacao() { return dataAplicacao; }
    public LocalDate getDataProximaDose() { return dataProximaDose; }
    public DoseStatus getDoseStatus() { return status;}

    public void setDataProximaDose(LocalDate dataProximaDose) { this.dataProximaDose = dataProximaDose; }
    public void setStatus(DoseStatus status) { this.status = status; }
  
}