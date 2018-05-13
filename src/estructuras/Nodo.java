package estructuras;

public abstract class Nodo<E> implements INodo<E> {
	
	private boolean revisado;
	
	private E elemento;
	
	private Nodo padre;

	public Nodo (E elem) {
		elemento = elem;
		revisado = false;
		padre = null;
	}
	
	@Override
	public boolean isRevisado() {
		return revisado;
	}

	@Override
	public void setRevisado(boolean revisado) {
		this.revisado = revisado;
	}

	@Override
	public Nodo getPadre() {
		return padre;
	}

	@Override
	public void setPadre(Nodo padre) {
		this.padre = padre;
		
	}

	@Override
	public E getElemento() {
		return elemento;
	}

	@Override
	public void setElemento(E elem) {
		elemento = elem;
		
	}

}
