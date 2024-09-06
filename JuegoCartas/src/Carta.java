import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {

    private int indice; // declarar

    public Carta(Random r) {
        indice = r.nextInt(52) + 1; // inicializar
    }

    public void mostrar(JPanel pnl, int x, int y) { // parametro (panel de cartas y coordenadas dentro del panel)
        String nombreImagen = "/imagenes/CARTA" + String.valueOf(indice) + ".jpg";
        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen)); // cargar imagen

        JLabel lbl = new JLabel(imagen); // mostrar carta
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());

        pnl.add(lbl);

    }

    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;

        if (residuo == 0) {
            residuo = 13; // para que me muestre la posicion 12
        }
        return NombreCarta.values()[residuo - 1];

    }

}
