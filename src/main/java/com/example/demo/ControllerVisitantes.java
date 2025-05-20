package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController
public class ControllerVisitantes {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/DatosVisitantes")
    public ResponseEntity<String> guardarDatos(@RequestBody VisitanteRequestTable visitante) {
        String nombrePuesto = visitante.getNombrePuesto();

        String crearTabla = "CREATE TABLE IF NOT EXISTS " + nombrePuesto + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "NombreApellidos VARCHAR(100), " +
                "cedula VARCHAR(15), " +
                "NombrePuesto VARCHAR(50), " +
                "EmpresaPermiso VARCHAR(50), " +
                "NombreApellidosEmergencia VARCHAR(100), " +
                "TelefonoEmergencia VARCHAR(15), " +
                "Eps VARCHAR(50), " +
                "Arl VARCHAR(50), " +
                "FuncionarioGestionaVisita VARCHAR(50), " +
                "TraeComputoExterno VARCHAR(50), " +
                "MarcaEquipo VARCHAR(50), " +
                "Serialequipo VARCHAR(20)" +
                ")";

        jdbcTemplate.execute(crearTabla);
        String insertarDatos = "INSERT INTO " + nombrePuesto + "(" + "NombreApellidos, cedula, NombrePuesto, EmpresaPermiso, " +
                "NombreApellidosEmergencia, TelefonoEmergencia, Eps, Arl, " +
                "FuncionarioGestionaVisita, TraeComputoExterno, MarcaEquipo, Serialequipo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertarDatos,
                visitante.getNombreApellidos(),
                visitante.getCedula(),
                visitante.getNombrePuesto(),
                visitante.getEmpresaPermiso(),
                visitante.getNombreApellidosEmergencia(),
                visitante.getTelefonoEmergencia(),
                visitante.getEps(),
                visitante.getArl(),
                visitante.getFuncionarioGestionaVisita(),
                visitante.getTraeComputoExterno(),
                visitante.getMarcaEquipo(),
                visitante.getSerialequipo()

        );

return ResponseEntity.ok("se guardaron los datos y se creo la abse de datos");

    }
}
