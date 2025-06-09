package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class ControllerVisitantes {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/CrearBaseDatosCliente")
    public ResponseEntity<Map<String, String>> CrearBaseDatosClientes(@RequestBody BaseDatosCliente cliente) {
        String nuevaBaseDatos = cliente.getNombreBaseDatos();


        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS CONTRASENA_CLIENTES");


        String crearTablaSQL = "CREATE TABLE IF NOT EXISTS CONTRASENA_CLIENTES.INICIOSESION (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombreBaseDatosCliente VARCHAR(100)," +
                "contrasenaBaseDatosCliente VARCHAR(100)" +
                ")";
        jdbcTemplate.execute(crearTablaSQL);

        String sqlInsert = "INSERT INTO CONTRASENA_CLIENTES.INICIOSESION (nombreBaseDatosCliente, contrasenaBaseDatosCliente) VALUES (?, ?)";
        jdbcTemplate.update(sqlInsert, cliente.getNombreBaseDatos(), cliente.getContrasenaBaseDatos());

        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + nuevaBaseDatos);

        return ResponseEntity.ok(Map.of("mensaje", "Se creó la base de datos para el cliente correctamente"));
    }


    @PostMapping("/DatosVisitantes")
    public ResponseEntity<Map<String, String>> LlenarDatosFormulario(@RequestBody Visitante visitante) {
        String nombreTablaPuesto = visitante.getNombrePuesto();
        String BaseDatosEmpresa = visitante.getEmpresaGestionaPuesto();


        String crearTabla = "CREATE TABLE IF NOT EXISTS " + nombreTablaPuesto + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombreApellidos VARCHAR(100), " +
                "cedula VARCHAR(15), " +
                "empresaGestionaPuesto VARCHAR(100)," +
                "nombrePuesto VARCHAR(50), " +
                "empresaPermiso VARCHAR(50), " +
                "nombreApellidosEmergencia VARCHAR(100), " +
                "telefonoEmergencia VARCHAR(15), " +
                "eps VARCHAR(50), " +
                "arl VARCHAR(50), " +
                "funcionarioGestionaVisita VARCHAR(50), " +
                "traeComputoExterno VARCHAR(50), " +
                "marcaEquipo VARCHAR(50), " +
                "serialequipo VARCHAR(20)" +
                ")";

        jdbcTemplate.execute("USE " + BaseDatosEmpresa);
        jdbcTemplate.execute(crearTabla);

        String insertarDatos = "INSERT INTO " + nombreTablaPuesto + " (nombreApellidos, cedula, empresaGestionaPuesto, nombrePuesto, empresaPermiso, " +
                "nombreApellidosEmergencia, telefonoEmergencia, eps, arl, " +
                "funcionarioGestionaVisita, traeComputoExterno, marcaEquipo, serialequipo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        jdbcTemplate.update(insertarDatos,
                visitante.getNombreApellidos(),
                visitante.getCedula(),
                visitante.getEmpresaGestionaPuesto(),
                visitante.getNombrePuesto(),
                visitante.getEmpresaPermiso(),
                visitante.getNombreApellidosEmergencia(),
                visitante.getTelefonoEmergencia(),
                visitante.getEps(),
                visitante.getArl(),
                visitante.getFuncionarioGestionaVisita(),
                visitante.getTraeComputoExterno(),
                visitante.getMarcaEquipo(),
                visitante.getSerialEquipo()

        );

        return  ResponseEntity.ok(Map.of("mensaje","creada tabla correctamente"));

    }



}

