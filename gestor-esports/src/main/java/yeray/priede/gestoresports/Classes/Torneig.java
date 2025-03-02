package yeray.priede.gestoresports.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Torneig {
    public String nom;
    public LocalDate data;
    private String joc;
    private String format;
    private String premis;
    private List<Participant> participants;  // Cambiado a Participant

    public Torneig(String nom, LocalDate data, String joc, String format, String premis) {
        this.nom = nom;
        this.data = data;
        this.joc = joc;
        this.format = format;
        this.premis = premis;
        this.participants = new ArrayList<>();
    }

    // Getters i setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getJoc() { return joc; }
    public void setJoc(String joc) { this.joc = joc; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public String getPremis() { return premis; }
    public void setPremis(String premis) { this.premis = premis; }

    public List<Participant> getParticipants() { return participants; }
    public void setParticipants(List<Participant> participants) { this.participants = participants; }

    public void afegirParticipant(Participant participant) {  // Cambiado para aceptar Participant
        participants.add(participant);
    }

    @Override
    public String toString() {
        return "Torneig: " + nom + " (" + data + ")";
    }
}
