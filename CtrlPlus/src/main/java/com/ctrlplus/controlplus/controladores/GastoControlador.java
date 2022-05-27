package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.enums.Categoria;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.servicios.GastoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class GastoControlador {

    @Autowired
    private GastoServicio gastoServicio;

    @PostMapping
    public String ingresar(ModelMap modelo, @RequestParam() Double monto, @RequestParam(required = false) String descripcion, @RequestParam(required = false) MultipartFile archivo, @RequestParam Categoria categoria) {
        try {
            gastoServicio.agregar(monto, categoria, descripcion, archivo);

            return "index";
        } catch (ErrorServicio e) {

            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("monto", monto);
            modelo.addAttribute("categoria", categoria);
            if (descripcion != null) {
                modelo.addAttribute("descripcion", descripcion);
            }
            if (archivo != null) {
                modelo.addAttribute("comprobante", archivo);
            } //in God we trust
            return "index";
        }
    }
    
    @PostMapping
    public String modificar(ModelMap modelo, @RequestParam() Double monto, @RequestParam(required = false) String descripcion, @RequestParam(required = false) MultipartFile archivo, @RequestParam() String id, @RequestParam Categoria categoria) {
        try {

            gastoServicio.modificar(id, monto, categoria, descripcion, archivo);
            return "index";
        } catch (ErrorServicio e) {

            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("monto", monto);
            if (descripcion != null) {
                modelo.addAttribute("descripcion", descripcion);
            }
             if (archivo != null) {
                modelo.addAttribute("comprobante", archivo);
            } //in God we trust
            modelo.addAttribute("id", id);
            return "index";
        }

    }
    @GetMapping
    public String listar(ModelMap modelo) {
        gastoServicio.listar();
        return "index";// devolver donde se vea
    }
    @PostMapping
    public String eliminar(ModelMap modelo, @RequestParam String id) {

        try {
            gastoServicio.eliminar(id);
            return "index";
        } catch (ErrorServicio ex) {

            modelo.addAttribute("error", ex.getMessage());

            return "index";
        }

    }
}
