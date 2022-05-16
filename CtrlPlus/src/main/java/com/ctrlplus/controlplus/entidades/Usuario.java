package com.ctrlplus.controlplus.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {
    
    
    //Renzo
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String mail;
    private String clave;
    
    @OneToMany
    private List<Gasto> gastos;
    @OneToMany
    private List<Ingreso> ingresos;

    public Usuario() {
    }

    public Usuario(String nombre, String mail, String clave, List<Gasto> gastos, List<Ingreso> ingresos) {
        this.nombre = nombre;
        this.mail = mail;
        this.clave = clave;
        this.gastos = gastos;
        this.ingresos = ingresos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", mail=" + mail + ", clave=" + clave + ", gastos=" + gastos + ", ingresos=" + ingresos + '}';
    }
}
