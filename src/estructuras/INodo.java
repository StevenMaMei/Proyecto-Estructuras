package estructuras;

import java.util.*;

public interface INodo <T>{

	public boolean isRevisado();
	public void setRevisado (boolean revisado);
	public Nodo getPadre();
	public void setPadre (Nodo padre);
	public T getElemento();
	public void setElemento (T elem);
	
}
