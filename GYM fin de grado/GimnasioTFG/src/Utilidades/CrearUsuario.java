import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearUsuario {

    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/Gym";
        String usuario = "root";
        String contraseña = "";

        // Datos del usuario a crear
        String nombreUsuario = "nuevo_usuario";
        String contraseñaUsuario = "nueva_contraseña";

        // Consulta SQL para insertar un nuevo usuario
        String sql = "INSERT INTO Usuarios (NombreUsuario, Contraseña, Rol) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros en la consulta
            statement.setString(1, nombreUsuario);
            statement.setString(2, contraseñaUsuario);
            statement.setString(3, "Usuario"); // Asignar el rol "Usuario"

            // Ejecutar la consulta
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("El usuario ha sido creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
