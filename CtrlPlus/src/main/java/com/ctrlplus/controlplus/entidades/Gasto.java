package com.ctrlplus.controlplus.entidades;

import com.ctrlplus.controlplus.enums.Categoria;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Gasto {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String descripcion;
    private Categoria categoria;
    private Double monto;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @OneToOne
    private Foto foto;

    public Gasto() {
    }

    public Gasto(String descripcion, Categoria categoria, Double monto, Date fecha, Foto foto) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.monto = monto;
        this.fecha = fecha;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Gasto{" + "id=" + id + ", descripcion=" + descripcion + ", categoria=" + categoria + ", monto=" + monto + ", fecha=" + fecha + ", foto=" + foto + '}';
    }
}
