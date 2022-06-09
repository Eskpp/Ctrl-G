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

    @GetMapping("/perfil")
    public String verPerfil(){
        return "perfil";
    }
    
    @GetMapping("/editar")
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

    @PostMapping("/editar")
    public String editarU(ModelMap modelo,
            @RequestParam String id,
            @RequestParam String nombre,
            @RequestParam String mail,
            @RequestParam String clave,
            @RequestParam String clave2) {
        try {
            usuarioServicio.modificar(id, mail, nombre, clave, clave2);
            return "index";
        } catch (ErrorServicio ex) {
            modelo.addAttribute("error", ex.getMessage());
            modelo.addAttribute("id", id);
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("mail", mail);
            return "index";
        }  
    }
    
    @PostMapping("/eliminar")
    public String eliminar(ModelMap modelo,
            @RequestParam String id){
        try {
            usuarioServicio.eliminar(id);
            return "redirect:/logout";
                                                                      //ver que hacer con la session, de momento redirecciona a logout
        } catch (ErrorServicio ex) {
            modelo.addAttribute("error", ex.getMessage());
            modelo.addAttribute("id", id);
            return "index";
        }
    }

}
