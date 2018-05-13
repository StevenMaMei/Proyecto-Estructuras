package estructuras;

public class NodoMatriz<E> extends Nodo implements INodoMatriz<E>{

	private int pos;
	
	public NodoMatriz(E elem, int pos) {
		super(elem);
		this.pos = pos;
	}

	@Override
	public int getPos() {
		return pos;
	}

	@Override
	public void setPos(int pos) {
		this.pos = pos;
		
	}

}
