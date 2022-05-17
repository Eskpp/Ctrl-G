package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.repositorios.UsuarioRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional(propagation = Propagation.NESTED)
    public Usuario registrar(String mail, String clave) throws ErrorServicio{
        
        validar(mail, clave);
        
        Usuario usuario = new Usuario();
        usuario.setMail(mail);
        usuario.setClave(clave);
        
        return usuarioRepositorio.save(usuario);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public Usuario modificar(String id, String mail, String clave) throws ErrorServicio{
        
        validar(mail, clave);
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setMail(mail);
            usuario.setClave(clave);
            return usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro un usuario con ese ID.");
        }        
    }
    
    @Transactional
    public void borrar(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuarioRepositorio.delete(usuario);
        } else {
            throw new ErrorServicio("No se encontró un usuario con ese id.");
        }
    }
    
    public void validar(String mail, String clave) throws ErrorServicio{
        if (mail == null || mail.trim().isEmpty()){
            throw new ErrorServicio("El mail no puede ser nulo");
        }
        if (clave == null || clave.trim().isEmpty()){
            throw new ErrorServicio("La clave no puede ser nula");
        }
        // agregar validaciones de formato de mail (nombre@direccion.algo)
        // agregar codificador de contraseña y que sea mas segura (max min)
    }
}
