package com.ctrlplus.controlplus.repositorios;

import com.ctrlplus.controlplus.entidades.Ingreso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepositorio extends JpaRepository<Ingreso, String> {
    
    //buscar ingresos por fecha
    @Query("SELECT i FROM Ingreso i WHERE  i.fecha = :fecha")
    public List<Ingreso> buscarPorFecha(@Param("fecha") String fecha);
    
    //ordenar ingresos por fecha
    @Query("SELECT i FROM Ingreso i ORDER BY fecha asc")
    public List<Ingreso> ordenarPorFechaAsc();
    
     @Query("SELECT i FROM Gasto i ORDER BY fecha desc")
    public List<Ingreso> ordenarPorFechaDesc();
    
    //buscar ingresos por monto
     @Query("SELECT i FROM Ingreso i WHERE  i.monto = :monto")
    public List<Ingreso> buscarPorMonto(@Param("monto") String monto);
    
    //ordenar ingresos por monto
    @Query("SELECT i FROM Ingreso i ORDER BY monto asc")
    public List<Ingreso> ordenarPorMontoAsc();
    
     @Query("SELECT i FROM Ingreso i ORDER BY fecha desc")
    public List<Ingreso> ordenarPorMontoDesc();

}
