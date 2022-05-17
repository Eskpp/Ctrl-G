package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.repositorios.IngresoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoServicio {

    @Autowired
    private IngresoRepositorio ingresoRepositorio;
}
