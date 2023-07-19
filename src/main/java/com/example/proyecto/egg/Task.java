package com.example.proyecto.egg;

import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Task {
    private final static Logger Log = LoggerFactory.getLogger(Task.class);
    @Scheduled(fixedRate = 86400000)
    public void msg(){
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Log.info("Fecha: {}",dateFormat.format(new Date()));
    }
}
