package estructuras;

public interface IGrafo<T> {

	public void agregarNodo (T nodo) throws Exception;
	public void generarArista (T nodo1, T nodo2, double peso);
	public int recorridoBFS (T nodoInicial) throws Exception;
	public int recorridoBFS ();
	public void recorridoDFS();
	public double [] [] prim();
	public Nodo darNodo (T nodo) throws Exception;
	public double [] [] Kruskal();
	public Nodo Dijkstra  (T nodo1, T nodo2) throws Exception;
	public Nodo FloydWarshall (T nodo1, T nodo2) throws Exception;
}
