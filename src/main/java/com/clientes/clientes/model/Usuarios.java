package com.clientes.clientes.model;

public class Usuarios {
    private int id;
    private String rut;
    private String rol;
    private String nombre;
    private String apellido;
    private String direccion ;

    public Usuarios(int id,String rut, String nombre, String apellido, String rol, String direccion) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.direccion = direccion;
    }
    
    public int getId() {
        return id;
    }
    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public String getDireccion() {
        return direccion;
    }

    public String getRol() {
        return rol;
    }
}
