package estructuras;

import java.util.ArrayList;

public interface IGrafo<T> {

	public void agregarNodo (T nodo) throws Exception;
	public void generarArista (T nodo1, T nodo2, double peso) throws Exception;
	public int recorridoBFS (T nodoInicial) throws Exception;
	public int recorridoBFS ();
	public void recorridoDFS();
	public IGrafo<T> prim();
	public IGrafo<T> Kruskal();
	public ListaPeso Dijkstra  (T nodo1, T nodo2) throws Exception;
	public double[][] FloydWarshall () throws Exception;
	public ArrayList <T> darAdyacentes (T nodo) throws Exception;
	public T darPadre (T nodo) throws Exception;
	
}
