package yeray.priede.gestoresports.Classes;

public class Participant {
    private String nombre;
    private String nickname;
    private String equipo;
    private int puntuacion;

    public Participant(String nombre, String nickname, String equipo, int puntuacion) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.equipo = equipo;
        this.puntuacion = puntuacion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getEquipo() { return equipo; }
    public void setEquipo(String equipo) { this.equipo = equipo; }

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    @Override
    public String toString() {
        return nombre + " (" + nickname + ") - " + equipo + " - Puntuació: " + puntuacion;
    }
}
