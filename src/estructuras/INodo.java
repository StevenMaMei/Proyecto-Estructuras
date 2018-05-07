package estructuras;

import java.util.*;

public interface INodo <T>{

	public ParNodoPeso buscarAdyacente (T Nodo) throws Exception;
	public HashMap <T, ParNodoPeso> darHijos ();
	public T retornarElemento();
	public void agregarArista (T nodo, double peso) throws Exception;
	
}
