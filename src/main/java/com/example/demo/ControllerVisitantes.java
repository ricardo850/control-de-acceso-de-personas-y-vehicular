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


@RestController
public class ControllerVisitantes {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/CrearBaseDatosCliente")
    public ResponseEntity<Map<String, String>> CrearBaseDatosClientes(@RequestBody BaseDatosCliente cliente) {
        String nuevaBaseDatos = cliente.getNombreBaseDatos();
        String Puesto = cliente.getNombreBaseDatos();

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
        jdbcTemplate.execute("USE " + nuevaBaseDatos);

        String crearTablaPuesto = "CREATE TABLE IF NOT EXISTS puestos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "puesto VARCHAR(100)" +
                ")";

        jdbcTemplate.execute(crearTablaPuesto);


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

        return  ResponseEntity.ok(Map.of("mensaje","enviado"));

    }


    @PostMapping("/inicioSesion")
    public ResponseEntity<Map<String, Object>> FunctionInicioSesion(@RequestBody InicioSesion inicioSesion) {
        String BaseDatos = inicioSesion.getNombreEmpresaVisitada();
        String Contrasena = inicioSesion.getContrasenaEmpresa();

        String validar = "true";

        String verificarDatos = "SELECT * FROM contrasena_clientes.iniciosesion WHERE nombreBaseDatosCliente = ? AND contrasenaBaseDatosCliente = ? ";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(verificarDatos, BaseDatos, Contrasena);

        if (!resultados.isEmpty()) {
            validar = "true";
            return ResponseEntity.ok(Map.of(
                    "mensaje", validar
                    ));
        } else {
            validar = "falso";
            return ResponseEntity.ok(Map.of("mensaje", validar));
        }



    }

    @GetMapping("/traerEmpresa")
    public ResponseEntity<Map<String,Object>> FunctionTraerEmpresas(){
        String tablaDatos = "SELECT * FROM contrasena_clientes.iniciosesion";
        List<Map<String, Object>> tablaExiste = jdbcTemplate.queryForList(tablaDatos);
        if(!tablaExiste.isEmpty()) {

            return ResponseEntity.ok(Map.of(
                    "mensaje", "true",
                    "datos", tablaExiste
            ));
        }else{
            return ResponseEntity.ok(Map.of(
                    "mensaje", "false"
            ));

        }
    }


    @PostMapping("/TraerPuestos")
    public ResponseEntity<Map<String,Object>> FunctionTraerPuestos(@RequestBody Visitante visitante){
        String nombreBD = visitante.getEmpresaGestionaPuesto();
        jdbcTemplate.execute("USE " + nombreBD);
        // cambia según tu caso
        String sql = "SELECT SELECT * FROM ";

        List<Map<String, Object>> tablas = jdbcTemplate.queryForList(sql, nombreBD);
        if(!tablas.isEmpty()) {

            return ResponseEntity.ok(Map.of(
                    "mensaje", "true",
                    "datos", tablas
            ));
        }else{
            return ResponseEntity.ok(Map.of(
                    "mensaje", "false"
            ));

        }
    }

    @PostMapping("/crearPuesto")
    public ResponseEntity<Map<String,Object>> FunctionCrearPuesto(@RequestBody Puesto puesto){
        String NombreBaseDatos = puesto.getNombreBaseDatosDatos();
        String NombrePuesto = puesto.getPuesto();

        jdbcTemplate.execute("USE " + NombreBaseDatos);

        String crearTabla = "CREATE TABLE IF NOT EXISTS `" + NombrePuesto + "` (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombreApellidos VARCHAR(100), " +
                "cedula VARCHAR(15), " +
                "empresaGestionaPuesto VARCHAR(100), " +
                "nombrePuesto VARCHAR(50), " +
                "nombreApellidosEmergencia VARCHAR(100), " +
                "telefonoEmergencia VARCHAR(15), " +
                "tipoSangre VARCHAR(50), " +
                "eps VARCHAR(50), " +
                "arl VARCHAR(50), " +
                "funcionarioGestionaVisita VARCHAR(50), " +
                "traeComputoExterno VARCHAR(50), " +
                "marcaEquipo VARCHAR(50), " +
                "serialequipo VARCHAR(20)" +
                ")";

        jdbcTemplate.execute(crearTabla);


        return ResponseEntity.ok(Map.of(
                "mensaje", "true"
        ));
    }


}

