package finzy.vista;

import static finzy.vista.Estilos.*;
import javax.swing.*;
import java.awt.*;

/** Bandeja de notificaciones y envío de feedback. Datos de ejemplo. */
public class Notificaciones extends JPanel {

    public Notificaciones() {
        setLayout(new BorderLayout());
        JPanel c = pagina();
        put(c, encabezado("Notificaciones", "Mantente al día con tu actividad."), 0);
        put(c, columns(bandeja(), 1.1, feedback(), .9), 1);
        rellenar(c, 2);
        add(scroll(c));
    }

    private Round bandeja() {
        Round p = panelWithTitle("BANDEJA", "Recientes");
        String[][] n = {   // TODO: cargar notificaciones reales
                {"✔", "Depósito recibido", "Hace 10 min", null}, {"!", "Solicitud de retiro aprobada", "Hace 2 h", null},
                {"★", "¡Meta al 85%!", "Ayer", null}, {"✎", "Nueva lección disponible", "Lun", null}};
        JPanel l = clear(new GridLayout(n.length, 1));
        for (String[] x : n) l.add(fila(x[0], x[1], x[2], x[3], MUTED));
        p.add(l, BorderLayout.CENTER);
        return p;
    }

    private Round feedback() {
        Round p = panelWithTitle("FEEDBACK", "Cuéntanos qué piensas");
        JTextArea ta = new JTextArea(6, 20);
        ta.setFont(font(Font.PLAIN, 13)); ta.setLineWrap(true); ta.setWrapStyleWord(true);
        ta.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        JScrollPane sp = new JScrollPane(ta); sp.setBorder(new RBorder(LINE, 12));
        Btn enviar = new Btn("Enviar feedback", CORAL, Color.WHITE);
        enviar.addActionListener(e -> {   // TODO: enviar a la capa de lógica
            JOptionPane.showMessageDialog(this, "¡Gracias por tu opinión!", "Finzy", JOptionPane.INFORMATION_MESSAGE); ta.setText("");
        });
        JPanel s = clear(new FlowLayout(FlowLayout.LEFT, 0, 12)); s.add(enviar);
        p.add(sp, BorderLayout.CENTER); p.add(s, BorderLayout.SOUTH);
        return p;
    }
}
