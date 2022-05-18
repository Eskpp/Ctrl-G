package com.ctrlplus.controlplus.repositorios;

import com.ctrlplus.controlplus.entidades.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, String> {
    //ordenar gastos por fecha
    
    //ordenar gastos por categoría
    //buscar gastos por categoría
    
    //ordenar gastos por monto
    
     //buscar por descripción

}
