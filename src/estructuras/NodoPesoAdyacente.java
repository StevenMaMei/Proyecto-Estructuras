package estructuras;

public class NodoPesoAdyacente<N extends Nodo> implements Comparable<NodoPesoAdyacente<N>>{

	private N nodo;
	private double peso;
	private N adyacente;
	
	public NodoPesoAdyacente (N nodo, double peso, N adyacente){
		this.nodo = nodo;
		this.peso = peso;
		this.adyacente = adyacente;
	}
	
	public N getNodo() {
		return nodo;
	}
	
	public double getPeso () {
		return peso;
	}
	
	public N getAdyacente () {
		return adyacente;
	}

	@Override
	public int compareTo(NodoPesoAdyacente<N> o) {
		Double d1 = peso;
		Double d2 = o.peso;
		return d1.compareTo(d2);
	}
}
