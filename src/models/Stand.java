package models;
import java.util.ArrayList;
import java.util.List;

public class Stand {
    private int numero;
    private String ubicacion;
    private String tamaño;
    private Empresa empresa;
    private List<Comentario> comentarios;

    public Stand(int numero, String ubicacion, String tamaño) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.tamaño = tamaño;
        this.comentarios = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public String getUbicacion() { return ubicacion; }
    public String getTamaño() { return tamaño; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public List<Comentario> getComentarios() { return comentarios; }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public double calcularPromedioCalificaciones() {
        if (comentarios.isEmpty()) return 0;
        return comentarios.stream().mapToInt(Comentario::getCalificacion).average().orElse(0);
    }
}