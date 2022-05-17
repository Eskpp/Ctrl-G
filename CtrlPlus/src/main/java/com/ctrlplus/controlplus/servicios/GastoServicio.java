package com.ctrlplus.controlplus.servicios;

import com.ctrlplus.controlplus.repositorios.GastoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GastoServicio {

    @Autowired
    private GastoRepositorio gastoRepositorio;
}
