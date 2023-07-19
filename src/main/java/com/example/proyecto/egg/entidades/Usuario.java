package com.example.proyecto.egg.entidades;

import com.example.proyecto.egg.enumeraciones.Rol;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity @Data
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombreUsuario;
    private String email;
    private String password;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private Boolean activo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imagen")
    private Imagen imagen;
}

