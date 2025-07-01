package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;


@RestController
public class ControllerVisitantes {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/insertarDatosInicioSesion")
    public ResponseEntity<Map<String, String>> CrearBaseDatosClientes(@RequestBody InicioSesion inicioSesion) {
        String nombresUsuarios = inicioSesion.getNombres();
        String apellidosUsuarios = inicioSesion.getApellidos();
        String empresaUsuario = inicioSesion.getEmpresa();
        String correoUsuario = inicioSesion.getCorreo();
        String contrasenaUsuario = inicioSesion.getContrasena();


        String sql = "INSERT INTO contrasena_clientes.iniciosesion (nombres,apellidos,empresa,correo,contrasena)VALUES(?,?,?,?,?)";

        jdbcTemplate.update(sql,nombresUsuarios,apellidosUsuarios,empresaUsuario,correoUsuario,contrasenaUsuario);

        String sqlBaseDatos = "CREATE DATABASE IF NOT EXISTS " + empresaUsuario;
        jdbcTemplate.execute(sqlBaseDatos);
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/" + empresaUsuario, "root", "")) {

            Statement stmt = conn.createStatement();

            String crearTabla = "CREATE TABLE IF NOT EXISTS puestos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "puesto VARCHAR(100), " +
                    "contrasena VARCHAR(100))";

            stmt.execute(crearTabla);

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "No se pudo crear la tabla en la nueva base de datos"));
        }
        return  ResponseEntity.ok(Map.of("mensaje" , "ok"));
    }


    @PostMapping("/DatosVisitantes")
    public ResponseEntity<Map<String, String>> LlenarDatosFormulario(@RequestBody Visitante visitante) {
        String nombreTablaPuesto = visitante.getNombrePuesto();
        String baseDatosEmpresa = visitante.getEmpresaGestionaPuesto();

        // Protege nombres dinámicos con backticks para evitar errores por caracteres inválidos
        String tablaCompleta = "`" + baseDatosEmpresa + "`.`" + nombreTablaPuesto + "`";

        String crearTabla = "CREATE TABLE IF NOT EXISTS " + tablaCompleta + " (" +
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

        String insertarDatos = "INSERT INTO " + tablaCompleta + " (nombreApellidos, cedula, empresaGestionaPuesto, nombrePuesto, " +
                "nombreApellidosEmergencia, telefonoEmergencia, tipoSangre, eps, arl, " +
                "funcionarioGestionaVisita, traeComputoExterno, marcaEquipo, serialequipo" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(insertarDatos,
                visitante.getNombreApellidos(),
                visitante.getCedula(),
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

        return ResponseEntity.ok(Map.of("mensaje", "enviado"));
    }


    @PostMapping("/inicioSesion")
    public ResponseEntity<Map<String, Object>> FunctionInicioSesion(@RequestBody InicioSesion inicioSesion) {
        String correo = inicioSesion.getCorreo();
        String Contrasena = inicioSesion.getContrasena();
        String empresa = inicioSesion.getEmpresa();

        String validar = "true";

        String verificarDatos = "SELECT * FROM contrasena_clientes.iniciosesion WHERE empresa = ? AND correo = ? AND contrasena = ? ";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(verificarDatos, empresa, correo, Contrasena);

        if (!resultados.isEmpty()) {
            validar = "true";
            return ResponseEntity.ok(Map.of(
                    "mensaje", validar,
                    "datos", resultados
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


        String sql = "SELECT * FROM `" + nombreBD + "`.puestos";
        List<Map<String, Object>> tablas = jdbcTemplate.queryForList(sql);
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
        String NombrePuesto = puesto.getNombrepuesto();
        String ContrasenaPuesto = puesto.getContrasena();

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


        String crearTablaVehiculos = "CREATE TABLE IF NOT EXISTS `" + "vehiculos_" + NombrePuesto + "` (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombreApellidoIngreso VARCHAR(100), " +
                "cedula VARCHAR(15), " +
                "empresaGestionaPuesto VARCHAR(100), " +
                "nombrePuesto VARCHAR(50), " +
                "tipoVehiculo VARCHAR(50), " +
                "numeroPlaca VARCHAR(100), " +
                "observacion VARCHAR(100)" +
                ")";


        jdbcTemplate.execute(crearTablaVehiculos);

        String insertarDatos = "INSERT INTO puestos (puesto,contrasena) VALUES (?,?)";

        jdbcTemplate.update(insertarDatos,NombrePuesto,ContrasenaPuesto);
        return ResponseEntity.ok(Map.of(
                "mensaje", "true"
        ));
    }

    @PostMapping("/TraerPuestosInformacion")
    public ResponseEntity<Map<String,Object>> FunctionTraerPuestosInformacion(@RequestBody Puesto puesto) {
        String nombreBD = puesto.getNombreBaseDatosDatos();
        String nombrePuesto = puesto.getNombrepuesto();
        String contrasena = puesto.getContrasena();

        if (!nombreBD.matches("^[a-zA-Z0-9_]+$") || !nombrePuesto.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Nombre de base de datos o tabla inválido");
        }

        String sql = "SELECT * FROM `" + nombreBD + "`.puestos WHERE puesto = ? AND contrasena = ?";
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql, nombrePuesto, contrasena);
        if (!resultados.isEmpty()) {
            String sqlDatos = "SELECT * FROM `" + nombreBD + "`.`" + nombrePuesto + "`";
            List<Map<String, Object>> resultadosDatos = jdbcTemplate.queryForList(sqlDatos);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "ok",
                    "datos", resultadosDatos
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "false"
            ));

        }
    }


        @PostMapping("/EnviarVehiculos")
        public ResponseEntity<Map<String,Object>> FunctionVehiculos(@RequestBody Vehiculos vehiculos){
           String nombreApellidoIngreso = vehiculos.getNombreApellidoIngreso();
           String cedula = vehiculos.getCedula();
           String empresaGestionaPuesto = vehiculos.getEmpresaGestionaPuesto();
           String nombrePuesto = vehiculos.getNombrePuesto();
           String tipoVehiculo = vehiculos.getTipoVehiculo();
           String numeroPlaca = vehiculos.getNumeroPlaca();
           String observacion = vehiculos.getObservacion();

            String insertarDatos = "INSERT INTO " + empresaGestionaPuesto + "." + "vehiculos_" + nombrePuesto + "(nombreApellidoIngreso,cedula,empresaGestionaPuesto,nombrePuesto,tipoVehiculo,numeroPlaca,observacion) VALUES (?,?,?,?,?,?,?)";
            jdbcTemplate.update(insertarDatos,nombreApellidoIngreso,cedula,empresaGestionaPuesto,nombrePuesto,tipoVehiculo,numeroPlaca,observacion);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "ok"
            ));
        }


    @PostMapping("/VerPuestosInformacion")
    public ResponseEntity<Map<String,Object>> FunctionVerPuestosInformacion(@RequestBody Puesto puesto) {
        String nombreBD = puesto.getNombreBaseDatosDatos();
        String nombrePuesto = puesto.getNombrepuesto();

        if (!nombreBD.matches("^[a-zA-Z0-9_]+$") || !nombrePuesto.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Nombre de base de datos o tabla inválido");
        }

        String sql = "SELECT * FROM `" + nombreBD + "`.puestos WHERE puesto = ? ";
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql, nombrePuesto);
        if (!resultados.isEmpty()) {
            String sqlDatos = "SELECT * FROM `" + nombreBD + "`.`" + nombrePuesto + "`";
            List<Map<String, Object>> resultadosDatos = jdbcTemplate.queryForList(sqlDatos);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "ok",
                    "datos", resultadosDatos
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "false"
            ));

        }
    }


    @PostMapping("/verVehiculos")
    public ResponseEntity<Map<String,Object>> FunctionVerVehiculos(@RequestBody Vehiculos vehiculos){
        String nombreBD = vehiculos.getEmpresaGestionaPuesto();
        String nombrePuesto = vehiculos.getNombrePuesto();

        if (!nombreBD.matches("^[a-zA-Z0-9_]+$") || !nombrePuesto.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Nombre de base de datos o tabla inválido");
        }

        String sql = "SELECT * FROM `" + nombreBD + "`.puestos WHERE puesto = ? ";
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql, nombrePuesto);
        if (!resultados.isEmpty()) {
            String sqlDatos = "SELECT * FROM `" + nombreBD + "`.`vehiculos_" + nombrePuesto + "`";
            List<Map<String, Object>> resultadosDatos = jdbcTemplate.queryForList(sqlDatos);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "ok",
                    "datos", resultadosDatos
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "false"
            ));

        }

    }



    @PostMapping("/inicioSesionAdministrador")
    public ResponseEntity<Map<String, Object>> FunctionInicioSesionAdministrador(@RequestBody InicioSesionAdministrador inicioSesion) {
        String correo = inicioSesion.getCorreo();
        String Contrasena = inicioSesion.getContrasena();


        String validar = "true";

        String verificarDatos = "SELECT * FROM contrasena_clientes.iniciosesion_administrador WHERE correo = ? AND contrasena = ? ";

        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(verificarDatos, correo, Contrasena);

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


}

