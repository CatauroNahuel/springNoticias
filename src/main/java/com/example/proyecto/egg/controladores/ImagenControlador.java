
package com.example.proyecto.egg.controladores;


import com.example.proyecto.egg.entidades.Noticia;
import com.example.proyecto.egg.entidades.Usuario;
import com.example.proyecto.egg.servicios.NoticiaServicios;
import com.example.proyecto.egg.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {
    
    @Autowired
    NoticiaServicios noticiaServicio;
    UsuarioServicios usuarioServicio;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario (@PathVariable String id){
        Usuario usuario = usuarioServicio.getOne(id);

        byte[] imagen= usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);



        return new ResponseEntity<>(imagen,headers, HttpStatus.OK);
    }

    @GetMapping("/noticia/{id}")
    public ResponseEntity<byte[]> imagennoticia (@PathVariable String id){
        Noticia noticia = noticiaServicio.getOne(id);
        
       byte[] imagen= noticia.getImagen().getContenido();
       
       HttpHeaders headers = new HttpHeaders();
       
       headers.setContentType(MediaType.IMAGE_JPEG);
       
        
        
       return new ResponseEntity<>(imagen,headers, HttpStatus.OK); 
    }
}
