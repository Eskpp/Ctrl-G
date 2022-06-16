package com.ctrlplus.controlplus.controladores;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.enums.Categoria;
import com.ctrlplus.controlplus.enums.CategoriaIngreso;
import com.ctrlplus.controlplus.errores.ErrorServicio;
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

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String login(ModelMap modelo,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String logout) {
        if (error != null) {
            modelo.addAttribute("error", "nombre de usuario o clave incorrectas.");
        }
        if (logout != null) {
            modelo.addAttribute("logout", "deslogeado satisfactoriamente");
        }
        return "login";
    }

    @PostMapping("/registro")
    public String registro(ModelMap modelo,
            @RequestParam String mail,
            @RequestParam String nombre,
            @RequestParam String clave,
            @RequestParam String clave2) {
        try {
            usuarioServicio.registrar(mail, nombre, clave, clave2);
            return "/login";                                      //googlear como logear al usuario con el registro
        } catch (ErrorServicio ex) {
            modelo.addAttribute("error", ex.getMessage());
            modelo.addAttribute("mail", mail);
            modelo.addAttribute("nombre", nombre);
            return "/login";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO')")
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo, HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado == null) {
            return "redirect:/login";
        }
        String IDusuario = logueado.getId();
        modelo.addAttribute("saldo", usuarioServicio.calcularSaldo(IDusuario));
        modelo.addAttribute("categorias", Categoria.values());
        modelo.addAttribute("categoriasingreso", CategoriaIngreso.values());
        modelo.addAttribute("movimientos", usuarioServicio.listarMovimientos(IDusuario));
        return "index";
    }

}
