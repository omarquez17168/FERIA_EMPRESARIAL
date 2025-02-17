package models;

import java.util.ArrayList;
import java.util.List;

public class Pabellon {
    private String nombre;
    private List<Stand> stands;

    public Pabellon(String nombre) {
        this.nombre = nombre;
        this.stands = new ArrayList<>();

        // Crear 10 stands con diferentes tamaños
        for (int i = 1; i <= 10; i++) {
            String tamaño = (i <= 3) ? "Grande" : (i <= 6) ? "Mediano" : "Pequeño";
            stands.add(new Stand(i, "Pabellón " + nombre + ", Stand " + i, tamaño));
        }
    }

    public String getNombre() { return nombre; }
    public List<Stand> getStands() { return stands; }

    public List<Stand> getStandsLibres() {
        List<Stand> libres = new ArrayList<>();
        for (Stand stand : stands) {
            if (stand.getEmpresa() == null) {
                libres.add(stand);
            }
        }
        return libres;
    }

    public List<Stand> getStandsOcupados() {
        List<Stand> ocupados = new ArrayList<>();
        for (Stand stand : stands) {
            if (stand.getEmpresa() != null) {
                ocupados.add(stand);
            }
        }
        return ocupados;
    }

    public List<Stand> getStandsLibresPorTamaño(String tamañoBuscado) {
        List<Stand> libres = new ArrayList<>();
        for (Stand stand : stands) {
            if (stand.getEmpresa() == null && stand.getTamaño().equalsIgnoreCase(tamañoBuscado)) {
                libres.add(stand);
            }
        }
        return libres;
    }

    public Stand buscarStand (int numero){
        for (Stand stand : stands){
            if (stand.getNumero() == numero){
                return stand;
            }
        }
        return null;
    }
}