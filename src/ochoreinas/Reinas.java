package ochoreinas;

import java.util.ArrayList;

/**
 * Modelo
 * @author Alexis Daniel Fuentes Pérez
 */

public class Reinas {
  private ArrayList<ArrayList<Integer>> soluciones;
  private ArrayList<Integer> solucion;
  private ArrayList<Long> tiempos;
  private Clock temporizador;
  final int N = 8;
  ArrayList<ArrayList<Integer>> posibilidades;
  
  public Reinas () {
    soluciones = new ArrayList<ArrayList<Integer>> ();
    solucion = new ArrayList<Integer> ();
    tiempos = new ArrayList<Long> ();
    temporizador = new Clock ();
    posibilidades = new ArrayList<ArrayList<Integer>> ();
  }
  
  /**
   * Método para solucionar el problema.
   * Guarda en un vector para cada fila, la columna en la que está la reina de dicha fila
   * @param fila
   * @param columnas
   * @param diagDesc
   * @param diagAsc
   */
  
  public void colocarReinas (int fila, ArrayList<Integer> columnas, ArrayList<Integer> diagDesc, ArrayList<Integer> diagAsc) {
    if(fila == 0) {
      temporizador.start();
    }
    
    if(fila == N){
      ArrayList<Integer> aux = new ArrayList<Integer> ();
      for(int i = 0; i < N; i++) {
        aux.add(new Integer(solucion.get(i)));
      }
      soluciones.add(aux);
      temporizador.stop();
      tiempos.add(temporizador.elapsedTime());
      temporizador.start();
    }
    
    else {
      for(int j = 0; j < N; j++) {
        if((!columnas.contains(j)) && (!diagDesc.contains(j - fila)) && (!diagAsc.contains(j + fila))) {
          if((fila + 1) == solucion.size()) {
            solucion.set(fila, j);
          } else {
            solucion.add(fila, j);
          }
          columnas.add(j);
          diagDesc.add(j - fila);
          diagAsc.add(j + fila);
                    
          colocarReinas(fila + 1, copiarArray(columnas), copiarArray(diagDesc), copiarArray(diagAsc));
          
          columnas.remove(columnas.size() - 1);
          diagDesc.remove(diagDesc.size() - 1);
          diagAsc.remove(diagAsc.size() - 1);
        }
      }
    }
  }
  
  /**
   * Eliminación de las soluciones que no cumplen la nueva restricción.
   * Se mira las agrupaciones de filas de 3 en 3 donde se podría cumplir dicha restricción.
   */
  
  public void modificarSolucion () {
    combinaciones(new ArrayList<Integer>());
    ArrayList<Integer> eliminables = new ArrayList<Integer> ();
    
    for(int k = 0; k < soluciones.size(); k++) {
      ArrayList<Integer> sol = soluciones.get(k);
      
      for(int i = 0; i < posibilidades.size(); i++) {
        ArrayList<Integer> triplete = posibilidades.get(i);
        
        double x = ((double)(triplete.get(1) + 1) - (double)(triplete.get(0) + 1)) / ((double)(triplete.get(2) + 1) - (double)(triplete.get(1) + 1));
        double y = (((double)sol.get(triplete.get(1)) + 1) - ((double)sol.get(triplete.get(0)) + 1)) / (((double)sol.get(triplete.get(2)) + 1) - ((double)sol.get(triplete.get(1)) + 1));
        
        if (x == y) {
          if(!eliminables.contains(k)) 
            eliminables.add(k);
          i = posibilidades.size();
        }
      }
    }
    
    for(int i = (eliminables.size() - 1); i >= 0; i--) {
      int index = eliminables.get(i);
      soluciones.remove(index);
      tiempos.remove(index);
    }
  }
  
  /**
   * Crea combinaciones de 3 elementos, con los indices desde 0 hasta N
   * @param aux Vector donde dejara las combinaciones
   */
  
  void combinaciones (ArrayList<Integer> aux) {
    if(aux.size() == 3) {
      posibilidades.add(aux);
    } else {
      for(int i = 0; i < N; i++) {
        if(!aux.contains(i)) {
          aux.add(i);
          combinaciones(copiarArray(aux));
          aux.remove(new Integer(i));
        }
      }
    }
  }
  
  public ArrayList<ArrayList<Integer>> getSoluciones (){
    return soluciones;
  }
  
  public ArrayList<Long> getTiempos () {
    return tiempos;
  }
  
  public ArrayList<Integer> copiarArray (ArrayList<Integer> original) {
    ArrayList<Integer> copia = new ArrayList<Integer> ();
    for(int i = 0; i < original.size(); i++) {
      int elemento = original.get(i);
      copia.add(new Integer(elemento));
    }
    return copia;
  }
  
  /**
   * Representacion de la solucion de forma algebraica
   * @param sol
   */
  
  public void mostrarSolucion (ArrayList<Integer> sol) {
    for(int i = 0; i < sol.size(); i++) {
      switch(sol.get(i)) {
        case 0: System.out.print("(Da" + (8 - i) + ") "); break;
        case 1: System.out.print("(Db" + (8 - i) + ") "); break;
        case 2: System.out.print("(Dc" + (8 - i) + ") "); break;
        case 3: System.out.print("(Dd" + (8 - i) + ") "); break;
        case 4: System.out.print("(De" + (8 - i) + ") "); break;
        case 5: System.out.print("(Df" + (8 - i) + ") "); break;
        case 6: System.out.print("(Dg" + (8 - i) + ") "); break;
        case 7: System.out.print("(Dh" + (8 - i) + ") "); break;
        default: break;
      }
    }
  }
  
}
