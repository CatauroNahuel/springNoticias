package com.example.proyecto.egg.entidades;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity @Data
public class Noticia {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    private String cuerpo;
    @Temporal(TemporalType.DATE)
    private Date FechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periodista")
    private Periodista Creador;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imagen")
    private Imagen imagen;
}