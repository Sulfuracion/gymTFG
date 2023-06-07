package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerRutinas {

    private static Constantes constantes = new Constantes();
    private static GestorBaseDatos gestorBaseDatos = new GestorBaseDatos();

    public void mostrarRutinas() {
        // Establecer la conexión a la base de datos
        Connection conn = gestorBaseDatos.getConexion();

        if (conn != null) {
            try {
                // Consulta SQL para obtener todas las rutinas
                String sql = "SELECT * FROM Rutinas";

                // Crear un objeto Statement
                Statement statement = conn.createStatement();

                // Ejecutar la consulta SQL
                ResultSet resultSet = statement.executeQuery(sql);

                // Iterar sobre los resultados y mostrar las rutinas
                while (resultSet.next()) {
                    int idRutina = resultSet.getInt("ID_rutina");
                    int idUsuario = resultSet.getInt("ID_usuario");
                    String nombreRutina = resultSet.getString("NombreRutina");

                    System.out.println("ID Rutina: " + idRutina);
                    System.out.println("ID Usuario: " + idUsuario);
                    System.out.println("Nombre Rutina: " + nombreRutina);
                    System.out.println("-------------------------");
                }

                // Cerrar el Statement y el ResultSet
                statement.close();
                resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Cerrar la conexión a la base de datos
                gestorBaseDatos.cerrarConexion();
            }
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }
    }
}

