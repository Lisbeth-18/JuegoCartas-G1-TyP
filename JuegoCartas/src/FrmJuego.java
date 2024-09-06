import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class FrmJuego extends JFrame {

    private JButton btnRepartir;
    private JButton btnVerificar;
    private JButton btnPuntaje;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    private JTabbedPane tpJugadores;

    Jugador jugador1, jugador2; // objetos clase jugador

    public FrmJuego() {
        btnRepartir = new JButton();
        btnVerificar = new JButton();
        btnPuntaje = new JButton();
        tpJugadores = new JTabbedPane();
        pnlJugador1 = new JPanel();
        pnlJugador2 = new JPanel();

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlJugador1.setBackground(new Color(246, 128, 225));
        pnlJugador1.setLayout(null);
        pnlJugador2.setBackground(new Color(128, 246, 233));
        pnlJugador2.setLayout(null);

        tpJugadores.setBounds(10, 40, 550, 170);
        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raul Vidal", pnlJugador2);

        btnRepartir.setBounds(10, 10, 100, 25);
        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRepartirClick(evt);
            }
        });

        btnVerificar.setBounds(120, 10, 100, 25);
        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnVerificarClick(evt);
            }
        });

        btnPuntaje.setBounds(230, 10, 100, 25);
        btnPuntaje.setText("Puntaje");
        btnPuntaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPuntajeClick(evt);
            }
        });

        getContentPane().setLayout(null);
        getContentPane().add(tpJugadores);
        getContentPane().add(btnRepartir);
        getContentPane().add(btnVerificar);
        getContentPane().add(btnPuntaje);

        jugador1 = new Jugador();
        jugador2 = new Jugador();

    }

    private void btnRepartirClick(ActionEvent evt) {

        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void btnVerificarClick(ActionEvent evt) {
        switch (tpJugadores.getSelectedIndex()) {
            case 0:

                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                break;

        }
    }

    private void btnPuntajeClick(ActionEvent evt) {
        switch (tpJugadores.getSelectedIndex()) {
            case 0:

                JOptionPane.showMessageDialog(null, jugador1.calcularPuntaje());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.calcularPuntaje());
                break;

        }
    }

}