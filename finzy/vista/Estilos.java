package finzy.vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public final class Estilos {
    private Estilos() {}

    public static final Color PLUM950 = new Color(0x071426), PLUM900 = new Color(0x0b2344),
            PLUM800 = new Color(0x7627c8), PLUM100 = new Color(0xe9e5fa),
            CORAL = new Color(0x1477e6), CORAL_DARK = new Color(0x095fc5), CORAL_SOFT = new Color(0xe0f3ff),
            CREAM = new Color(0xf2f7fb), INK = new Color(0x142033), MUTED = new Color(0x6f7b8d),
            LINE = new Color(0xdce5ee), GREEN = new Color(0x0787a7), GREEN_SOFT = new Color(0xdff5fa),
            GOLD = new Color(0xf5b51b), SOFT_GOLD = new Color(0xfff3cc);

    public static Font font(int style, int size) { return new Font("DM Sans", style, size); }

    public static JLabel label(String t, int style, int size, Color c) {
        JLabel l = new JLabel(t); l.setFont(font(style, size)); l.setForeground(c); return l;
    }

    public static <T extends JComponent> T left(T c) { c.setAlignmentX(Component.LEFT_ALIGNMENT); return c; }

    public static JPanel clear(LayoutManager lm) { JPanel p = new JPanel(lm); p.setOpaque(false); return p; }

    public static void aa(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /** Panel con esquinas redondeadas. */
    public static class Round extends JPanel {
        private final Color bg, border; private final int arc;
        public Round(Color bg, Color border, int arc) { this.bg = bg; this.border = border; this.arc = arc; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create(); aa(g2);
            g2.setColor(bg); g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
            if (border != null) { g2.setColor(border); g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc); }
            g2.dispose(); super.paintComponent(g);
        }
    }

    /** Borde redondeado para campos de texto. */
    public static class RBorder extends AbstractBorder {
        private final Color c; private final int arc;
        public RBorder(Color c, int arc) { this.c = c; this.arc = arc; }
        @Override public void paintBorder(Component comp, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create(); aa(g2);
            g2.setColor(c); g2.drawRoundRect(x, y, w - 1, h - 1, arc, arc); g2.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(6, 12, 6, 12); }
    }

    /** Botón redondeado con hover. */
    public static class Btn extends JButton {
        private Color bg, line;
        public Btn(String t, Color bg, Color fg) {
            super(t); this.bg = bg; setForeground(fg); setFont(font(Font.BOLD, 13));
            setContentAreaFilled(false); setBorderPainted(false); setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(10, 18, 10, 18));
        }
        public void style(Color bg, Color fg, Color line) { this.bg = bg; setForeground(fg); this.line = line; repaint(); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create(); aa(g2);
            int w = getWidth() - 1, h = getHeight() - 1;
            if (bg != null) { g2.setColor(bg); g2.fillRoundRect(0, 0, w, h, 14, 14); }
            if (getModel().isRollover()) {
                g2.setColor(bg == null ? new Color(255, 255, 255, 14) : new Color(0, 0, 0, 20));
                g2.fillRoundRect(0, 0, w, h, 14, 14);
            }
            if (line != null) { g2.setColor(line); g2.drawRoundRect(0, 0, w, h, 14, 14); }
            g2.dispose(); super.paintComponent(g);
        }
    }

    /** Barra de progreso fina (0..1). */
    public static class Progress extends JComponent {
        private final double v; private final Color track, fill;
        public Progress(double v, Color track, Color fill) {
            this.v = v; this.track = track; this.fill = fill;
            setPreferredSize(new Dimension(180, 6)); setMaximumSize(new Dimension(Integer.MAX_VALUE, 6));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create(); aa(g2);
            g2.setColor(track); g2.fillRoundRect(0, 0, getWidth(), 6, 6, 6);
            g2.setColor(fill); g2.fillRoundRect(0, 0, (int) (getWidth() * v), 6, 6, 6);
            g2.dispose();
        }
    }

    // ---- Estructura de páginas ----
    public static JPanel pagina() {
        JPanel c = new JPanel(new GridBagLayout()); c.setBackground(CREAM);
        c.setBorder(new EmptyBorder(26, 34, 40, 34)); return c;
    }

    public static void put(JPanel p, Component c, int row) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridy = row; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.insets = new Insets(0, 0, 16, 0);
        p.add(c, g);
    }

    public static void rellenar(JPanel p, int row) {
        GridBagConstraints f = new GridBagConstraints(); f.gridy = row; f.weighty = 1; p.add(Box.createGlue(), f);
    }

    public static JScrollPane scroll(JPanel c) {
        JScrollPane sp = new JScrollPane(c); sp.setBorder(null); sp.getVerticalScrollBar().setUnitIncrement(16); return sp;
    }

    public static JPanel encabezado(String titulo, String sub) {
        JPanel t = clear(new GridLayout(2, 1, 0, 4));
        t.add(label(titulo, Font.BOLD, 26, PLUM950));
        t.add(label(sub, Font.PLAIN, 13, MUTED));
        return t;
    }

    public static JPanel columns(Component a, double wa, Component b, double wb) {
        JPanel p = clear(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints(); g.fill = GridBagConstraints.BOTH; g.weighty = 1;
        g.weightx = wa; g.insets = new Insets(0, 0, 0, 7); p.add(a, g);
        g.weightx = wb; g.insets = new Insets(0, 7, 0, 0); p.add(b, g);
        return p;
    }

    public static Round panelWithTitle(String kicker, String title) {
        Round p = new Round(Color.WHITE, LINE, 18);
        p.setLayout(new BorderLayout()); p.setBorder(new EmptyBorder(18, 20, 14, 20));
        JPanel h = clear(new GridLayout(2, 1, 0, 3)); h.setBorder(new EmptyBorder(0, 0, 10, 0));
        h.add(label(kicker, Font.BOLD, 10, CORAL_DARK)); h.add(label(title, Font.BOLD, 16, PLUM950));
        p.add(h, BorderLayout.NORTH);
        return p;
    }

    public static Round stat(String title, String value, String note, Color ibg, Color ifg, String sym) {
        Round r = new Round(Color.WHITE, LINE, 18);
        r.setLayout(new BorderLayout(14, 0)); r.setBorder(new EmptyBorder(18, 18, 18, 18));
        Round ic = new Round(ibg, null, 14); ic.setLayout(new GridBagLayout());
        ic.setPreferredSize(new Dimension(46, 46)); ic.add(label(sym, Font.BOLD, 18, ifg));
        JPanel w = clear(new GridBagLayout()); w.add(ic);
        JPanel t = clear(new GridLayout(3, 1));
        t.add(label(title, Font.BOLD, 11, MUTED)); t.add(label(value, Font.BOLD, 21, PLUM950)); t.add(label(note, Font.PLAIN, 11, MUTED));
        r.add(w, BorderLayout.WEST); r.add(t, BorderLayout.CENTER);
        return r;
    }

    /** Fila de icono + título + subtítulo + valor a la derecha (movimientos, notificaciones). */
    public static JPanel fila(String icono, String titulo, String sub, String derecha, Color colorDerecha) {
        JPanel row = clear(new BorderLayout(12, 0));
        row.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, new Color(0xeef2f6)), new EmptyBorder(8, 0, 8, 0)));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        Round ic = new Round(PLUM100, null, 12); ic.setLayout(new GridBagLayout());
        ic.setPreferredSize(new Dimension(34, 34)); ic.add(label(icono, Font.BOLD, 14, PLUM800));
        JPanel w = clear(new GridBagLayout()); w.add(ic);
        JPanel t = clear(new GridLayout(2, 1));
        t.add(label(titulo, Font.BOLD, 13, INK)); t.add(label(sub, Font.PLAIN, 11, MUTED));
        row.add(w, BorderLayout.WEST); row.add(t, BorderLayout.CENTER);
        if (derecha != null) row.add(label(derecha, Font.BOLD, 13, colorDerecha), BorderLayout.EAST);
        return row;
    }

    public static void styleField(JTextField f) {
        f.setFont(font(Font.PLAIN, 14)); f.setForeground(INK); f.setBorder(new RBorder(LINE, 12));
        f.setPreferredSize(new Dimension(100, 44)); f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
    }

    public static JPanel barras(int[] vals, String[] dias, int resaltado) {
        JPanel chart = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create(); aa(g2);
                int w = getWidth(), h = getHeight() - 22;
                g2.setColor(new Color(0xeef2f6));
                for (int i = 0; i <= 4; i++) g2.drawLine(0, i * h / 4, w, i * h / 4);
                g2.setColor(LINE); g2.drawLine(0, h, w, h);
                int slot = w / vals.length, bw = 30;
                g2.setFont(font(Font.PLAIN, 11));
                for (int i = 0; i < vals.length; i++) {
                    int bh = (int) (h * vals[i] / 100.0), x = i * slot + (slot - bw) / 2;
                    g2.setColor(i == resaltado ? CORAL : PLUM100); g2.fillRoundRect(x, h - bh, bw, bh, 10, 10);
                    g2.setColor(MUTED);
                    g2.drawString(dias[i], i * slot + (slot - g2.getFontMetrics().stringWidth(dias[i])) / 2, h + 16);
                }
                g2.dispose();
            }
        };
        chart.setOpaque(false); chart.setPreferredSize(new Dimension(300, 190));
        return chart;
    }
}
