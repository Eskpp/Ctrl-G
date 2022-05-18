package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.entidades.Usuario;
import com.ctrlplus.controlplus.errores.ErrorServicio;
import com.ctrlplus.controlplus.repositorios.UsuarioRepositorio;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
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
        usuario.setNombre(conseguirNombre(mail));
        usuario.setClave(clave);
        
        return usuarioRepositorio.save(usuario);
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public Usuario modificar(String id, String mail, String nombre, String clave) throws ErrorServicio{
        
        validar(mail, clave);
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setMail(mail);
            usuario.setNombre(nombre);
            usuario.setClave(clave);
            return usuarioRepositorio.save(usuario);
        } else {
            throw new ErrorServicio("No se encontro un usuario con ese ID.");
        }        
    }
    
    @Transactional(propagation = Propagation.NESTED)
    public void eliminar(String id) throws ErrorServicio{
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuarioRepositorio.delete(usuario);
        } else {
            throw new ErrorServicio("No se encontró un usuario con ese id.");
        }
    }
    
    public String conseguirNombre(String mail){
        StringTokenizer st = new StringTokenizer(mail, "@");
        return st.nextToken();
    }
    
    public void validar(String mail, String clave) throws ErrorServicio{
        
        String emailFormate = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailFormate);
        
        if (mail == null || mail.trim().isEmpty()){
            throw new ErrorServicio("El mail no puede ser nulo");
        }
        
        if (!p.matcher(mail).matches()) {
            throw new ErrorServicio("El mail no es inválido.");
        }       
        
        if (clave == null || clave.trim().isEmpty()){
            throw new ErrorServicio("La clave no puede ser nula");
        }
        // agregar codificador de contraseña y que sea mas segura (max min)
    }
}
