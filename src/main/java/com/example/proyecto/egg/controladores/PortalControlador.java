package com.example.proyecto.egg.controladores;

import com.example.proyecto.egg.entidades.Noticia;
import com.example.proyecto.egg.servicios.NoticiaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")//localhost:8080
public class PortalControlador {

    @Autowired
    private NoticiaServicios noticiaServicio;

    @GetMapping("/")//localhost:8080
    public String index(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.put("noticias",noticias);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if (error!=null){
            modelo.put("error", "usuario o contraseña incorectos");
        }
        return "login.html";
    }

    @GetMapping("/inicio")
    public String inicio(ModelMap modelo){
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.put("noticias",noticias);
        return "inicio.html";
    }
}
