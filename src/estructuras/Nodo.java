package estructuras;

import java.io.Serializable;

public abstract class Nodo<E> implements INodo<E>, Serializable {
	
	private boolean revisado;
	
	private E elemento;
	
	private Nodo<E> padre;

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
	public Nodo<E> getPadre() {
		return padre;
	}

	@Override
	public void setPadre(Nodo<E> padre) {
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
