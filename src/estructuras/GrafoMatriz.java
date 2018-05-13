package estructuras;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GrafoMatriz<E> implements IGrafo<E> {

	private double[][] matrizAdyacencia;
	private HashMap<Integer, NodoMatriz<E>> nodos;
	private HashMap<E, NodoMatriz> indices;
	private int maxNodos;
	private int totalNodos;
	
	public GrafoMatriz (int maxNodos){
		matrizAdyacencia = new double [maxNodos][maxNodos];
		nodos = new HashMap<>();
		this.maxNodos = maxNodos;
		totalNodos = 0;
	}
	
	@Override
	public void agregarNodo(E nodo) throws Exception {
		if (totalNodos == maxNodos)
			throw new Exception ("Ha excedido el maximo de nodos permitidos");
		
		NodoMatriz nuevo = new NodoMatriz<E>(nodo, totalNodos++);
		nodos.put(totalNodos, nuevo);
		indices.put(nodo, nuevo);
	}

	@Override
	public void generarArista(E nodo1, E nodo2, double peso) throws Exception {
		int indice1;
		int indice2;
		try {
			indice1 = indices.get(nodo1).getPos();
			indice2 = indices.get(nodo2).getPos();
		} catch (Exception e) {
			throw new Exception ("Alguno de los dos nodos no existe");
		}
		
		matrizAdyacencia[indice1][indice2] = peso;
		matrizAdyacencia[indice2][indice1] = peso;
		
	}

	@Override
	public int recorridoBFS(E nodoInicial) throws Exception {
		for (int i = 0; i < maxNodos && nodos.get(i) != null; i++) {
			nodos.get(i).setRevisado(false);
		}
		int recorridos = 0;
		NodoMatriz act = indices.get(nodoInicial);
		if (act == null) {
			throw new Exception ("El nodo no existe");
		}
		
		Queue<NodoMatriz<E>> cola = new LinkedList<>();
		cola.add(act);
		while (!cola.isEmpty()) {
			NodoMatriz<E> actual = cola.poll();
			int indice = actual.getPos();
			for (int i = 0; i < matrizAdyacencia.length; i++) {
				NodoMatriz<E> agregar = nodos.get(i);
				if (matrizAdyacencia[indice][i] != 0 && !agregar.isRevisado()) {
					agregar.setRevisado(true);
					cola.add(agregar);
					recorridos++;
					agregar.setPadre(actual);
				}
			}
		}
		
		return recorridos;
	}

	@Override
	public int recorridoBFS() {
		int retorno;
		try {
			retorno = recorridoBFS((E) nodos.get(0).getElemento());
		} catch (Exception e) {
			retorno = 0;
		}
		return retorno;
	}

	@Override
	public void recorridoDFS() {
		for (int i = 0; i < maxNodos && nodos.get(i) != null; i++) {
			nodos.get(i).setRevisado(false);
		}
		
		for (int i = 0; i < maxNodos; i++) {
			NodoMatriz<E> act = nodos.get(i);
			if (act != null && !act.isRevisado()) {
				Stack<NodoMatriz<E>> stack = new Stack();
				stack.push(act);
				while (!stack.isEmpty()) {
					NodoMatriz<E> actual = stack.pop();
					int indice = actual.getPos();
					for (int j = 0; j < matrizAdyacencia.length; j++) {
						NodoMatriz<E> agregar = nodos.get(j);
						if (matrizAdyacencia[indice][j] != 0 && !agregar.isRevisado()) {
							agregar.setRevisado(true);
							stack.push(agregar);
							agregar.setPadre(actual);
						}
					}
				}
			}
		}
			
	}

	@Override
	public double[][] prim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] Kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nodo Dijkstra(E nodo1, E nodo2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nodo FloydWarshall(E nodo1, E nodo2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
