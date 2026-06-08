package vacinahub.domain;

import java.time.LocalDate;

public class RegistroVacina {
    private Long id;
    private Vacina vacina;
    private int doseAtual;
    private LocalDate dataAplicacao;
    private LocalDate dataProximaDose;
    private DoseStatus status;

    public RegistroVacina(Vacina vacina, int doseAtual, LocalDate dataAplicacao) {
        this.vacina = vacina;
        this.doseAtual = doseAtual;
        this.dataAplicacao = dataAplicacao;
        this.status = DoseStatus.APLICADA;
    }

    // Getters e Setters básicos
    public Vacina getVacina() { return vacina; }
    public int getDoseAtual() { return doseAtual; }
    public LocalDate getDataAplicacao() { return dataAplicacao; }
    public void setDataProximaDose(LocalDate dataProximaDose) { this.dataProximaDose = dataProximaDose; }
    public void setStatus(DoseStatus status) { this.status = status; }
}