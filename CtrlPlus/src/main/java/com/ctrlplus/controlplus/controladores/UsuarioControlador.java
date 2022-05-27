package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
 
    
    @GetMapping 
   public String editarU(@RequestParam String id, ModelMap modelo){ 
       
           try {
            Usuario usuario = usuarioServicio.buscarPorID(id);
            modelo.addAttribute("usuario", usuario);
        } catch (ErrorServicio e) {
            modelo.addAttribute("error", e.getMessage());
            
            return  "index";
        }
   return  "index";
   }
    @PostMapping
    public String editarU(@RequestParam String id, ModelMap modelo, @RequestParam String nombre, @RequestParam String mail, @RequestParam String clave,@RequestParam String clave2){
        try {
          Usuario usuario= usuarioServicio.buscarPorID(id);
          usuarioServicio.modificar(id, mail, nombre, clave, clave2);
        } catch (ErrorServicio ex) {
            Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.addAttribute("error", ex.getMessage());
            modelo.addAttribute("id", id );
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("mail", mail);
        }
        return "index";
    }
    
    
    
    
    
    
    
}
