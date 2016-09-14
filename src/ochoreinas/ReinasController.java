package ochoreinas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Controlador
 * @author Alexis Daniel Fuentes Pérez
 */

public class ReinasController {
  private Reinas modelo;
  private ReinasView vista;
  
  public ReinasController (Reinas r, ReinasView v) {
    modelo = r;
    vista = v;
  }
  
  ArrayList<ArrayList<Integer>> getSoluciones () {
    return modelo.getSoluciones();
  }
  
  ArrayList<Long> getTiempos () {
    return modelo.getTiempos();
  }
  
  void colocarReinas (int fila, ArrayList<Integer> columnas, ArrayList<Integer> diagDesc, ArrayList<Integer> diagAsc) {
    modelo.colocarReinas(fila, columnas, diagDesc, diagAsc);
  }
  
  void modificarSolucion () {
    modelo.modificarSolucion();
  }
  
  /**
   * Envia los resultados a la vista
   * @throws IOException
   */
  
  void enviarResultados () throws IOException {
    vista.setDatos(getSoluciones (), getTiempos ());
  }
 
}
