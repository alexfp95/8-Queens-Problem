package ochoreinas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;


/**
 * Mi JFrame
 * @author Alexis Daniel Fuentes Pérez
 */

public class MiJFrame extends JFrame {
  
  final int HGAP = 0;
  final int VGAP = 0;
  
  public MiJFrame () {
    setLayout(new BorderLayout (HGAP, VGAP));
    getContentPane().setBackground(Color.WHITE);
  }
}
