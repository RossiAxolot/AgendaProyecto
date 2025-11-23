package com.ejemplo.agenda.model;

public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    public Contacto() {}

    public Contacto(int id, String nombre, String apellido, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getcorreo() { return correo; }
    public void setcorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "Contacto{id=" + id + ", nombre=" + nombre +
                ", apellido=" + apellido + ", telefono=" + telefono +
                ", correo=" + correo + "}";
    }
}