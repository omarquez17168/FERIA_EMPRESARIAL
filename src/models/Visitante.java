package models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Visitante {
    private String nombre;
    private String identificacion;
    private String correo;
    private List<Comentario> visitas;

    public Visitante(String nombre, String identificacion, String correo) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.correo = correo;
        this.visitas = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString(){
        return "Visitante: " + nombre +"| id: " + identificacion + "| Correo: "+ correo;
    }
}