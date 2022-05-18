package com.ctrlplus.controlplus.repositorios;

import com.ctrlplus.controlplus.entidades.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepositorio extends JpaRepository<Ingreso, String> {
    
    //ordenar ingresos por fecha
    
    //ordenar ingresos por monto
    
    //buscar por descripción

}
