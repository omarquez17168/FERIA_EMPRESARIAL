package models;
import java.time.LocalDate;

public class Comentario {
    private Visitante visitante;
    private Stand stand;
    private int calificacion; // 1 a 5 estrellas
    private String mensaje;
    private LocalDate fecha;

    public Comentario(Visitante visitante, Stand stand, int calificacion, String mensaje) {
        this.visitante = visitante;
        this.stand = stand;
        this.calificacion = calificacion;
        this.mensaje = mensaje;
        this.fecha = LocalDate.now();
    }

    public Visitante getVisitante() { return visitante; }
    public Stand getStand() { return stand; }
    public int getCalificacion() { return calificacion; }
    public String getMensaje() { return mensaje; }
    public LocalDate getFecha() { return fecha; }
}