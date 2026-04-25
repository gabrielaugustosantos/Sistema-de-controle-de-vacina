package vacinahub;

public class Vacina {

    private Long id;
    private String nome;
    private String publicoAlvo; // Ex: Crianças, Idosos, Adultos, Geral
    private int dosesNecessarias;
    private int mesesIntervalo; // Tempo de espera entre as doses (se houver mais de uma)

    // Construtor vazio
    public Vacina() {
    }

    // Construtor completo
    public Vacina(Long id, String nome, String publicoAlvo, int dosesNecessarias, int mesesIntervalo) {
        this.id = id;
        this.nome = nome;
        this.publicoAlvo = publicoAlvo;
        this.dosesNecessarias = dosesNecessarias;
        this.mesesIntervalo = mesesIntervalo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPublicoAlvo() { return publicoAlvo; }
    public void setPublicoAlvo(String publicoAlvo) { this.publicoAlvo = publicoAlvo; }

    public int getDosesNecessarias() { return dosesNecessarias; }
    public void setDosesNecessarias(int dosesNecessarias) { this.dosesNecessarias = dosesNecessarias; }

    public int getMesesIntervalo() { return mesesIntervalo; }
    public void setMesesIntervalo(int mesesIntervalo) { this.mesesIntervalo = mesesIntervalo; }
}