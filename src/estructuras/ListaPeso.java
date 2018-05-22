package estructuras;

import java.util.LinkedList;

public class ListaPeso<T> {

	private LinkedList<T> list;
	private double total;
	
	public ListaPeso (LinkedList<T> l, double t) {
		list = l;
		total = t;
	}

	public LinkedList<T> getList() {
		return list;
	}

	public void setList(LinkedList<T> list) {
		this.list = list;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
