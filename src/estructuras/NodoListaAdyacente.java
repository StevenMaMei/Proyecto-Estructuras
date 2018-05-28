package estructuras;

import java.util.ArrayList;
import java.util.HashMap;

public class NodoListaAdyacente<E> extends Nodo<E> implements INodoLista<E>, Comparable<NodoListaAdyacente<E>> {

	private ArrayList<INodoLista<E>> adyacentes;
	private HashMap<INodoLista<E>,Double> pesos;
	private double pesoCaminoPrevio;
	
	public NodoListaAdyacente(E elem) {
		super(elem);
		adyacentes= new ArrayList<>();
		pesos= new HashMap<>();
	}
	
	@Override
	public ArrayList<INodoLista<E>> darAdyacentes() {
		return (ArrayList<INodoLista<E>>)adyacentes;
	}

	@Override
	public HashMap<INodoLista<E>, Double> darPesos() {
		return pesos;
	}

	@Override
	public void agregarAdyacente(INodoLista<E> n) {
		adyacentes.add(n);
		
	}

	@Override
	public void agregarPeso(INodoLista<E> adyacente, Double peso) {
		pesos.put(adyacente, peso);
		
	}
	
	public void modificarPesoCaminoPrevio(double n){
		pesoCaminoPrevio=n;
	}
	public double darPesoCaminoPrevio(){
		return pesoCaminoPrevio;
	}
	
	public Double darPesoAdyacente(INodoLista<E> n){
		return pesos.get(n);
	}

	@Override
	public int compareTo(NodoListaAdyacente<E> o) {
		if(pesoCaminoPrevio==o.pesoCaminoPrevio){
			return 0;
		}else if(pesoCaminoPrevio<o.pesoCaminoPrevio){
			return -1;
		}else{
			return 1;
		}
	}
	

	

}
