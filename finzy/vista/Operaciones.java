package finzy.vista;

import static finzy.vista.Estilos.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/** Depósito, retiro y transferencia. Solo captura datos; la operación real va en la capa de lógica. */
public class Operaciones extends JPanel {

    private final CardLayout cards = new CardLayout();
    private final JPanel formas = clear(cards);

    public Operaciones() {
        setLayout(new BorderLayout());
        JPanel c = pagina();
        put(c, encabezado("Operaciones", "Deposita, retira o transfiere tu dinero."), 0);

        JPanel tabs = clear(new FlowLayout(FlowLayout.LEFT, 8, 0));
        Btn[] bs = new Btn[3]; String[] nombres = {"Depósito", "Retiro", "Transferencia"};
        for (int i = 0; i < 3; i++) {
            final int k = i; bs[i] = new Btn(nombres[i], null, INK); tabs.add(bs[i]);
            bs[i].addActionListener(e -> { cards.show(formas, nombres[k]); for (int j = 0; j < 3; j++) activo(bs[j], j == k); });
        }
        for (int j = 0; j < 3; j++) activo(bs[j], j == 0);
        put(c, tabs, 1);

        formas.add(forma("Depósito", new String[]{"Monto", "Cuenta de origen"}), "Depósito");
        formas.add(forma("Retiro", new String[]{"Monto", "Motivo"}), "Retiro");
        formas.add(forma("Transferencia", new String[]{"Monto", "Destinatario", "Mensaje (opcional)"}), "Transferencia");
        put(c, formas, 2);
        rellenar(c, 3);
        add(scroll(c));
    }

    private static void activo(Btn b, boolean sel) { b.style(sel ? PLUM100 : Color.WHITE, sel ? PLUM800 : INK, sel ? PLUM800 : LINE); }

    private Round forma(String titulo, String[] campos) {
        Round r = new Round(Color.WHITE, LINE, 18);
        r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS)); r.setBorder(new EmptyBorder(26, 30, 26, 30));
        r.add(left(label(titulo.toUpperCase(), Font.BOLD, 10, CORAL_DARK))); r.add(Box.createVerticalStrut(18));
        for (String campo : campos) {
            r.add(left(label(campo, Font.BOLD, 12, PLUM950))); r.add(Box.createVerticalStrut(6));
            JTextField f = new JTextField(); styleField(f); f.setMaximumSize(new Dimension(480, 44));
            r.add(left(f)); r.add(Box.createVerticalStrut(14));
        }
        Btn ok = new Btn("Confirmar " + titulo.toLowerCase(), CORAL, Color.WHITE);
        ok.addActionListener(e -> JOptionPane.showMessageDialog(this, titulo + " enviado (demo).", "Finzy", JOptionPane.INFORMATION_MESSAGE));
        // TODO: llamar a la capa de lógica con los datos de los campos.
        r.add(left(ok));
        return r;
    }
}
