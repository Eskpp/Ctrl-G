package com.ctrlplus.controlplus.entidades;

import com.ctrlplus.controlplus.enums.Categoria;
import com.ctrlplus.controlplus.enums.CategoriaIngreso;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
public class Movimiento {
    
    private String descripcion;
    private Double monto;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @OneToOne
    private Comprobante comprobante;
    
    @Enumerated(EnumType.STRING)
    private CategoriaIngreso categoriaIngreso;
    
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    
    @ManyToOne
    private Usuario usuario;

}
