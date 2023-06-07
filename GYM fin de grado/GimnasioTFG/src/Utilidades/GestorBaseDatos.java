package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDatos {

    private Constantes constantes;
    private Connection conexion;

    public void conectarBBDD() {
        try {
            conexion = DriverManager.getConnection(constantes.getURL_BBDD(), constantes.getUSUARIO_BBDD(), constantes.getCONTRASEÑA_BBDD());
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }

    public void verRutinas() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = conexion.createStatement();
            String sql = "SELECT * FROM Rutinas";
            resultSet = statement.executeQuery(sql);

            // Procesar el resultado de la consulta
            while (resultSet.next()) {
                int idRutina = resultSet.getInt("ID_rutina");
                int idUsuario = resultSet.getInt("ID_usuario");
                String nombreRutina = resultSet.getString("NombreRutina");

                System.out.println("ID Rutina: " + idRutina);
                System.out.println("ID Usuario: " + idUsuario);
                System.out.println("Nombre Rutina: " + nombreRutina);
                System.out.println("--------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta SQL");
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el ResultSet");
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el Statement");
                    e.printStackTrace();
                }
            }
        }
    }

    public void setConstantes(Constantes constantes) {
        this.constantes = constantes;
    }

}

