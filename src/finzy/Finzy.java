package finzy;
import finzy.vista.VentanaAcceso;
import javax.swing.SwingUtilities;
public class Finzy {
    
    public static void main(String[] args) {
         SwingUtilities.invokeLater(() -> new VentanaAcceso().setVisible(true));
    }
    
}
