package com.example.proyecto.egg.servicios;

import com.example.proyecto.egg.entidades.Noticia;
import com.example.proyecto.egg.excepciones.MiException;
import com.example.proyecto.egg.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServicios {

    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public Noticia crearNoticia(String titulo, String cuerpo) throws MiException{

       validar(titulo, cuerpo);

        Noticia noticia = new Noticia();

        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);

        noticiaRepositorio.save(noticia);
        return noticia;
    }

    public List<Noticia> listarNoticias(){

        List<Noticia> noticias;

        noticias = noticiaRepositorio.findAll();

        return noticias;
    }

    @Transactional
    public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException{

        validar(titulo, cuerpo);

        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);

        if(respuesta.isPresent()){

            Noticia noticia=respuesta.get();

            noticia.setTitulo(titulo);

            noticia.setCuerpo(cuerpo);

            noticiaRepositorio.save(noticia);
        }

    }

    public Noticia getOne(String id){
        return noticiaRepositorio.getOne(id);
    }


private void validar(String titulo, String cuerpo) throws MiException{

    if(titulo==null || titulo.isEmpty()){
        throw new MiException("deve completar el titulo de la noticia");
    }

    if (cuerpo==null || cuerpo.isEmpty()){
        throw new MiException("deve completar el cuerpo de la noticia");
    }
}
}
