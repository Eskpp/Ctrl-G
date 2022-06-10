package com.ctrlplus.controlplus.repositorios;

import com.ctrlplus.controlplus.entidades.Gasto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, String> {

    @Query("SELECT g FROM Gasto g WHERE g.usuario_id = :usuario")
    public List<Gasto> listarPorUsuario(@Param("usuario") String usuario);
    //@Query(value = "SELECT * FROM usuario WHERE email = :email", nativeQuery = true)

    //buscar gastos por categoría
    @Query("SELECT g FROM Gasto g WHERE g.categoria = :categoria AND g.usuario_id = :usuario")
    public List<Gasto> buscarPorCategoria(@Param("categoria") String categoria, @Param("usuario") String usuario);

    //ordenar gastos por categoría
    @Query("SELECT g FROM Gasto g ORDER BY categoria asc WHERE g.usuario_id = :usuario")
    public List<Gasto> ordenarPorCategoriaAsc(@Param("usuario") String usuario);

    @Query("SELECT g FROM Gasto g ORDER BY categoria desc WHERE g.usuario_id = :usuario")
    public List<Gasto> ordenarPorCategoriaDesc(@Param("usuario") String usuario);

    //buscar gastos por fecha
    @Query("SELECT g FROM Gasto g WHERE  g.fecha = :fecha AND g.usuario_id = :usuario")
    public List<Gasto> buscarPorFecha(@Param("fecha") String fecha, @Param("usuario") String usuario);

    //ordenar gastos por fecha
    @Query("SELECT g FROM Gasto g WHERE g.usuario_id = :usuario ORDER BY g.fecha asc")
    public List<Gasto> ordenarPorFechaAsc(@Param("usuario") String usuario);

    @Query("SELECT g FROM Gasto g WHERE g.usuario_id = :usuario ORDER BY g.fecha desc")
    public List<Gasto> ordenarPorFechaDesc(@Param("usuario") String usuario);

    //buscar gastos por monto
    @Query("SELECT g FROM Gasto g WHERE  g.monto = :monto AND g.usuario_id = :usuario")
    public List<Gasto> buscarPorMonto(@Param("monto") String monto, @Param("usuario") String usuario);

    //ordenar gastos por monto
    @Query("SELECT g FROM Gasto g WHERE g.usuario_id = :usuario ORDER BY g.monto asc")
    public List<Gasto> ordenarPorMontoAsc(@Param("usuario") String usuario);

    @Query("SELECT g FROM Gasto g WHERE g.usuario_id = :usuario ORDER BY g.fecha desc")
    public List<Gasto> ordenarPorMontoDesc(@Param("usuario") String usuario);

}
