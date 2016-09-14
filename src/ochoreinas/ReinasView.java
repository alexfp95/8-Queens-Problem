package ochoreinas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;

/**
 * Vista
 * @author Alexis Daniel Fuentes Pérez
 */

public class ReinasView {
  private MiJFrame frame;
  private JPanel tablero;
  private KeyboardPanel resultados = new KeyboardPanel();
  
  private ArrayList<ArrayList<Integer>> conjSoluciones;
  ArrayList<Long> tiempo;
  int numSol;
  
  /**
   * Constructor. 
   * El frame llevara un panel con un Keylistener
   */
  
  public ReinasView () {
    frame = new MiJFrame ();
    frame.setTitle("El problema de las 8 reinas");
    
    tablero = new JPanel ();
    
    tablero.setLayout(new GridLayout (8, 8, 0, 0));
    
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++) {
        JPanel casilla = new JPanel ();
        if (((i + j) % 2) == 0) {
          casilla.setBackground(Color.WHITE);
        } else {
          casilla.setBackground(Color.BLACK);
        }
        tablero.add(casilla);
      }
    }
    
    frame.add(tablero, BorderLayout.CENTER);
    
    resultados.setLayout(new GridLayout (1, 2, 0, 0));
    
    JLabel posiciones = new JLabel ("Posiciones de las reinas");
    JLabel tiempo = new JLabel ("Tiempo en calcularlo");
    
    resultados.add(posiciones);
    resultados.add(tiempo);
    resultados.setFocusable(true);
    
    frame.add(resultados, BorderLayout.SOUTH);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize (600, 600);
    frame.setLocation (100, 100);
    frame.setVisible (true);
  }
  
  /**
   * Actualiza la vista
   * @param solucion
   * @param tiempo
   */
  
  public void updateVista (ArrayList<Integer> solucion, Long tiempo) {
    //frame.getContentPane().removeAll();
    tablero.removeAll();

    ImageIcon negra = new ImageIcon ("src/icon/negra.png");
    ImageIcon blanca = new ImageIcon ("src/icon/blanca.png");
    
    for(int i = 0; i < 8; i++) {
      int columna = solucion.get(i);
      for(int j = 0; j < 8; j++) {
        JPanel casilla = new JPanel ();
        if (((i + j) % 2) == 0) {
          casilla.setBackground(Color.WHITE);
          if(j == columna) {
            ImageIcon img = new ImageIcon (negra.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            JLabel reina = new JLabel ();
            reina.setIcon(img);
            casilla.add(reina);
          }
        }
        else {
          casilla.setBackground(Color.BLACK);
          if(j == columna) {
            ImageIcon img = new ImageIcon (blanca.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            JLabel reina = new JLabel ();
            reina.setIcon(img);
            casilla.add(reina);
          }
        }
        tablero.add(casilla);
      }
    }
    
    resultados.removeAll();
    JLabel posiciones = new JLabel (obtenerPos(solucion));
    resultados.add(posiciones);
    
    JLabel ltiempo = new JLabel ("  Tiempo en calcularlo: " + (tiempo / Math.pow(10, 9)) + " segundos");
    resultados.add(ltiempo);
    
    frame.getContentPane().repaint();
    frame.setVisible(true);   
  }
  
  /**
   * Muestra la solución de forma algebraica
   * @param solucion
   * @return solucion algebraica
   */
  
  public String obtenerPos (ArrayList<Integer> solucion) {
    String posiciones = new String ();
    for(int i = 0; i < solucion.size(); i++) {
      switch(solucion.get(i)) {
        case 0: posiciones = posiciones + "(Da" + (8 - i) + ") "; break;
        case 1: posiciones = posiciones + "(Db" + (8 - i) + ") "; break;
        case 2: posiciones = posiciones + "(Dc" + (8 - i) + ") "; break;
        case 3: posiciones = posiciones + "(Dd" + (8 - i) + ") "; break;
        case 4: posiciones = posiciones + "(De" + (8 - i) + ") "; break;
        case 5: posiciones = posiciones + "(Df" + (8 - i) + ") "; break;
        case 6: posiciones = posiciones + "(Dg" + (8 - i) + ") "; break;
        case 7: posiciones = posiciones + "(Dh" + (8 - i) + ") "; break;
        default: break;
      }
    }
    return posiciones;
  }
  
  /**
   * Funcion para recibir datos del controlador
   * @param soluciones
   * @param t tiempo de ejecución
   */
  
  public void setDatos (ArrayList<ArrayList<Integer>> soluciones, ArrayList<Long> t) {
    conjSoluciones = soluciones;
    tiempo = t;
  }
  
  /**
   * Clase interna.
   * Panel con KeyListener que permitirá actualizar las soluciones al presiona cualquier tecla.
   */
  
  class KeyboardPanel extends JPanel {
    public KeyboardPanel() {
      addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
          updateVista(conjSoluciones.get(numSol), tiempo.get(numSol));
          if(numSol < (conjSoluciones.size() - 1))
            numSol++;
        }
      });
    }
  }
  
}
