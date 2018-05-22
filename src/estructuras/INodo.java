package estructuras;

public interface INodo <T>{

	public boolean isRevisado();
	public void setRevisado (boolean revisado);
	public Nodo<T> getPadre();
	public void setPadre (Nodo<T> padre);
	public T getElemento();
	public void setElemento (T elem);
	
}
