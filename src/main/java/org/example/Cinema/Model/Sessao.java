package org.example.Cinema.Model;

public class Sessao {
    private int id;
    private int filmeId;
    private String horario;
    private String sala;

    // Construtor padrão
    public Sessao() {}

    // Construtor completo
    public Sessao(int id, int filmeId, String horario, String sala) {
        this.id = id;
        this.filmeId = filmeId;
        this.horario = horario;
        this.sala = sala;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFilmeId() { return filmeId; }
    public void setFilmeId(int filmeId) { this.filmeId = filmeId; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }

    @Override
    public String toString() {
        return this.horario + " (" + this.sala + ")";
    }
}
