import Utilidades.*;

public class PantallaInicio {
    private static Constantes constantes = new Constantes();
    private static GestorBaseDatos gestorBaseDatos = new GestorBaseDatos();

    public static void main(String[] args) {
        gestorBaseDatos.setConstantes(constantes);

        gestorBaseDatos.conectarBBDD();

    }

}
