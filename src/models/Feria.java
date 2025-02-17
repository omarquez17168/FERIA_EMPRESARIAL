package models;

import java.util.ArrayList;
import java.util.List;

public class Feria {
    private String nombre;
    private String ubicacion;
    private List<Empresa> empresas;
    private List<Visitante> visitantes;
    private List<Pabellon> pabellones;

    // Constructor
    public Feria(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.empresas = new ArrayList<>();
        this.visitantes = new ArrayList<>();
        this.pabellones = new ArrayList<>();

        // Crear los 3 pabellones con 10 stands cada uno
        pabellones.add(new Pabellon("A"));
        pabellones.add(new Pabellon("B"));
        pabellones.add(new Pabellon("C"));
    }

    // GETTERS
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public List<Pabellon> getPabellones() { return pabellones; }
    public List<Empresa> listarEmpresas() { return empresas; }
    public List<Visitante> listarVisitantes() { return visitantes; }

    // REGISTRAR EMPRESA
    public void registrarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    // REGISTRAR VISITANTE
    public void registrarVisitante(Visitante visitante) {
        visitantes.add(visitante);
    }

    // BUSCAR EMPRESA POR NOMBRE
    public Empresa buscarEmpresa(String nombre) {
        for (Empresa empresa : empresas) {
            if (empresa.getNombre().equalsIgnoreCase(nombre)) {
                return empresa;
            }
        }
        return null;
    }

    // EDITAR EMPRESA
    public boolean editarEmpresa(String nombre, String nuevoSector, String nuevoContacto) {
        Empresa empresa = buscarEmpresa(nombre);
        if (empresa != null) {
            empresa.setSector(nuevoSector);
            empresa.setContacto(nuevoContacto);
            return true;
        }
        return false;
    }

    // ELIMINAR EMPRESA
    public boolean eliminarEmpresa(String nombre) {
        Empresa empresa = buscarEmpresa(nombre);
        if (empresa != null) {
            empresas.remove(empresa);
            return true;
        }
        return false;
    }

    // BUSCAR VISITANTE POR NOMBRE
    public Visitante buscarVisitante(String nombre) {
        for (Visitante visitante : visitantes) {
            if (visitante.getNombre().equalsIgnoreCase(nombre)) {
                return visitante;
            }
        }
        return null;
    }

    // ASIGNAR STAND A EMPRESA
    public void asignarStand(Empresa empresa, Pabellon pabellon, int numeroStand) {
        for (Stand stand : pabellon.getStands()) {
            if (stand.getNumero() == numeroStand && stand.getEmpresa() == null) {
                stand.setEmpresa(empresa);
                empresa.asignarStand(stand);
                System.out.println(" Stand #" + numeroStand + " en " + pabellon.getNombre() + " asignado a " + empresa.getNombre());
                return;
            }
        }
        System.out.println(" El stand seleccionado ya está ocupado.");
    }

    // BUSCAR STAND POR NÚMERO Y PABELLÓN
    public Stand buscarStand(int numero, Pabellon pabellon) {
        for (Stand stand : pabellon.getStands()) {
            if (stand.getNumero() == numero) {
                return stand;
            }
        }
        return null;
    }

    public Visitante buscarVisitantePorId(String identificacion) {
        for (Visitante visitante : visitantes) {
            if (visitante.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return visitante;
            }
        }
        return null;
    }

    public boolean editarVisitante(String identificacion, String nuevoNombre, String nuevoCorreo) {
        Visitante visitante = buscarVisitantePorId(identificacion);
        if (visitante != null) {
            visitante.setNombre(nuevoNombre);
            visitante.setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    public boolean eliminarVisitante(String identificacion) {
        Visitante visitante = buscarVisitantePorId(identificacion);
        if (visitante != null) {
            visitantes.remove(visitante);
            return true;
        }
        return false;
    }
}