package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.servicios.IngresoServicio;
import java.util.Date;
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
public class IngresoControlador {

    @Autowired
    private IngresoServicio ingresoServicio;

    @PostMapping
    public String ingresar(ModelMap modelo, @RequestParam() Double monto, @RequestParam(required = false) String descripcion, @RequestParam(required = false) MultipartFile archivo) {
        try {
            ingresoServicio.agregar(monto, descripcion, archivo);

            return "index";
        } catch (ErrorServicio e) {

            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("monto", monto);
            if (descripcion != null) {
                modelo.addAttribute("descripcion", descripcion);
            }
            return "index";
        }
    }

    @PostMapping
    public String modificar(ModelMap modelo, @RequestParam() Double monto, @RequestParam(required = false) String descripcion, @RequestParam(required = false) MultipartFile archivo, @RequestParam() String id) {
        try {

            ingresoServicio.modificar(id, monto, descripcion, archivo);
            return "index";
        } catch (ErrorServicio e) {

            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("monto", monto);
            if (descripcion != null) {
                modelo.addAttribute("descripcion", descripcion);
            }
            modelo.addAttribute("id", id);
            return "index";
        }

    }

    @GetMapping
    public String listar(ModelMap modelo) {
        ingresoServicio.listar(); 
        return "index";// devolver donde se vea
    }

}
