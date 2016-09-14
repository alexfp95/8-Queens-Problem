package ochoreinas;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Problema de las 8 reinas, con una nueva restricción.
 * @author Alexis Daniel Fuentes Pérez
 */

public class Principal {
  
  public static void main(String[] args) throws IOException {
    Reinas a = new Reinas ();
    ReinasView b = new ReinasView ();
    ReinasController c = new ReinasController (a, b);
    
    ArrayList<Integer> columnas = new ArrayList<Integer>();
    ArrayList<Integer> diagDesc = new ArrayList<Integer>();
    ArrayList<Integer> diagAsc = new ArrayList<Integer>();
    
    c.colocarReinas(0, columnas, diagDesc, diagAsc);
    c.modificarSolucion();
    c.enviarResultados();
  }

}
