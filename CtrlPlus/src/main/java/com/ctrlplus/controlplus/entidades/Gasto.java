package com.ctrlplus.controlplus.entidades;

import com.ctrlplus.controlplus.enums.Categoria;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
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
    private Comprobante foto;

}
