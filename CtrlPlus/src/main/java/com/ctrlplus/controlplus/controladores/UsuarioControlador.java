package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER')")
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping()
    public String verPerfil(){
        return "perfil";
    }
    
    @GetMapping()
    public String editarU(@RequestParam String id, ModelMap modelo) {

        try {
            Usuario usuario = usuarioServicio.buscarPorID(id);
            modelo.addAttribute("usuario", usuario);
        } catch (ErrorServicio e) {
            modelo.addAttribute("error", e.getMessage());

            return "index";
        }
        return "index";
    }

    @PostMapping()
    public String editarU(ModelMap modelo,
            @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String mail,
            @RequestParam String clave,
            @RequestParam String clave2) {
        try {
            usuarioServicio.modificar(id, mail, nombre, clave, clave2);
        } catch (ErrorServicio ex) {
            modelo.addAttribute("error", ex.getMessage());
            modelo.addAttribute("id", id);
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("mail", mail);
        }
        return "index";
    }

}
