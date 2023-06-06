import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnotarPR {

    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/Gym";
        String usuario = "root";
        String contraseña = "";

        // Datos del PR a anotar
        int idUsuario = 1; // ID del usuario al que se asignará el PR
        String tipoPR = "Sentadilla"; // Tipo de PR (ejemplo: Sentadilla, Press de banca, peso muerto)
        double pesoMaximo = 200.0; // Peso máximo alcanzado

        // Consulta SQL para insertar un nuevo PR
        String sql = "INSERT INTO PersonalRecords (ID_usuario, TipoPR, PesoMaximo) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros en la consulta
            statement.setInt(1, idUsuario);
            statement.setString(2, tipoPR);
            statement.setDouble(3, pesoMaximo);

            // Ejecutar la consulta
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("El PR ha sido anotado exitosamente.");
            } else {
                System.out.println("No se pudo anotar el PR.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
