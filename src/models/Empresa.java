package models;

public class Empresa {
    private String nombre;
    private String sector;
    private String contacto;
    private Stand stand;

    public Empresa(String nombre, String sector, String contacto) {
        this.nombre = nombre;
        this.sector = sector;
        this.contacto = contacto;
    }

    public String getNombre() { return nombre; }
    public String getSector() { return sector; }
    public String getContacto() { return contacto; }
    public Stand getStand() { return stand; }
    public void asignarStand(Stand stand) { this.stand = stand; }

    public void setSector(String sector) { this.sector = sector; }
    public void setContacto(String contacto) { this.contacto = contacto; }
}