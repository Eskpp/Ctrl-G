package com.ctrlplus.controlplus.repositorios;

import com.ctrlplus.controlplus.entidades.Gasto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, String> {
   
    
    //buscar gastos por categoría
    @Query("SELECT g FROM Gasto g WHERE  g.categoria = :categoria")
    public List<Gasto> buscarPorCategoria(@Param("categoria") String categoria);
    
    //ordenar gastos por categoría
    @Query("SELECT g FROM Gasto g ORDER BY categoria asc")
    public List<Gasto> ordenarPorCategoriaAsc();
    
     @Query("SELECT g FROM Gasto g ORDER BY categoria desc")
    public List<Gasto> ordenarPorCategoriaDesc();
    
    //buscar gastos por fecha
    @Query("SELECT g FROM Gasto g WHERE  g.fecha = :fecha")
    public List<Gasto> buscarPorFecha(@Param("fecha") String fecha);
    
    //ordenar gastos por fecha
    @Query("SELECT g FROM Gasto g ORDER BY fecha asc")
    public List<Gasto> ordenarPorFechaAsc();
    
     @Query("SELECT g FROM Gasto g ORDER BY fecha desc")
    public List<Gasto> ordenarPorFechaDesc();
    
    //buscar gastos por monto
     @Query("SELECT g FROM Gasto g WHERE  g.monto = :monto")
    public List<Gasto> buscarPorMonto(@Param("monto") String monto);
    
    //ordenar gastos por monto
    @Query("SELECT g FROM Gasto g ORDER BY monto asc")
    public List<Gasto> ordenarPorMontoAsc();
    
     @Query("SELECT g FROM Gasto g ORDER BY fecha desc")
    public List<Gasto> ordenarPorMontoDesc();
    
}
