package finzy.vista;

import static finzy.vista.Estilos.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/** Contenedor: barra lateral, barra superior y los paneles según el rol (Menor / Tutor). */
public class VentanaPrincipal extends JFrame {

    private static final Color NAV = new Color(0xb7c3d6);
    private final CardLayout cards = new CardLayout();
    private final JPanel paginas = new JPanel(cards);
    private final JLabel titulo = label("", Font.BOLD, 22, PLUM950);
    private final Map<String, Btn> nav = new LinkedHashMap<>();

    public VentanaPrincipal(String rol, String nombre) {
        super("Finzy - " + rol);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 800); setMinimumSize(new Dimension(1060, 700)); setLocationRelativeTo(null);

        boolean menor = rol.equals("Menor");
        String inicio = menor ? "Inicio" : "Resumen";
        paginas.add(menor ? new PanelMenor(nombre, () -> mostrar("Operaciones")) : new PanelTutor(nombre), inicio);
        if (menor) paginas.add(new Operaciones(), "Operaciones");
        paginas.add(new Notificaciones(), "Notificaciones");

        JPanel p = new JPanel(new BorderLayout());
        p.add(sidebar(menor ? new String[]{"Inicio", "Operaciones", "Notificaciones"}
                            : new String[]{"Resumen", "Notificaciones"}, rol), BorderLayout.WEST);
        JPanel ws = new JPanel(new BorderLayout()); ws.setBackground(CREAM);
        ws.add(topbar(nombre), BorderLayout.NORTH);
        ws.add(paginas, BorderLayout.CENTER);
        p.add(ws, BorderLayout.CENTER);
        setContentPane(p);
        mostrar(inicio);
    }

    private void mostrar(String nombre) {
        cards.show(paginas, nombre); titulo.setText(nombre);
        nav.forEach((k, b) -> { if (k.equals(nombre)) b.style(new Color(255, 255, 255, 24), Color.WHITE, null); else b.style(null, NAV, null); });
    }

    private JPanel sidebar(String[] items, String rol) {
        JPanel s = new JPanel(); s.setLayout(new BoxLayout(s, BoxLayout.Y_AXIS));
        s.setBackground(PLUM950); s.setBorder(new EmptyBorder(30, 18, 20, 18)); s.setPreferredSize(new Dimension(230, 0));
        s.add(left(label("  Finzy", Font.BOLD, 23, Color.WHITE))); s.add(Box.createVerticalStrut(8));
        s.add(left(label("  Perfil " + rol, Font.PLAIN, 12, new Color(0x7f93ad)))); s.add(Box.createVerticalStrut(36));
        for (String it : items) {
            Btn b = new Btn("   " + it, null, NAV);
            b.setFont(font(Font.PLAIN, 14)); b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
            b.addActionListener(e -> mostrar(it));
            nav.put(it, b); s.add(left(b)); s.add(Box.createVerticalStrut(4));
        }
        s.add(Box.createVerticalGlue());
        Btn out = new Btn("↪   Cerrar sesión", null, new Color(0xa9b7cc));
        out.setFont(font(Font.PLAIN, 12)); out.setHorizontalAlignment(SwingConstants.LEFT);
        out.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        out.addActionListener(e -> { new VentanaAcceso().setVisible(true); dispose(); });
        s.add(left(out));
        return s;
    }

    private JPanel topbar(String nombre) {
        JPanel t = new JPanel(new BorderLayout()); t.setBackground(Color.WHITE);
        t.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, LINE), new EmptyBorder(0, 36, 0, 36)));
        t.setPreferredSize(new Dimension(0, 86));
        JPanel l = clear(new GridBagLayout()); l.add(titulo); t.add(l, BorderLayout.WEST);
        Round av = new Round(PLUM100, null, 36); av.setLayout(new GridBagLayout()); av.setPreferredSize(new Dimension(36, 36));
        av.add(label(nombre.substring(0, Math.min(2, nombre.length())).toUpperCase(), Font.BOLD, 11, PLUM800));
        JPanel r = clear(new GridBagLayout()); r.add(label(nombre + "   ", Font.BOLD, 12, INK)); r.add(av);
        t.add(r, BorderLayout.EAST);
        return t;
    }
}
