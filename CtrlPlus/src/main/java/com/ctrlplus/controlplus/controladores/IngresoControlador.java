package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.servicios.IngresoServicio;
import com.ctrlplus.controlplus.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER')")
@RequestMapping("/")
public class IngresoControlador {

    @Autowired
    private IngresoServicio ingresoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping
    public String ingresar(ModelMap modelo,
            HttpSession session,
            @RequestParam() Double monto,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) MultipartFile archivo) {

        Usuario logeado = (Usuario)session.getAttribute("usuariosession");
        if (logeado == null) {
            return "index";
        }
        try {
            usuarioServicio.agregarIngreso(logeado,ingresoServicio.agregar(monto, descripcion, archivo));
            //preguntar a pilar si esta buena esta forma de vincular usuario con ingreso/registro
            //o si hacerlo en el servicio del ingreso
            //testear metodo de vinculacion y avisar
            return "index";
        } catch (ErrorServicio e) {

            modelo.addAttribute("error", e.getMessage());
            modelo.addAttribute("monto", monto);
            if (descripcion != null) {
                modelo.addAttribute("descripcion", descripcion);
            }
            //como devolver una foto por modelo, asi el usuario no tiene que resubirla
            return "index";
        }
    }

    @PostMapping
    public String modificar(ModelMap modelo,
            @RequestParam() Double monto,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) MultipartFile archivo,
            @RequestParam() String id) {
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

    @PostMapping
    public String eliminar(ModelMap modelo, @RequestParam String id) {

        try {
            ingresoServicio.eliminar(id);
            return "index";
        } catch (ErrorServicio ex) {

            modelo.addAttribute("error", ex.getMessage());

            return "index";
        }

    }
}
