package com.example.proyecto.egg.repositorios;

import com.example.proyecto.egg.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia,String> {

    @Query("SELECT N FROM Noticia N WHERE N.titulo = :titulo")
    public Noticia buscarPorTitulo(@Param("titulo") String titulo);
}
