package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.entidades.Foto;
import com.ctrlplus.controlplus.entidades.Ingreso;
import com.ctrlplus.controlplus.repositorios.IngresoRepositorio;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngresoServicio {

    @Autowired
    private IngresoRepositorio ingresoRepositorio;

    Ingreso ingreso = new Ingreso();

    @Transactional(propagation = Propagation.NESTED)
    public Ingreso agregar(Double monto, String descripcion, Foto foto) {

        ingreso.setMonto(monto);
        ingreso.setFecha(new Date());
        ingreso.setDescripcion(descripcion);
        ingreso.setFoto(foto);

        return ingresoRepositorio.save(ingreso);
    }

    public void validar(Double monto, Categoria categoria) throws ErrorServicio {
        if (monto == null || monto.toString().isEmpty()) {
            throw new ErrorServicio("Debe ingresar un importe.");
        }

    
}
