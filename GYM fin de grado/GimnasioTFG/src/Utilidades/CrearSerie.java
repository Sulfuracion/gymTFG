import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearSerie {

    public static void main(String[] args) {
        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost/Gym";
        String usuario = "root";
        String contraseña = "";

        // Datos de la serie a crear
        int idRutina = 1; // ID de la rutina a la que se asignará la serie
        int numeroSerie = 1; // Número de serie
        int repeticiones = 10; // Cantidad de repeticiones
        double peso = 50.0; // Peso utilizado

        // Consulta SQL para insertar una nueva serie
        String sql = "INSERT INTO Series (ID_rutina, NumeroSerie, Repeticiones, Peso) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros en la consulta
            statement.setInt(1, idRutina);
            statement.setInt(2, numeroSerie);
            statement.setInt(3, repeticiones);
            statement.setDouble(4, peso);

            // Ejecutar la consulta
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("La serie ha sido creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la serie.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
