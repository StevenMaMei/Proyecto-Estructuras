package estructuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class GrafoListaAdyacente<E> implements IGrafo<E> {

	private HashMap<E, NodoListaAdyacente<E>> nodos;
	
	int maxNodos;
	int totalNodos;
	
	public GrafoListaAdyacente( int maxNodos) {
		nodos= new HashMap<>();
		this.maxNodos = maxNodos;
		totalNodos = 0;
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
	public IGrafo<E> prim() throws Exception {
		int cantEsperada= nodos.size();
		int cantReal=recorridoBFS();
		if(cantEsperada!= cantReal)
			throw new Exception("Grafo no conexo");
		PriorityQueue<NodoPesoAdyacente<NodoListaAdyacente<E>>> cola = new PriorityQueue<>();
		GrafoListaAdyacente<E> retorno= new GrafoListaAdyacente<>(maxNodos); //agrege maxNodos
		HashMap<E, NodoListaAdyacente<E>> nodosNuevos= new HashMap<>();
		for(E na:nodos.keySet()){
			NodoListaAdyacente<E> actual = nodos.get(na);
			E elemActual=actual.getElemento();
			actual.setRevisado(false);
			retorno.agregarNodo(elemActual);
			NodoListaAdyacente<E> nuevo= retorno.darNodo(elemActual);
			nodosNuevos.put(elemActual, nuevo);
			ArrayList<NodoListaAdyacente<E>> adyacentes= new ArrayList<>();
			for(int i=0;i<adyacentes.size();i++){
				cola.add(new NodoPesoAdyacente<NodoListaAdyacente<E>>(actual, actual.darPesoAdyacente(adyacentes.get(i)),adyacentes.get(i) ));
			}
			
		}
		NodoPesoAdyacente<NodoListaAdyacente<E>> aristaMenor= cola.poll();
		NodoListaAdyacente<E> actual= aristaMenor.getNodo();
		actual.setRevisado(true);
		retorno.generarArista(actual.getElemento(), aristaMenor.getAdyacente().getElemento(), aristaMenor.getPeso());
		cola=new PriorityQueue<>();
		actual=aristaMenor.getAdyacente();
		
		while(!cola.isEmpty()){
			actual.setRevisado(true);
			ArrayList<NodoListaAdyacente<E>> adyacentes= new ArrayList<>();
			for(int i=0;i<adyacentes.size();i++){
				NodoListaAdyacente<E> adyacenteActual= adyacentes.get(i);
				if(!adyacenteActual.isRevisado()){
					NodoPesoAdyacente<NodoListaAdyacente<E>> aristaNueva=new NodoPesoAdyacente<NodoListaAdyacente<E>>(actual, actual.darPesoAdyacente(adyacenteActual), adyacenteActual);
					cola.add(aristaNueva);
				}
			}
			boolean agregada=false;
			while(!agregada){
				NodoPesoAdyacente<NodoListaAdyacente<E>> aristaSiguiente= cola.poll();
				if(!aristaSiguiente.getAdyacente().isRevisado()){
					retorno.generarArista(aristaSiguiente.getNodo().getElemento(), aristaSiguiente.getAdyacente().getElemento(), aristaSiguiente.getPeso());
					agregada=true;
					actual=aristaSiguiente.getAdyacente();
				}
			}
			
			
		}
		

		return retorno;
	}

	@Override
	public GrafoListaAdyacente<E> Kruskal() {
		
		GrafoListaAdyacente<E> grafoSalida = new GrafoListaAdyacente<>(maxNodos);
		ConjuntosDisjuntos conjunto = new ConjuntosDisjuntos(maxNodos);
		
		for (int i=0; i< totalNodos ; i++) {
			try {
				grafoSalida.agregarNodo((E)nodos.get(i).getElemento());
				nodos.get(i).setRevisado(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ArrayList<NodoPesoAdyacente<NodoListaAdyacente<E>>> aristas = new ArrayList<>();
			
			//Recorrer la lista de adyacencia
			
			
		}
		
		
		
		return grafoSalida;
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

	public NodoListaAdyacente<E> darNodo(E elem){
		return nodos.get(elem);
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
