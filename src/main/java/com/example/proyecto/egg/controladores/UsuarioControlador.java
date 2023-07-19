package com.example.proyecto.egg.controladores;

import com.example.proyecto.egg.excepciones.MiException;
import com.example.proyecto.egg.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")//localhost:8080/usuario
public class UsuarioControlador {

    @Autowired
    private UsuarioServicios usuarioServicios;

    @GetMapping("/registrar")
    public String registrar(){
        return "usuarioNuevo_form.html";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo){
        try {
            modelo.put("usuario", usuarioServicios.crearUsuario(nombre,email,password,password2));
            modelo.put("exito","el usuario se creo corectamente");
            return "usuarioVer.html";
        } catch (MiException e) {
            modelo.put("nombre",nombre);
            modelo.put("email",email);
            modelo.put("error",e.getMessage());
            return "usuarioNuevo_form.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificador(@PathVariable String id, ModelMap modelo){
        modelo.put("usuario",usuarioServicios.getOne(id));
        return "usuarioModificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificador(@PathVariable String id,@RequestParam String nombre, @RequestParam String email,@RequestParam String password,@RequestParam String password2, ModelMap modelo){
        try {
            usuarioServicios.modificarUsuario(id, nombre,email,password,password2);
            modelo.put("usuario",usuarioServicios.getOne(id));
            modelo.put("exito","el usuario fue modificada correctamante");
            return "usuarioVer.html";
        } catch (MiException e) {
            modelo.put("error",e.getMessage());
            modelo.put("usuario",usuarioServicios.getOne(id));
            return "usuarioModificar.html";
        }
    }

}
