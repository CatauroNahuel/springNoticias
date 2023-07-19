package com.example.proyecto.egg.controladores;

import com.example.proyecto.egg.entidades.Noticia;
import com.example.proyecto.egg.entidades.Periodista;
import com.example.proyecto.egg.entidades.Usuario;
import com.example.proyecto.egg.servicios.PeriodistaServicios;
import com.example.proyecto.egg.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private UsuarioServicios usuarioServicios;

    @Autowired
    private PeriodistaServicios periodistaServicios;

    @GetMapping("/dashboard")
    public String dashboard(ModelMap modelo){
        return "panelAdmin.html";
    }

    @GetMapping("/usuarios")//localhost:8080
    public String listaUsuarios(ModelMap modelo){
        List<Usuario> usuarios = usuarioServicios.listarUsuarios();
        modelo.put("usuarios",usuarios);
        return "usuarioLista.html";
    }

    @GetMapping("/usuario/{id}")
    public String vistaUsuario(@PathVariable String id,ModelMap modelo){
        Usuario usuario = usuarioServicios.getOne(id);
        modelo.put("usuario",usuario);
        return "usuarioVer.html";
    }

    @GetMapping("/alta.periodista/{id}")
    public String altaPeriodista(@PathVariable String id){
        periodistaServicios.combertirEnPeriodista(usuarioServicios.getOne(id));
        return ("redirect:/admin/usuario/" + id);
    }

    @GetMapping("/baja.periodista/{id}")
    public String bajaPeriodista(@PathVariable String id){
        periodistaServicios.combertirEnUsuario((Periodista) usuarioServicios.getOne(id));
        return ("redirect:/admin/usuario/" + id);
    }
}
