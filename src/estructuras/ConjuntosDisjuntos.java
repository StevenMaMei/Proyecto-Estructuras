package estructuras;

public class ConjuntosDisjuntos  {

	
	private int[] p;
	
	
	
	// Constructor

	public ConjuntosDisjuntos(int n) {
		p = new int[n];
		
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
	}

	public int findSet(int i) {
		if (p[i] == i) {
			return i;
		} else {
			p[i] = findSet(p[i]); // eficiencia
			return p[i];
		}
	}

	public boolean esElMismo(int i, int j) {
		return findSet(i) == findSet(j);
	}

	public void union(int i, int j) {
		if (!esElMismo(i, j)) {
			int x = findSet(i);
			int y = findSet(j);

			p[x] = y;

		}

	}

}
