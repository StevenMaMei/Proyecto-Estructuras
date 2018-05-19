package estructuras;

public class ParNodoPeso<T extends INodo> implements Comparable{

	private T nod;
	private double peso;
	
	public ParNodoPeso (T nod, double peso) {
		this.nod = nod;
		this.peso = peso;
	}

	@Override
	public int compareTo(Object o) {
		ParNodoPeso <T> par = (ParNodoPeso<T>) o;
		return (int) (peso - par.peso);
	}

}
