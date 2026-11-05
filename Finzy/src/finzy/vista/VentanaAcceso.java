package finzy.vista;

import static finzy.vista.Estilos.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/** Ventana de registro / login. Solo interfaz: la validación real va en otra capa. */
public class VentanaAcceso extends JFrame {

    private boolean registro = false;
    private String rol = "Menor";
    private final JTextField nombre = new JTextField(), correo = new JTextField();
    private final JPasswordField clave = new JPasswordField();
    private final JLabel titulo = label("", Font.BOLD, 30, PLUM950);
    private final JLabel subtitulo = label("", Font.PLAIN, 14, MUTED);
    private final JLabel lblNombre = label("Nombre completo", Font.BOLD, 12, PLUM950);
    private final Component espNombre = Box.createVerticalStrut(14);
    private final Btn enviar = new Btn("", CORAL, Color.WHITE);
    private final Btn cambiar = new Btn("", null, CORAL_DARK);

    public VentanaAcceso() {
        super("Finzy - Acceso");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);
        JPanel p = new JPanel(new BorderLayout());
        p.add(panelLateral(), BorderLayout.WEST);
        p.add(formulario(), BorderLayout.CENTER);
        setContentPane(p);
        actualizar();
    }

    private JPanel panelLateral() {
        JPanel a = new JPanel();
        a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
        a.setBackground(PLUM950); a.setBorder(new EmptyBorder(40, 50, 40, 50));
        a.setPreferredSize(new Dimension(440, 0));
        a.add(left(label("Finzy", Font.BOLD, 28, Color.WHITE)));
        a.add(Box.createVerticalGlue());
        a.add(left(label("<html><div style='width:320px'>Aprende a cuidar<br>tu dinero desde hoy.</div></html>", Font.BOLD, 36, Color.WHITE)));
        a.add(Box.createVerticalStrut(16));
        a.add(left(label("<html><div style='width:320px'>Ahorra, envía y aprende con un tutor que te acompaña en cada paso.</div></html>",
                Font.PLAIN, 15, new Color(0xbfc9d9))));
        a.add(Box.createVerticalGlue());
        return a;
    }

    private JPanel formulario() {
        JPanel side = new JPanel(new GridBagLayout()); side.setBackground(Color.WHITE);
        JPanel f = new JPanel(); f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS));
        f.setOpaque(false); f.setPreferredSize(new Dimension(460, 560));

        f.add(left(titulo)); f.add(Box.createVerticalStrut(8)); f.add(left(subtitulo)); f.add(Box.createVerticalStrut(24));
        f.add(left(label("Soy", Font.BOLD, 12, PLUM950))); f.add(Box.createVerticalStrut(7));
        Btn menor = new Btn("Menor", null, INK), tutor = new Btn("Tutor", null, INK);
        estiloRol(menor, true); estiloRol(tutor, false);
        menor.addActionListener(e -> { rol = "Menor"; estiloRol(menor, true); estiloRol(tutor, false); });
        tutor.addActionListener(e -> { rol = "Tutor"; estiloRol(menor, false); estiloRol(tutor, true); });
        JPanel roles = clear(new GridLayout(1, 2, 11, 0)); roles.add(menor); roles.add(tutor);
        roles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        f.add(left(roles)); f.add(Box.createVerticalStrut(18));

        f.add(left(lblNombre)); f.add(Box.createVerticalStrut(6));
        styleField(nombre); f.add(left(nombre)); f.add(espNombre);
        f.add(left(label("Correo electrónico", Font.BOLD, 12, PLUM950))); f.add(Box.createVerticalStrut(6));
        styleField(correo); f.add(left(correo)); f.add(Box.createVerticalStrut(14));
        f.add(left(label("Contraseña", Font.BOLD, 12, PLUM950))); f.add(Box.createVerticalStrut(6));
        styleField(clave); f.add(left(clave)); f.add(Box.createVerticalStrut(22));

        enviar.setFont(font(Font.BOLD, 14)); enviar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        enviar.addActionListener(e -> entrar());
        f.add(left(enviar)); f.add(Box.createVerticalStrut(8));
        cambiar.setFont(font(Font.BOLD, 12));
        cambiar.addActionListener(e -> { registro = !registro; actualizar(); });
        f.add(left(cambiar)); f.add(Box.createVerticalGlue());
        side.add(f);
        return side;
    }

    private static void estiloRol(Btn b, boolean sel) {
        b.style(sel ? PLUM100 : Color.WHITE, sel ? PLUM800 : INK, sel ? PLUM800 : LINE);
    }

    private void actualizar() {
        titulo.setText(registro ? "Crea tu cuenta" : "Bienvenido de nuevo");
        subtitulo.setText(registro ? "Regístrate para empezar a ahorrar." : "Inicia sesión para continuar.");
        enviar.setText(registro ? "Registrarme" : "Ingresar");
        cambiar.setText(registro ? "¿Ya tienes cuenta? Inicia sesión" : "¿No tienes cuenta? Regístrate");
        lblNombre.setVisible(registro); nombre.setVisible(registro); espNombre.setVisible(registro);
    }

    private void entrar() {
        if (correo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Escribe tu correo electrónico.", "Finzy", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // TODO: aquí llamar a la capa de lógica (login / registro) antes de abrir la ventana.
        String n = registro && !nombre.getText().isBlank() ? nombre.getText().trim() : correo.getText().split("@")[0];
        new VentanaPrincipal(rol, Character.toUpperCase(n.charAt(0)) + n.substring(1)).setVisible(true);
        dispose();
    }
}
