package com.example.demo;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.sun.jdi.connect.spi.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;
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
                "nombreApellidosEmergencia VARCHAR(100), " +
                "telefonoEmergencia VARCHAR(15), " +
                "tipoSangre VARCHAR(50)," +
                "eps VARCHAR(50), " +
                "arl VARCHAR(50), " +
                "funcionarioGestionaVisita VARCHAR(50), " +
                "traeComputoExterno VARCHAR(50), " +
                "marcaEquipo VARCHAR(50), " +
                "serialequipo VARCHAR(20)" +
                ")";

        jdbcTemplate.execute("USE " + BaseDatosEmpresa);
        jdbcTemplate.execute(crearTabla);

        String insertarDatos = "INSERT INTO " + nombreTablaPuesto + " (nombreApellidos, cedula, empresaGestionaPuesto, nombrePuesto, " +
                "nombreApellidosEmergencia, telefonoEmergencia, tipoSangre, eps, arl, " +
                "funcionarioGestionaVisita, traeComputoExterno, marcaEquipo, serialequipo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        jdbcTemplate.update(insertarDatos,
                visitante.getNombreApellidos(),
                String.valueOf(visitante.getCedula()),
                visitante.getEmpresaGestionaPuesto(),
                visitante.getNombrePuesto(),
                visitante.getNombreApellidosEmergencia(),
                visitante.getTelefonoEmergencia(),
                visitante.getTipoSangre(),
                visitante.getEps(),
                visitante.getArl(),
                visitante.getFuncionarioGestionaVisita(),
                visitante.getTraeComputoExterno(),
                visitante.getMarcaEquipo(),
                visitante.getSerialEquipo()

        );

        return  ResponseEntity.ok(Map.of("mensaje","creada tabla correctamente"));

    }


    @PostMapping("/inicioSesion")
    public ResponseEntity<Map<String, Object>> FunctionInicioSesion(@RequestBody InicioSesion inicioSesion) {
        String BaseDatos = inicioSesion.getNombreEmpresaVisitada();
        String TablaPuesto = inicioSesion.getPuestoTrabajo();
        String Contrasena = inicioSesion.getContrasenaEmpresa();

        String verificarDatos = "SELECT * FROM contrasena_clientes.iniciosesion WHERE nombreBaseDatosCliente = ? AND contrasenaBaseDatosCliente = ? ";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(verificarDatos, BaseDatos, Contrasena);

        if (!resultados.isEmpty()) {
            jdbcTemplate.execute("USE " + BaseDatos);
            String tablaDatos = "SELECT * FROM " + TablaPuesto;
            List<Map<String, Object>> resultadosDatos = jdbcTemplate.queryForList(tablaDatos);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Usuario encontrado",
                    "datos", resultadosDatos
            ));
        } else {
            return ResponseEntity.ok(Map.of("mensaje", "no se encontro el usuario"));
        }



    }





}

