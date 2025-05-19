package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllerVisitantes {

    @PostMapping("/DatosVisitantes")
    public ResponseEntity<String> guardarDatos(@RequestBody VisitanteRequestTable visitante){


        return ResponseEntity.ok("datos enviados correctamente");
    }

}
