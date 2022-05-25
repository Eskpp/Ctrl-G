package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.entidades.Comprobante;
import com.ctrlplus.controlplus.entidades.Ingreso;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.repositorios.IngresoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class IngresoServicio {

    @Autowired
    private IngresoRepositorio ingresoRepositorio;
    @Autowired
    private ComprobanteServicio comprobanteServicio;

    Ingreso ingreso = new Ingreso();

    @Transactional(propagation = Propagation.NESTED)
    public Ingreso agregar(Double monto, String descripcion, MultipartFile archivo) throws ErrorServicio {

        ingreso.setMonto(monto);
        ingreso.setFecha(new Date());
        ingreso.setDescripcion(descripcion);
       if (archivo != null ) {
                Comprobante comprobante = comprobanteServicio.guardar(archivo);
                ingreso.setComprobante(comprobante);
            }

        return ingresoRepositorio.save(ingreso);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void modificar(String id, Double monto, String descripcion, MultipartFile archivo) throws ErrorServicio {

        validar(monto);

        Optional<Ingreso> respuesta = ingresoRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Ingreso ingreso = respuesta.get();
            ingreso.setMonto(monto);
            ingreso.setFecha(new Date());
            ingreso.setDescripcion(descripcion);
            if (archivo != null ) {
                Comprobante comprobante = comprobanteServicio.guardar(archivo);
                ingreso.setComprobante(comprobante);
            }
            
            ingresoRepositorio.save(ingreso);

        } else {
            throw new ErrorServicio("No existe el gasto que desea modificar.");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio {

        Optional<Ingreso> respuesta = ingresoRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Ingreso ingreso = respuesta.get();
            ingresoRepositorio.delete(ingreso);
        } else {
            throw new ErrorServicio("No existe el gasto que desea eliminar.");
        }
    }

    public List<Ingreso> listar() {
        return (List<Ingreso>) ingresoRepositorio.findAll();
    }

    public void validar(Double monto) throws ErrorServicio {
        if (monto == null || monto.toString().isEmpty()) {
            throw new ErrorServicio("Debe ingresar un importe.");

        }

    }
}
