package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class GrafoMatriz<E> implements IGrafo<E> {

	private double[][] matrizAdyacencia;
	private HashMap<Integer, NodoMatriz<E>> nodos;
	private HashMap<E, NodoMatriz> indices;
	private HashMap<NodoMatriz,Integer> indiceVertice; //Asignar un indice al vertice 
	private int maxNodos;
	private int totalNodos;
	
	public GrafoMatriz (int maxNodos){
		matrizAdyacencia = new double [maxNodos][maxNodos];
		for (int i = 0; i < maxNodos; i++) {
			for (int j = 0; j < maxNodos; j++) {
				if (i == j)
					matrizAdyacencia[i][j] = 0;
				else 
					matrizAdyacencia [i][j] = Integer.MAX_VALUE;
			}
		}
		nodos = new HashMap<>();
		this.maxNodos = maxNodos;
		totalNodos = 0;
		
		indiceVertice = new HashMap<>();
	}
	
	@Override
	public void agregarNodo(E nodo) throws Exception {
		if (totalNodos == maxNodos)
			throw new Exception ("Ha excedido el maximo de nodos permitidos");

		NodoMatriz nuevo = new NodoMatriz<E>(nodo, totalNodos++);
		nodos.put(totalNodos, nuevo);
		indices.put(nodo, nuevo);
		indiceVertice.put(nuevo, nuevo.getPos());
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
		for (int i = 0; i < totalNodos; i++) {
			nodos.get(i).setRevisado(false);
		}
		int recorridos = 0;
		NodoMatriz act = indices.get(nodoInicial);
		if (act == null) {
			throw new Exception ("El nodo no existe");
		}
		
		Queue<NodoMatriz<E>> cola = new LinkedList<>();
		cola.add(act);
		act.setRevisado(true);
		while (!cola.isEmpty()) {
			NodoMatriz<E> actual = cola.poll();
			int indice = actual.getPos();
			for (int i = 0; i < totalNodos; i++) {
				NodoMatriz<E> agregar = nodos.get(i);
				if (matrizAdyacencia[indice][i] != Integer.MAX_VALUE && !agregar.isRevisado()) {
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
		for (int i = 0; i < totalNodos; i++) {
			nodos.get(i).setRevisado(false);
		}
		
		for (int i = 0; i < totalNodos; i++) {
			NodoMatriz<E> act = nodos.get(i);
			if (act != null && !act.isRevisado()) {
				Stack<NodoMatriz<E>> stack = new Stack();
				stack.push(act);
				while (!stack.isEmpty()) {
					NodoMatriz<E> actual = stack.pop();
					if (!actual.isRevisado()) {
						actual.setRevisado(true);
						int indice = actual.getPos();
						for (int j = 0; j < totalNodos; j++) {
							NodoMatriz<E> agregar = nodos.get(j);
							if (matrizAdyacencia[indice][j] != Integer.MAX_VALUE && !agregar.isRevisado()) {
								stack.push(agregar);
								agregar.setPadre(actual);
							}
						}
					}
				}
			}
		}
			
	}

	@Override
	public GrafoMatriz<E> prim() {
		int totAgregados = 0;
		
		GrafoMatriz <E> retorno = new GrafoMatriz<>(maxNodos);
		for (int i = 0; i < totalNodos; i++) {
			try {
				retorno.agregarNodo((E) nodos.get(i).getElemento());
				nodos.get(i).setRevisado(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PriorityQueue<NodoPesoAdyacente<NodoMatriz<E>>> colaPrior = new PriorityQueue<>(maxNodos);
		double minPeso = Integer.MAX_VALUE;
		int ind1 = 0;
		int ind2 = 0;
		for (int i = 0; i < totalNodos; i++) {
			for (int j = i+1; j < totalNodos; j++) {
				if (matrizAdyacencia[i][j] < minPeso) {
					minPeso = matrizAdyacencia [i][j];
					ind1 = i;
					ind2 = j;
				}
			}
		}
		NodoMatriz<E> nodo1 = nodos.get(ind1);
		NodoMatriz<E> nodo2 = nodos.get(ind2);
		
		nodo1.setRevisado(true);
		nodo2.setRevisado(true);
		
		try {
			retorno.generarArista((E) nodo1.getElemento(), (E) nodo2.getElemento(), minPeso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		totAgregados = 2;
		
		for (int i = 0; i < totalNodos; i++) {
			NodoMatriz<E> nuevo = nodos.get(i);
			if (i != ind1 && matrizAdyacencia[ind1][i] != Integer.MAX_VALUE && !nuevo.isRevisado()) {
				NodoPesoAdyacente<NodoMatriz<E>> p = new NodoPesoAdyacente<> (nuevo, matrizAdyacencia[ind1][i], nodo1);
				colaPrior.add(p);
			} 
			
			if (i != ind2 && matrizAdyacencia[ind2][i] != Integer.MAX_VALUE && !nuevo.isRevisado()) {
				NodoPesoAdyacente<NodoMatriz<E>> p = new NodoPesoAdyacente<> (nuevo, matrizAdyacencia[ind2][i], nodo2);
				colaPrior.add(p);
			}
		}
		
		while (totAgregados < totalNodos) {
			NodoPesoAdyacente<NodoMatriz<E>> n = colaPrior.poll();
			NodoMatriz<E> nuev = n.getNodo();
			
			if (!nuev.isRevisado()) {
				E elemento = (E) nuev.getElemento();
				E adyacente = (E) n.getAdyacente().getElemento();
			}
		}
		
		
		return retorno;
		
	}

	@Override
	public GrafoMatriz<E> Kruskal() {
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

	@Override
	public ArrayList<E> darAdyacentes(E nodo) throws Exception {
		NodoMatriz n = indices.get(nodo);
		if (n == null)
				throw new Exception ("No existe ningun nodo con el elemento buscado");
		ArrayList <E> retorno = new ArrayList<>();
		int ind = n.getPos();
		for (int i = 0; i < totalNodos; i++) {
			if (ind != i && matrizAdyacencia[ind][i] != Integer.MAX_VALUE) {
				retorno.add((E) nodos.get(i).getElemento());
			}
		}
		
		
		
		return retorno;
	}

}
