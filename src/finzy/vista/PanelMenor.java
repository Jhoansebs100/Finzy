package finzy.vista;

import static finzy.vista.Estilos.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/** Panel del menor: saldo, extracto y metas. Datos de ejemplo. */
public class PanelMenor extends JPanel {

    public PanelMenor(String nombre, Runnable irAOperaciones) {
        setLayout(new BorderLayout());
        JPanel c = pagina();
        put(c, encabezado("Hola, " + nombre, "Este es el resumen de tu dinero."), 0);
        put(c, tarjetaSaldo(irAOperaciones), 1);
        put(c, columns(extracto(), 1.15, metas(), .85), 2);
        rellenar(c, 3);
        add(scroll(c));
    }

    private Round tarjetaSaldo(Runnable irAOperaciones) {
        Round card = new Round(PLUM900, null, 28);
        card.setLayout(new BorderLayout()); card.setPreferredSize(new Dimension(0, 215));
        JPanel l = clear(null); l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS)); l.setBorder(new EmptyBorder(30, 32, 26, 20));
        l.add(left(label("SALDO DISPONIBLE", Font.BOLD, 11, new Color(0xa9b7cc)))); l.add(Box.createVerticalStrut(8));
        l.add(left(label("$ 1.248.500", Font.BOLD, 38, Color.WHITE))); l.add(Box.createVerticalStrut(4));  // TODO: saldo real
        l.add(left(label("Cuenta de ahorro · COP", Font.PLAIN, 12, new Color(0x93a3bb)))); l.add(Box.createVerticalGlue());
        Btn dep = new Btn("+  Depositar", null, Color.WHITE); dep.style(new Color(255, 255, 255, 18), Color.WHITE, new Color(255, 255, 255, 45));
        Btn env = new Btn("➤  Operaciones", CORAL, Color.WHITE);
        env.addActionListener(e -> irAOperaciones.run()); dep.addActionListener(e -> irAOperaciones.run());
        JPanel acts = clear(new FlowLayout(FlowLayout.LEFT, 0, 0)); acts.add(env); acts.add(Box.createHorizontalStrut(9)); acts.add(dep);
        l.add(left(acts));
        card.add(l, BorderLayout.CENTER);

        JPanel visual = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create(); aa(g2);
                int cx = getWidth() / 2, cy = getHeight() / 2, w = 240, h = 136, x = cx - w / 2, y = cy - h / 2;
                g2.rotate(Math.toRadians(-5), cx, cy);
                g2.setPaint(new GradientPaint(x, y, new Color(0x2a8fd0), x + w, y + h, new Color(0x5a2f9a)));
                g2.fillRoundRect(x, y, w, h, 18, 18);
                g2.setColor(new Color(255, 255, 255, 60)); g2.drawRoundRect(x, y, w, h, 18, 18);
                g2.setColor(GOLD); g2.fillRoundRect(x + 20, y + 40, 30, 22, 6, 6);
                g2.setColor(Color.WHITE); g2.setFont(font(Font.BOLD, 11)); g2.drawString("FINZY", x + 20, y + 28);
                g2.setFont(font(Font.BOLD, 14)); g2.drawString("••••  ••••  4821", x + 20, y + 90);
                g2.setColor(GOLD); g2.fillOval(x + w - 28, y - 14, 34, 34);
                g2.setColor(PLUM950); g2.setFont(font(Font.BOLD, 16)); g2.drawString("$", x + w - 16, y + 9);
                g2.dispose();
            }
        };
        visual.setOpaque(false); visual.setPreferredSize(new Dimension(400, 0));
        card.add(visual, BorderLayout.EAST);
        return card;
    }

    private Round extracto() {
        Round p = panelWithTitle("EXTRACTO", "Últimos movimientos");
        String[][] rows = {   // TODO: reemplazar por los movimientos reales
                {"☕", "Cafetería", "Hoy, 9:12 a. m.", "-$ 8.500"}, {"↓", "Mesada", "Ayer", "+$ 120.000"},
                {"✎", "Libros del colegio", "Lun, 3:40 p. m.", "-$ 45.000"}, {"★", "Premio por meta", "Dom", "+$ 20.000"}};
        JPanel list = clear(new GridLayout(rows.length, 1));
        for (String[] r : rows) list.add(fila(r[0], r[1], r[2], r[3], r[3].startsWith("+") ? GREEN : INK));
        p.add(list, BorderLayout.CENTER);
        return p;
    }

    private Round metas() {
        Round p = panelWithTitle("AHORRO", "Mis metas");
        JPanel l = clear(null); l.setLayout(new BoxLayout(l, BoxLayout.Y_AXIS));
        Object[][] metas = {{"Bicicleta nueva", .62, "$ 620.000 / $ 1.000.000"}, {"Viaje de fin de año", .30, "$ 150.000 / $ 500.000"},
                {"Audífonos", .85, "$ 170.000 / $ 200.000"}};
        for (Object[] m : metas) {
            l.add(left(label((String) m[0], Font.BOLD, 13, INK))); l.add(Box.createVerticalStrut(6));
            l.add(left(new Progress((double) m[1], PLUM100, CORAL))); l.add(Box.createVerticalStrut(4));
            l.add(left(label((String) m[2], Font.PLAIN, 11, MUTED))); l.add(Box.createVerticalStrut(16));
        }
        p.add(l, BorderLayout.CENTER);
        return p;
    }
}
