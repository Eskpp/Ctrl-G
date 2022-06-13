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
@PreAuthorize("hasAnyRole('ROLE_USUARIO')")
@RequestMapping("/ingreso")
public class IngresoControlador {

    @Autowired
    private IngresoServicio ingresoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/ingresar")
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
            ingresoServicio.agregar(monto, descripcion, logeado, archivo);
           
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

    @PostMapping("/modificar")
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

    @GetMapping("/listar")
    public String listar(ModelMap modelo, HttpSession session) {
        Usuario logeado = (Usuario)session.getAttribute("usuariosession");
        if (logeado == null) {
            return "/login";
        }
        modelo.addAttribute("ingresos", ingresoServicio.listar(logeado.getId()));
        return "ingresos";
    }

    @PostMapping("/eliminar")
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
