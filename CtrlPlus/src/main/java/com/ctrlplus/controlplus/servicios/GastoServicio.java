package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.entidades.Comprobante;
import com.ctrlplus.controlplus.entidades.Gasto;
import com.ctrlplus.controlplus.enums.Categoria;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.repositorios.GastoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GastoServicio {

    @Autowired
    private GastoRepositorio gastoRepositorio;

    @Transactional()
    public Gasto agregar(Double monto, Categoria categoria, String descripcion, Comprobante comprobante) throws ErrorServicio {

        validar(monto, categoria);

        Gasto gasto = new Gasto();

        gasto.setMonto(monto);
        gasto.setFecha(new Date());
        gasto.setCategoria(categoria);
        gasto.setDescripcion(descripcion);
        gasto.setComprobante(comprobante);

        return gastoRepositorio.save(gasto);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, Double monto, Categoria categoria, String descripcion, Comprobante comprobante) throws ErrorServicio {

        validar(monto, categoria);

        Optional<Gasto> respuesta = gastoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Gasto gasto = respuesta.get();
            gasto.setMonto(monto);
            gasto.setFecha(new Date());
            gasto.setCategoria(categoria);
            gasto.setDescripcion(descripcion);
            gasto.setComprobante(comprobante);

            gastoRepositorio.save(gasto);

        } else {
            throw new ErrorServicio("No existe el gasto que desea modificar.");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio {

        Optional<Gasto> respuesta = gastoRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Gasto gasto = respuesta.get();
            gastoRepositorio.delete(gasto);
        } else {
            throw new ErrorServicio("No existe el gasto que desea eliminar.");
        }
    }
    
     @Transactional(readOnly = true) 
    public List<Gasto> listar() {
        return gastoRepositorio.findAll(); 
    }

    public void validar(Double monto, Categoria categoria) throws ErrorServicio {
        if (monto == null || monto.toString().isEmpty()) {
            throw new ErrorServicio("Debe ingresar un importe.");
        }

        if (categoria == null || categoria.toString().isEmpty()) {
            throw new ErrorServicio("Debe seleccionar una Categoría.");
        }
    }
}
