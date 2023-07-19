package com.example.proyecto.egg.controladores;


import com.example.proyecto.egg.excepciones.MiException;
import com.example.proyecto.egg.servicios.NoticiaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticia")//localhost:8080/noticia
public class NoticiaControlador {

    @Autowired
    private NoticiaServicios noticiaServicio;

    @GetMapping("/registrar")//localhost:8080/noticia/registrar
    public String registrar(){
        return "noticiaNueva_form.html";
    }

    @PostMapping("/registrar")//localhost:8080/noticia/registro
    public String registrar(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){

        try {
            modelo.put("noticia",noticiaServicio.crearNoticia(titulo,cuerpo));
            modelo.put("exito","La noticia fue cargada correctamante");
            return "noticiaVer.html";
        } catch (MiException e) {
            modelo.put("titulo",titulo);
            modelo.put("cuerpo",cuerpo);
            modelo.put("error",e.getMessage());
            return "noticiaNueva_form.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificador(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia",noticiaServicio.getOne(id));
        return "noticiaModificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificador(@PathVariable String id,@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpo);
            modelo.put("noticia",noticiaServicio.getOne(id));
            modelo.put("exito","La noticia fue modificada correctamante");
            return "noticiaVer.html";
        } catch (MiException e) {
            modelo.put("error",e.getMessage());
            modelo.put("noticia",noticiaServicio.getOne(id));
            return "noticiaModificar.html";
        }
    }

    @GetMapping("/{id}")
    public String mostrar(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia",noticiaServicio.getOne(id));
        return "noticiaVer.html";
    }

}
