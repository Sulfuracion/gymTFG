package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearRutina {

    public void crearNuevaRutina() {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/Gym";
        String usuario = "root";
        String contraseña = "";

        // Datos de la rutina a crear
        int idUsuario = 1; // ID del usuario al que se asignará la rutina
        String nombreRutina = "Rutina de prueba";

        // Consulta SQL para insertar una nueva rutina
        String sql = "INSERT INTO Rutinas (ID_usuario, NombreRutina) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros en la consulta
            statement.setInt(1, idUsuario);
            statement.setString(2, nombreRutina);

            // Ejecutar la consulta
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("La rutina ha sido creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la rutina.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
