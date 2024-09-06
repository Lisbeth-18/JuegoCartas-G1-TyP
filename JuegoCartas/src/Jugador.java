import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int MARGEN = 10;
    private final int DISTANCIA = 50;
    private final int TOTAL_CARTAS = 10;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() { // metodo
        int i = 0;
        for (Carta c : cartas) {
            cartas[i++] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int p = 1;
        for (Carta c : cartas) {
            c.mostrar(pnl, MARGEN + TOTAL_CARTAS * DISTANCIA - p++ * DISTANCIA, MARGEN);

        }

        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";

        // Crear un mapa para almacenar cartas por pinta
        Map<Pinta, ArrayList<Integer>> cartasPorPinta = new HashMap<>();
        for (Carta c : cartas) {
            cartasPorPinta.putIfAbsent(c.getPinta(), new ArrayList<>());
            cartasPorPinta.get(c.getPinta()).add(c.getNombre().ordinal() + 1); // +1 para ajustar el rango 1-13
        }

        // Contar cartas por nombre (para la parte original del código)
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        StringBuilder sb = new StringBuilder();

        // Revisar grupos por nombre
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                if (!hayGrupos) {
                    hayGrupos = true;
                    sb.append("Se encontraron los siguientes grupos por nombre:\n");
                }
                sb.append(Grupo.values()[contadores[i]]).append(" de ").append(NombreCarta.values()[i]).append("\n");
            }
        }

        // Revisar secuencias por pinta
        for (Map.Entry<Pinta, ArrayList<Integer>> entry : cartasPorPinta.entrySet()) {
            ArrayList<Integer> numeros = entry.getValue();
            Collections.sort(numeros);

            // Buscar secuencias de al menos 3 cartas
            int start = 0;
            while (start < numeros.size()) {
                int end = start;
                while (end < numeros.size() - 1 && numeros.get(end + 1) == numeros.get(end) + 1) {
                    end++;
                }
                if (end - start >= 2) { // Al menos 3 cartas en secuencia
                    if (!hayGrupos) {
                        hayGrupos = true;
                        sb.append("Se encontraron los siguientes grupos por secuencia:\n");
                    }
                    sb.append("Secuencia de ").append(end - start + 1).append(" cartas en ").append(entry.getKey()).append(": ");
                    for (int i = start; i <= end; i++) {
                        sb.append(NombreCarta.values()[numeros.get(i) - 1]).append(" ");
                    }
                    sb.append("\n");
                }
                start = end + 1;
            }
        }

        if (hayGrupos) {
            mensaje = sb.toString();
        }

        return mensaje;
    }
    public int calcularPuntaje() {
        // Determinar el valor de cada carta
        int[] valores = new int[NombreCarta.values().length];
        for (int i = 0; i < valores.length; i++) {
            if (NombreCarta.values()[i] == NombreCarta.AS ||
                NombreCarta.values()[i] == NombreCarta.JACK ||
                NombreCarta.values()[i] == NombreCarta.QUEEN ||
                NombreCarta.values()[i] == NombreCarta.KING) {
                valores[i] = 10;
            } else {
                valores[i] = i + 1; // Los valores corresponden al número en el nombre
            }
        }

        // Crear un mapa para almacenar el número de cartas por nombre
        Map<NombreCarta, Integer> contadores = new HashMap<>();
        for (Carta c : cartas) {
            contadores.put(c.getNombre(), contadores.getOrDefault(c.getNombre(), 0) + 1);
        }

        // Crear un conjunto de cartas que no forman grupos
        ArrayList<Carta> cartasNoEnGrupos = new ArrayList<>();
        for (Carta c : cartas) {
            if (contadores.get(c.getNombre()) < 2) { // No forma grupo si hay menos de 2 cartas con el mismo nombre
                cartasNoEnGrupos.add(c);
            }
        }

        // Calcular el puntaje de las cartas que no forman grupos
        int puntaje = 0;
        for (Carta c : cartasNoEnGrupos) {
            puntaje += valores[c.getNombre().ordinal()];
        }

        return puntaje;
    }
}

