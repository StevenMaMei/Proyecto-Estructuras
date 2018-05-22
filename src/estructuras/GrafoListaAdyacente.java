package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GrafoListaAdyacente<E> implements IGrafo<E> {

	private HashMap<E, NodoListaAdyacente<E>> nodos;
	public GrafoListaAdyacente() {
		nodos= new HashMap<>();
	}

	@Override
	public void agregarNodo(E nodo) throws Exception {
		NodoListaAdyacente<E> nuevo= new NodoListaAdyacente<E>(nodo);
		nodos.put(nodo, nuevo);
		
	}

	@Override
	public void generarArista(E nodo1, E nodo2, double peso) throws Exception {
		NodoListaAdyacente<E> n1 = nodos.get(nodo1);
		NodoListaAdyacente<E> n2= nodos.get(nodo2);
		
		n1.agregarAdyacente(n2);
		n2.agregarAdyacente(n1);
		
		n1.agregarPeso(n2, peso);
		n2.agregarPeso(n1, peso);
	}

	@Override
	public int recorridoBFS(E nodoInicial) throws Exception {
		for(E na: nodos.keySet()){
			nodos.get(na).setRevisado(false);
		}
		int encontrados=0;
		NodoListaAdyacente<E> actual= nodos.get(nodoInicial);
		if(actual == null)
			throw new Exception("Nodo existe el nodo");
		Queue<NodoListaAdyacente<E>> cola= new LinkedList<>();
		cola.add(actual);
		actual.setRevisado(true);
		while(!cola.isEmpty()){
			NodoListaAdyacente<E> revisando= cola.poll();
			ArrayList<INodoLista<E>> adyacentes= revisando.darAdyacentes();
			for(int i=0;i<adyacentes.size();i++){
				if(!adyacentes.get(i).isRevisado()){
					adyacentes.get(i).setRevisado(true);
					encontrados++;
					adyacentes.get(i).setPadre(revisando);
					cola.add((NodoListaAdyacente<E>)adyacentes.get(i));
				}
			}
			revisando.setRevisado(true);
		}
		return encontrados;
		
	}

	@Override
	public int recorridoBFS() {
		int respuesta;
		Set<E> llaves =  nodos.keySet();
		try {
			respuesta = recorridoBFS(llaves.iterator().next());
		} catch (Exception e) {
			respuesta=0;
		}
		return respuesta;
	}

	@Override
	public void recorridoDFS() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGrafo<E> prim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGrafo<E> Kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaPeso Dijkstra(E nodo1, E nodo2) throws Exception {
		HashMap<NodoListaAdyacente<E>,Double> l= new HashMap<>();
		HashMap<NodoListaAdyacente<E>, Double> s= new HashMap<>();
		
		for(E na:nodos.keySet()){
			l.put(nodos.get(na), Double.MAX_VALUE);
		}
		NodoListaAdyacente<E> n1=nodos.get(nodo1);
		NodoListaAdyacente<E> n2 = nodos.get(nodo2);
		l.put(n1, 0.0);
		PriorityQueue<NodoListaAdyacente<E>> cola= new PriorityQueue<>();
		cola.add(n1);
		while(s.get(n2)== null){
			NodoListaAdyacente<E> actual= cola.poll();
			HashMap<INodoLista<E>, Double> pesosActuales=actual.darPesos();
			double lActual=l.get(actual);
			s.put(actual, 0.0);
			ArrayList<INodoLista<E>> adyacentes = actual.darAdyacentes();
			for(int i=0;i<adyacentes.size();i++){
				NodoListaAdyacente<E> adyacenteActual=(NodoListaAdyacente<E>) adyacentes.get(i);
				Double distanciaActualNuevo= pesosActuales.get(adyacenteActual);
				if(distanciaActualNuevo+lActual<l.get(adyacenteActual)){
					adyacenteActual.setPadre(actual);
					adyacenteActual.modificarPesoCaminoPrevio(distanciaActualNuevo+lActual);
					l.put(adyacenteActual, distanciaActualNuevo+lActual);
					cola.add(adyacenteActual);
				}
			}
			
		}
		LinkedList<E> camino= new LinkedList<>();
		NodoListaAdyacente<E> actual=n2;
		while(actual!= n1){
			E elemento= actual.getElemento();
			camino.addFirst(elemento);
			actual=nodos.get(elemento);
		}
		return new ListaPeso<>(camino, l.get(n2));
		
	}

	

	@Override
	public ArrayList<E> darAdyacentes(E nodo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E darPadre(E nodo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] FloydWarshall() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
