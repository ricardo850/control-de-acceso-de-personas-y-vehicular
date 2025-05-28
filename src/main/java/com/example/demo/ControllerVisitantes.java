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
    public ResponseEntity<Map<String, String>> LlenarDatosFormulario(@RequestBody Visitante visitante){
        String nombreTablaPuesto = visitante.getNombrePuesto();
        String crearTabla = "CREATE TABLE IF NOT EXISTS " + nombreTablaPuesto + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "NombreApellidos VARCHAR(100), " +
                "cedula VARCHAR(15), " +
                "empresaGestionaPuesto VARCHAR(100)," +
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

    }



}
