package estructuras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
		for (int i = 0; i < maxNodos; i++) {
			for (int j = 0; j < maxNodos; j++) {
				if (i == j)
					matrizAdyacencia[i][j] = 0;
				else 
					matrizAdyacencia [i][j] = Double.MAX_VALUE;
			}
		}
		
		nodos = new HashMap<>();
		indices = new HashMap<> ();
		
		this.maxNodos = maxNodos;
		totalNodos = 0;
		
	}
	
	@Override
	public void agregarNodo(E nodo) throws Exception {
		if (totalNodos == maxNodos)
			throw new Exception ("Ha excedido el maximo de nodos permitidos");
		if (indices.get(nodo) != null) {
			throw new Exception ("El nodo ya existe");
		}
		NodoMatriz<E> nuevo = new NodoMatriz<>(nodo, totalNodos);
		nodos.put(totalNodos++, nuevo);
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
		for (int i = 0; i < totalNodos; i++) {
			nodos.get(i).setRevisado(false);
		}
		int recorridos = 0;
		NodoMatriz<E> act = indices.get(nodoInicial);
		if (act == null) {
			throw new Exception ("El nodo no existe");
		}
		
		Queue<NodoMatriz<E>> cola = new LinkedList<>();
		cola.add(act);
		act.setRevisado(true);
		while (!cola.isEmpty()) {
			recorridos++;
			NodoMatriz<E> actual = cola.poll();
			int indice = actual.getPos();
			for (int i = 0; i < totalNodos; i++) {
				NodoMatriz<E> agregar = nodos.get(i);
				if (matrizAdyacencia[indice][i] != Double.MAX_VALUE && !agregar.isRevisado()) {
					agregar.setRevisado(true);
					cola.add(agregar);
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
			retorno = recorridoBFS(nodos.get(0).getElemento());
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
				Stack<NodoMatriz<E>> stack = new Stack<>();
				stack.push(act);
				while (!stack.isEmpty()) {
					NodoMatriz<E> actual = stack.pop();
					if (!actual.isRevisado()) {
						actual.setRevisado(true);
						int indice = actual.getPos();
						for (int j = 0; j < totalNodos; j++) {
							NodoMatriz<E> agregar = nodos.get(j);
							if (matrizAdyacencia[indice][j] != Double.MAX_VALUE && !agregar.isRevisado()) {
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
	public GrafoMatriz<E> Kruskal() throws Exception {
		
		GrafoMatriz<E> grafoSalida = new GrafoMatriz<>(maxNodos);
		ConjuntosDisjuntos conjunto = new ConjuntosDisjuntos(maxNodos);
		
		for (int i = 0; i < totalNodos; i++) {
			try {
				grafoSalida.agregarNodo((E) nodos.get(i).getElemento());
				nodos.get(i).setRevisado(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ArrayList<NodoPesoAdyacente<NodoMatriz<E>>> aristas = new ArrayList<>();
		
		for (int i = 0; i < maxNodos; i++) {
			for (int j = i+1; j < maxNodos; j++) {
				if (matrizAdyacencia[i][j] != Double.MAX_VALUE) {
					NodoPesoAdyacente<NodoMatriz<E>> arista = new NodoPesoAdyacente<NodoMatriz<E>>(nodos.get(i), matrizAdyacencia[i][j], nodos.get(j));
					aristas.add(arista);
				}
			}
		}
		
		Collections.sort(aristas);
		
		for (int k = 0; k < aristas.size(); k++) {
			
			NodoPesoAdyacente<NodoMatriz<E>> arista = aristas.get(k);
			NodoMatriz<E> i = arista.getNodo();
			NodoMatriz<E> j = arista.getAdyacente();
			
			if (!conjunto.esElMismo(i.getPos(), j.getPos())) {
				conjunto.union(i.getPos(), j.getPos());
				grafoSalida.generarArista(i.getElemento(), j.getElemento(), arista.getPeso());
			}
			
		}
		
		
		return grafoSalida;
	}
	
	
	@Override
	public GrafoMatriz<E> prim() throws Exception{
		int totAgregados = 0;
		int conexos = recorridoBFS();
		if (conexos < totalNodos)
			throw new Exception("El grafo debe de ser conexo");
		
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
		double minPeso = Double.MAX_VALUE;
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
			retorno.generarArista(nodo1.getElemento(), nodo2.getElemento(), minPeso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		totAgregados = 2;
		
		for (int i = 0; i < totalNodos; i++) {
			NodoMatriz<E> nuevo = nodos.get(i);
			if (i != ind1 && matrizAdyacencia[ind1][i] != Double.MAX_VALUE && !nuevo.isRevisado()) {
				NodoPesoAdyacente<NodoMatriz<E>> p = new NodoPesoAdyacente<> (nuevo, matrizAdyacencia[ind1][i], nodo1);
				colaPrior.add(p);
			} 
			
			if (i != ind2 && matrizAdyacencia[ind2][i] != Double.MAX_VALUE && !nuevo.isRevisado()) {
				NodoPesoAdyacente<NodoMatriz<E>> p = new NodoPesoAdyacente<> (nuevo, matrizAdyacencia[ind2][i], nodo2);
				colaPrior.add(p);
			}
		}
		
		while (totAgregados < totalNodos) {
			NodoPesoAdyacente<NodoMatriz<E>> n = colaPrior.poll();
			NodoMatriz<E> nuev = n.getNodo();
			
			if (!nuev.isRevisado()) {
				E elemento = nuev.getElemento();
				E adyacente = n.getAdyacente().getElemento();
				try {
					retorno.generarArista(elemento, adyacente, n.getPeso());
				} catch (Exception e) {
					e.printStackTrace();
				}
				totAgregados++;
				
				int ind3 = indices.get(elemento).getPos();
				for (int i = 0; i < totalNodos; i++) {
					NodoMatriz<E> nuevo = nodos.get(i);
					if (i != ind3 && matrizAdyacencia[ind3][i] != Double.MAX_VALUE && !nuevo.isRevisado()) {
						NodoPesoAdyacente<NodoMatriz<E>> p = new NodoPesoAdyacente<>(nuevo, matrizAdyacencia[ind3][i], nuev);
						colaPrior.add(p);
					}
				}
			}
		}
		
		
		return retorno;
		
	}



	@Override
	public ListaPeso<E> Dijkstra(E nodo1, E nodo2) throws Exception {
		NodoMatriz<E> n1 = indices.get(nodo1);
		NodoMatriz <E> n2 = indices.get(nodo2);
		LinkedList<E> camino = new LinkedList<>();
		
		if (n1 == null || n2 == null) {
			throw new Exception ("Alguno de los dos nodos no existe");
		}
		
		int indice1 = n1.getPos();
		
		HashMap<Integer, Double> L = new HashMap<>();
		HashMap<Integer, E> S = new HashMap<>();
		HashMap<Integer, E> parcial = new HashMap <>();
		
		for (int i = 0; i < totalNodos; i++) {
			L.put(i, matrizAdyacencia[indice1][i]);
			parcial.put(i, nodos.get(0).getElemento());
		}
		
		while (S.get(n2.getPos()) == null) {
		
			double min = Double.MAX_VALUE;
			int ind = 0;
			for (int key : L.keySet()) {
				if (S.get(key) == null && L.get(key) <= min) {
					min = L.get(key);
					ind = key;
				}
			}
				S.put(ind, parcial.get(ind));
				for (int i = 0; i < totalNodos; i++) {
					double pes = matrizAdyacencia[ind][i];
					if (S.get(i) == null) {
						if (L.get(ind) + pes < L.get(i)) {
							L.put(i, L.get(ind) + pes);
							parcial.put(i, nodos.get(ind).getElemento());
						}
							
					}
				}
		}
		int ind2 = n2.getPos();
		int act = ind2;
		if (L.get(ind2) == Double.MAX_VALUE) {
			throw new Exception ("Es imposible llegar de un nodo al otro");
		}
		
		
		while (act != indice1) {
			E elem = nodos.get(act).getElemento();
			camino.addFirst(elem);
			act = indices.get(S.get(act)).getPos();
		}
		camino.addFirst(nodo1);
		
		return new ListaPeso<>(camino, L.get(ind2));
	}

	@Override
	public double[][] FloydWarshall() throws Exception {
		double [][] retorno = new double[totalNodos] [totalNodos];
		for (int i = 0; i < totalNodos; i++) {
			for (int j = 0; j < totalNodos; j++) {
				retorno [i][j] = matrizAdyacencia[i][j];
			}
		}
		for (int k = 0; k < totalNodos; k++) {
			for (int i = 0; i < totalNodos; i++) {
				for (int j = i+1; j < totalNodos; j++) {
					if (retorno [i][j] > retorno [i][k] + retorno [k][j]) {
						retorno [i][j] = retorno [i][k] + retorno [k][j];
						retorno [j][i] = retorno [i][j];
					}
				}
			}
		}
		
		return retorno;
	}

	@Override
	public ArrayList<E> darAdyacentes(E nodo) throws Exception {
		NodoMatriz<E> n = indices.get(nodo);
		if (n == null)
				throw new Exception ("No existe ningun nodo con el elemento buscado");
		ArrayList <E> retorno = new ArrayList<>();
		int ind = n.getPos();
		for (int i = 0; i < totalNodos; i++) {
			if (ind != i && matrizAdyacencia[ind][i] != Double.MAX_VALUE) {
				retorno.add(nodos.get(i).getElemento());
			}
		}
		
		
		
		return retorno;
	}

	@Override
	public E darPadre(E nodo) throws Exception {
		NodoMatriz<E> act = indices.get(nodo);
		if (act == null)
			throw new Exception ("El nodo no existe");
		
		NodoMatriz<E> padre = (NodoMatriz<E>) act.getPadre();
		E retorno;
		if (padre == null)
			retorno = null;
		else {
			retorno = padre.getElemento();
		}
		return retorno;
	}
	

	public boolean estaNodo (E nodo) {
		boolean retorno = false;
		NodoMatriz<E> elNodo = indices.get(nodo);
		if (elNodo != null) {
			int pos = elNodo.getPos();
			retorno = nodos.get(pos) != null;
		} else {
			retorno = false;
		}
		
		return retorno;
	}




	

}
