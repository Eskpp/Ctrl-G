package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.enums.Categoria;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.servicios.GastoServicio;
import com.ctrlplus.controlplus.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public String ingresar(ModelMap modelo,
            HttpSession session,
            @RequestParam() Double monto,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) MultipartFile archivo,
            @RequestParam Categoria categoria) {

        Usuario logeado = (Usuario)session.getAttribute("usuariosession");
        if (logeado == null) {
            return "index";
        }  try {
            usuarioServicio.agregarGasto(logeado, gastoServicio.agregar(monto, categoria, descripcion, archivo));
            //preguntar a pilar si esta buena esta forma de vincular usuario con ingreso/registro
            //o si hacerlo en el servicio del ingreso
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
