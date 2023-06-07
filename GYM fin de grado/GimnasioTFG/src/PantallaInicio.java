import Utilidades.*;

import javax.swing.*;

public class PantallaInicio {
    private static Constantes constantes = new Constantes();
    private static GestorBaseDatos gestorBaseDatos = new GestorBaseDatos();
    private static JButton NuevaRutina;
    private static JPanel Selector;
    private static JButton VerRutinas;

    public static void main(String[] args) {
        gestorBaseDatos.setConstantes(constantes);

        gestorBaseDatos.conectarBBDD();

        // ActionListener para el botón "NuevaRutina"
        NuevaRutina.addActionListener(e -> {
            CrearRutina crearRutina = new CrearRutina();
            crearRutina.crearNuevaRutina();
        });

        // ActionListener para el botón "VerRutinas"
        VerRutinas.addActionListener(e -> {
            VerRutinas verRutinas = new VerRutinas();
            verRutinas.mostrarRutinas();
        });

    }

}
