package estructuras;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GrafoListaAdyacente<E> implements IGrafo<E>, Serializable {

	private HashMap<E, NodoListaAdyacente<E>> nodos;

	int maxNodos;
	int totalNodos;

	public GrafoListaAdyacente(int maxNodos) {
		nodos = new HashMap<>();
		this.maxNodos = maxNodos;
		totalNodos = 0;
	}
	
	public HashMap<E, NodoListaAdyacente<E>> darNodos(){
		return nodos;
	}

	@Override
	public void agregarNodo(E nodo) throws Exception {

		NodoListaAdyacente<E> nuevo= new NodoListaAdyacente<E>(nodo);
		if(nodos.get(nodo)!=null){
			throw new Exception("Nodo ya existente");
		}
		if(totalNodos== maxNodos)
			throw new Exception("N�mero m�ximo de nodos alcanzados");
		nodos.put(nodo, nuevo);
		totalNodos++;
		
	}

	@Override
	public void generarArista(E nodo1, E nodo2, double peso) throws Exception {
		NodoListaAdyacente<E> n1 = nodos.get(nodo1);
		NodoListaAdyacente<E> n2= nodos.get(nodo2);
		if(n1==null || n2==null)
			throw new Exception("Uno de los nodos no existe");

		n1.agregarAdyacente(n2);
		n2.agregarAdyacente(n1);

		n1.agregarPeso(n2, peso);
		n2.agregarPeso(n1, peso);
	}

	@Override
	public int recorridoBFS(E nodoInicial) throws Exception {
		for (E na : nodos.keySet()) {
			nodos.get(na).setRevisado(false);
		}
		int encontrados = 0;
		NodoListaAdyacente<E> actual = nodos.get(nodoInicial);
		if (actual == null)
			throw new Exception("Nodo existe el nodo");
		Queue<NodoListaAdyacente<E>> cola = new LinkedList<>();
		cola.add(actual);
		encontrados++;
		actual.setRevisado(true);
		while (!cola.isEmpty()) {
			NodoListaAdyacente<E> revisando = cola.poll();
			ArrayList<INodoLista<E>> adyacentes = revisando.darAdyacentes();
			for (int i = 0; i < adyacentes.size(); i++) {
				if (!adyacentes.get(i).isRevisado()) {
					adyacentes.get(i).setRevisado(true);
					encontrados++;
					adyacentes.get(i).setPadre(revisando);
					cola.add((NodoListaAdyacente<E>) adyacentes.get(i));
				}
			}
			revisando.setRevisado(true);
		}
		return encontrados;

	}

	@Override
	public int recorridoBFS() {
		int respuesta;
		Set<E> llaves = nodos.keySet();
		try {
			respuesta = recorridoBFS(llaves.iterator().next());
		} catch (Exception e) {
			respuesta = 0;
		}
		return respuesta;
	}

	@Override
	public void recorridoDFS() {

		for(E actual: nodos.keySet()){
			nodos.get(actual).setRevisado(false);
		}
		
		for(E a:nodos.keySet()){
			NodoListaAdyacente<E> act= nodos.get(a);
			if(!act.isRevisado()){
				Stack<NodoListaAdyacente<E>> pila= new Stack<>();
				pila.push(act);
				while(!pila.isEmpty()){
					NodoListaAdyacente<E> actual= pila.pop();
					if(!actual.isRevisado()){
						actual.setRevisado(true);
						ArrayList<INodoLista<E>> adyacentes= actual.darAdyacentes();
						for(int i=0;i<adyacentes.size();i++){
							NodoListaAdyacente<E> agregar= (NodoListaAdyacente<E>) adyacentes.get(i);
							if(!agregar.isRevisado()){
								pila.push(agregar);
								agregar.setPadre(actual);
							}
						}
					}
				}
				
			}
		}
		
	}

	@Override
	public IGrafo<E> prim() throws Exception {
		int cantEsperada = nodos.size();
		int cantReal = recorridoBFS();
		if (cantEsperada != cantReal)
			throw new Exception("Grafo no conexo");
		PriorityQueue<NodoPesoAdyacente<NodoListaAdyacente<E>>> cola = new PriorityQueue<>();
		GrafoListaAdyacente<E> retorno = new GrafoListaAdyacente<>(maxNodos); // agrege maxNodos
		HashMap<E, NodoListaAdyacente<E>> nodosNuevos = new HashMap<>();
		for (E na : nodos.keySet()) {
			NodoListaAdyacente<E> actual = nodos.get(na);
			E elemActual = actual.getElemento();
			actual.setRevisado(false);
			retorno.agregarNodo(elemActual);
			NodoListaAdyacente<E> nuevo = retorno.darNodo(elemActual);
			nodosNuevos.put(elemActual, nuevo);
			ArrayList<INodoLista<E>> adyacentes= actual.darAdyacentes();
			for(int i=0;i<adyacentes.size();i++){
				
				cola.add(new NodoPesoAdyacente<NodoListaAdyacente<E>>(actual, actual.darPesoAdyacente((NodoListaAdyacente<E>)adyacentes.get(i)),(NodoListaAdyacente<E>)adyacentes.get(i) ));

			}

		}
		NodoPesoAdyacente<NodoListaAdyacente<E>> aristaMenor= cola.poll();
		NodoListaAdyacente<E> actual= aristaMenor.getNodo();
//		actual.setRevisado(true);
//		retorno.generarArista(actual.getElemento(), aristaMenor.getAdyacente().getElemento(), aristaMenor.getPeso());
		cola=new PriorityQueue<>();
//		actual=aristaMenor.getAdyacente();
		cola.add(aristaMenor);

		while(!cola.isEmpty()){
			actual.setRevisado(true);
			ArrayList<INodoLista<E>> adyacentes= actual.darAdyacentes();
			for(int i=0;i<adyacentes.size();i++){
				NodoListaAdyacente<E> adyacenteActual= (NodoListaAdyacente<E>) adyacentes.get(i);
				if(!adyacenteActual.isRevisado()){
					NodoPesoAdyacente<NodoListaAdyacente<E>> aristaNueva=new NodoPesoAdyacente<NodoListaAdyacente<E>>(actual, actual.darPesoAdyacente(adyacenteActual), adyacenteActual);
					cola.add(aristaNueva);
				}
			}
			
			boolean agregada=false;
			while(!agregada&&!cola.isEmpty()){
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

		for (int i = 0; i < totalNodos; i++) {
			try {
				grafoSalida.agregarNodo((E) nodos.get(i).getElemento());
				nodos.get(i).setRevisado(false);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		// Recorrer la lista de adyacencia
		ArrayList<NodoPesoAdyacente<NodoListaAdyacente<E>>> aristas = new ArrayList<>();
		
		
		
		for (E key : nodos.keySet()) {
			NodoListaAdyacente<E> actual = new NodoListaAdyacente<>(key);
			//NodoPesoAdyacente<NodoListaAdyacente<E>> arista = new NodoPesoAdyacente<NodoListaAdyacente<E>>(actual,actual.darAdyacentes() ,	  );
			//aristas.add(arista);
			
		}
		
		
		Collections.sort(aristas);
		
		for(int k=0; k<aristas.size();k++) {
			NodoPesoAdyacente<NodoListaAdyacente<E>> arista = aristas.get(k); 
			NodoListaAdyacente<E> i = arista.getNodo();
			NodoListaAdyacente<E> j = arista.getAdyacente();
			
			//if(!conjunto.esElMismo(nodos.get(i).darPesoAdyacente(i), nodos.get(j))) {
			//	conjunto.union(i, j);
			//	grafoSalida.generarArista(i.getElemento(), j.getElemento(), arista.getPeso());
			//}
		}

		return grafoSalida;
	}

	@Override
	public ListaPeso Dijkstra(E nodo1, E nodo2) throws Exception {
		HashMap<NodoListaAdyacente<E>, Double> l = new HashMap<>();
		HashMap<NodoListaAdyacente<E>, Double> s = new HashMap<>();

		for (E na : nodos.keySet()) {
			l.put(nodos.get(na), Double.MAX_VALUE);
		}
		NodoListaAdyacente<E> n1 = nodos.get(nodo1);
		NodoListaAdyacente<E> n2 = nodos.get(nodo2);
		if(n1==null || n2==null)
			throw new Exception("Uno de los nodos no existe");
		l.put(n1, 0.0);
		PriorityQueue<NodoListaAdyacente<E>> cola = new PriorityQueue<>();
		cola.add(n1);
		boolean encontrado=false;
		while(s.get(n2)== null&&!cola.isEmpty()){
			NodoListaAdyacente<E> actual= cola.poll();
			HashMap<INodoLista<E>, Double> pesosActuales=actual.darPesos();
			double lActual=l.get(actual);

			s.put(actual, 0.0);
			if(actual==n2)
				encontrado=true;
			ArrayList<INodoLista<E>> adyacentes = actual.darAdyacentes();
			for (int i = 0; i < adyacentes.size(); i++) {
				NodoListaAdyacente<E> adyacenteActual = (NodoListaAdyacente<E>) adyacentes.get(i);
				Double distanciaActualNuevo = pesosActuales.get(adyacenteActual);
				if (distanciaActualNuevo + lActual < l.get(adyacenteActual)) {
					adyacenteActual.setPadre(actual);
					adyacenteActual.modificarPesoCaminoPrevio(distanciaActualNuevo + lActual);
					l.put(adyacenteActual, distanciaActualNuevo + lActual);
					cola.add(adyacenteActual);
				}
			}

		}
		if(!encontrado)
			throw new Exception("Imposible llegar del nodo1 al nodo2");
		LinkedList<E> camino= new LinkedList<>();
		NodoListaAdyacente<E> actual=n2;
		while(actual!= n1){
			E elemento= actual.getElemento();
			camino.addFirst(elemento);
			actual=(NodoListaAdyacente<E>) actual.getPadre();

		}
		camino.addFirst(n1.getElemento());
		return new ListaPeso<>(camino, l.get(n2));

	}

	public NodoListaAdyacente<E> darNodo(E elem) {
		return nodos.get(elem);
	}

	@Override
	public ArrayList<E> darAdyacentes(E nodo) throws Exception {
		if(nodos.get(nodo)== null)
			throw new Exception("Nodo no encontrado");
		ArrayList<INodoLista<E>> adyacentes=nodos.get(nodo).darAdyacentes();
		
		ArrayList<E> retorno=new ArrayList<>();
		for(int i=0;i<adyacentes.size();i++){
			retorno.add(adyacentes.get(i).getElemento());
		}
		return retorno;
	}

	@Override
	public E darPadre(E nodo) throws Exception {
		return nodos.get(nodo).getPadre().getElemento();
	}

	@Override
	public double[][] FloydWarshall() throws Exception {
		double[][] retorno= new double[nodos.size()][nodos.size()];
		int i=0;
		for(E a: nodos.keySet()){
			int j=0;
			NodoListaAdyacente<E> actual= nodos.get(a);
			for(E p:nodos.keySet()){
				NodoListaAdyacente<E> proximo=nodos.get(p);
				if(proximo==actual){
					retorno[i][j]=0;
				}else{
					Double peso=actual.darPesoAdyacente(proximo);
					if(peso==null){
						retorno[i][j]=Double.MAX_VALUE;
					}else{
						retorno[i][j]=peso;
					}
				}
				j++;
			}
			i++;
		}
		
		for(int k=0;k<retorno.length;k++){
			for(i=0;i<retorno.length;i++){
				for(int j=0;j<retorno.length;j++){
					if(retorno[i][j]>retorno[i][k]+retorno[k][j]){
						retorno[i][j]=retorno[i][k]+retorno[k][j];
					}
				}
			}
		}
		return retorno;
	}

	@Override
	public void eliminarNodo(E nodo) throws Exception {
		NodoListaAdyacente<E> aEliminar=nodos.get(nodo);
		if(aEliminar==null)
			throw new Exception("Nodo no existente");
		for(E k:nodos.keySet()){
			NodoListaAdyacente<E> actual=nodos.get(k);
			actual.darAdyacentes().remove(aEliminar);
			actual.darPesos().remove(aEliminar);
		}
		nodos.remove(nodo);
	}

	@Override
	public void eliminarArista(E nodo1, E nodo2) throws Exception {
		NodoListaAdyacente<E> n1=nodos.get(nodo1);
		NodoListaAdyacente<E> n2=nodos.get(nodo2);
		if(n1==null || n2==null||n1.darPesoAdyacente(n2)==null )
			throw new Exception("Arista o nodo no existente");
		
		n1.darAdyacentes().remove(n2);
		n1.darPesos().remove(n2);
		
		n2.darAdyacentes().remove(n1);
		n2.darPesos().remove(n1);
	}

	@Override
	public double darPeso(E nodo1, E nodo2) throws Exception {
		NodoListaAdyacente<E> n1=nodos.get(nodo1);
		NodoListaAdyacente<E> n2=nodos.get(nodo2);
		if(n1==null || n2==null||n1.darPesoAdyacente(n2)==null )
			throw new Exception("Arista o nodo no existente");
		
		return n1.darPesos().get(n2);
	}

}
