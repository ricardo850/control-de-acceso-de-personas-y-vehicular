package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllerVisitantes {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/DatosVisitantes")
    public ResponseEntity<String> guardarDatos(@RequestBody VisitanteRequestTable visitante){
String nombrePuesto = visitante.getNombrePuesto();

String crearTabla = "CREATE TABLE IF NOT EXISTS" + nombrePuesto + "(" +
        "id INT AUTO_INCREMENT PRIMARY KEY, " +
        "nombre_apellido VARCHAR(100), "+
        "cedula INT(15)" +
        "puesto_ingreso VARCHAR(50)"+
        "empresa_permiso VARCHAR(50)" +

")";
        jdbcTemplate.execute(crearTabla);
        return ResponseEntity.ok("datos enviado");
    }

}
