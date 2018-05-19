package estructuras;

import java.util.PriorityQueue;

public class Prueba {

	public static void main(String[] args) {
		
		NodoMatriz<String> n1 = new NodoMatriz<String>("A", 0);
		NodoMatriz<String> n2 = new NodoMatriz<String> ("B", 0);
		NodoMatriz<String> n3 = new NodoMatriz<String>("C", 0);
		
		PriorityQueue<NodoPesoAdyacente<NodoMatriz<String>>> p = new PriorityQueue<>();
		NodoPesoAdyacente<NodoMatriz<String>> no = new NodoPesoAdyacente<NodoMatriz<String>>(n3, 10, n1);
		NodoPesoAdyacente<NodoMatriz<String>> no2 = new NodoPesoAdyacente<NodoMatriz<String>>(n2, 1, n2);
		NodoPesoAdyacente<NodoMatriz<String>> no3 = new NodoPesoAdyacente<NodoMatriz<String>>(n1, 8, n1);
		p.add(no);
		p.add(no2);
		p.add(no3);
		System.out.println(p.poll().getPeso());
		System.out.println(p.poll().getPeso());
		System.out.println(p.poll().getPeso());

	}

}
