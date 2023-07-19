package com.example.proyecto.egg.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Periodista extends Usuario{
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "Creador",cascade = CascadeType.ALL)
    private List<Noticia> misNoticias = new ArrayList<>();
    private Integer sueldoMensual;
    private Boolean activo;
}
