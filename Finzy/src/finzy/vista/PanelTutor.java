package finzy.vista;

import static finzy.vista.Estilos.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/** Panel del tutor: solicitudes del menor y resumen. Datos de ejemplo. */
public class PanelTutor extends JPanel {

    public PanelTutor(String nombre) {
        setLayout(new BorderLayout());
        JPanel c = pagina();
        put(c, encabezado("Hola, " + nombre, "Resumen del menor vinculado: Sofía."), 0);
        JPanel stats = clear(new GridLayout(1, 3, 14, 0));
        stats.add(stat("SALDO DEL MENOR", "$ 1.248.500", "Cuenta de ahorro", GREEN_SOFT, GREEN, "$"));
        stats.add(stat("GASTO SEMANAL", "$ 98.500", "-4% vs. semana anterior", CORAL_SOFT, CORAL_DARK, "↗"));
        stats.add(stat("SOLICITUDES", "3", "Pendientes de revisar", SOFT_GOLD, new Color(0x8a6300), "!"));
        put(c, stats, 1);
        put(c, columns(solicitudes(), 1.1, resumen(), .9), 2);
        rellenar(c, 3);
        add(scroll(c));
    }

    private Round solicitudes() {
        Round p = panelWithTitle("PENDIENTES", "Solicitudes");
        JPanel lista = clear(null); lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
        String[][] datos = {{"Retiro", "$ 30.000 · Entradas al cine"}, {"Transferencia", "$ 15.000 · Para Juan"}, {"Retiro", "$ 50.000 · Zapatos"}};
        for (String[] d : datos) lista.add(fila(d, lista));
        p.add(lista, BorderLayout.CENTER);
        return p;
    }

    private JPanel fila(String[] d, JPanel lista) {
        JPanel f = clear(new BorderLayout(10, 0));
        f.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, new Color(0xeef2f6)), new EmptyBorder(10, 0, 10, 0)));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 62));
        JPanel t = clear(new GridLayout(2, 1)); t.add(label(d[0], Font.BOLD, 13, INK)); t.add(label(d[1], Font.PLAIN, 11, MUTED));
        Btn ok = new Btn("Aprobar", GREEN, Color.WHITE), no = new Btn("Rechazar", null, CORAL_DARK);
        no.style(null, CORAL_DARK, LINE);
        for (Btn b : new Btn[]{ok, no}) { b.setFont(font(Font.BOLD, 11)); b.setBorder(new EmptyBorder(6, 12, 6, 12)); }
        // TODO: notificar la decisión a la capa de lógica.
        java.awt.event.ActionListener quitar = e -> { lista.remove(f); lista.revalidate(); lista.repaint(); };
        ok.addActionListener(quitar); no.addActionListener(quitar);
        JPanel a = clear(new FlowLayout(FlowLayout.RIGHT, 6, 0)); a.add(ok); a.add(no);
        f.add(t, BorderLayout.CENTER); f.add(a, BorderLayout.EAST);
        return f;
    }

    private Round resumen() {
        Round p = panelWithTitle("RESUMEN", "Gastos de la semana");
        p.add(barras(new int[]{40, 65, 30, 80, 55, 95, 45}, new String[]{"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"}, 5), BorderLayout.CENTER);
        return p;
    }
}
