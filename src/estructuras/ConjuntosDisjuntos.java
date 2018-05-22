package estructuras;

public class ConjuntosDisjuntos {

	private int[] arreglo;

	// Constructor
	public ConjuntosDisjuntos(int n) {
		arreglo = new int[n];

		for (int i = 0; i < n; i++) {
			arreglo[i] = i;
		}
	}


	//Retorna un puntero al representante del unico conjunto que contiene x
	public int findSet(int i) {
		if (arreglo[i] == i) {
			return i;
		} else {
			arreglo[i] = findSet(arreglo[i]); // eficiencia
			return arreglo[i];
		}
	}

	//Verifica ya se encuentra en el conjunto del otro
	public boolean esElMismo(int i, int j) {
		return findSet(i) == findSet(j);
	}

	//Une los conjuntos dinamicos que contienen x y y 
	public void union(int i, int j) {
		if (!esElMismo(i, j)) {
			int x = findSet(i);
			int y = findSet(j);

			arreglo[x] = y;

		}

	}

}
