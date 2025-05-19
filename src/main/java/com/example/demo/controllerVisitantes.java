package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllerVisitantes {

    @PostMapping("/DatosVisitantes")
    public ResponseEntity<String> guardarDatos(@RequestBody VisitanteRequestTable visitante){

     VisitanteRequestTable visitanteNuevo = new VisitanteRequestTable();
     visitanteNuevo.setNombreApellidos(visitante.getNombreApellidos());
     visitanteNuevo.setCedula(visitante.getCedula());
     visitanteNuevo.setEmpresaPermiso(visitante.getEmpresaPermiso());
     visitanteNuevo.setNombrePuesto(visitante.getNombrePuesto());

        return ResponseEntity.ok("datos enviados correctamente");
    }

}
