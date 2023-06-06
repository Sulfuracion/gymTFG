package Utilidades;

import Utilidades.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBaseDatos {

    private Constantes constantes;
    
    public void conectarBBDD() {
        Connection conex = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conex = DriverManager.getConnection(constantes.getURL_BBDD(), constantes.getUSUARIO_BBDD(), constantes.getCONTRASEÑA_BBDD());
            Statement a = conex.createStatement();
            System.out.println("Funsiona");
            
        }
        catch (SQLException e){
            System.out.println("No se ha podido añadir los datos a la base de datos");
            e.printStackTrace();
        } 
        catch (Exception e) {
            System.out.println("Error en la aplicación.");
            e.printStackTrace();
        }
    }

    public void setConstantes(Constantes constantes) {
        this.constantes = constantes;
    }

}