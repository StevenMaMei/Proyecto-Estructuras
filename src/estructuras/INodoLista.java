package estructuras;

import java.util.ArrayList;
import java.util.HashMap;

public interface INodoLista<T> extends INodo<T> {

	ArrayList<INodoLista<T>> darAdyacentes();
	HashMap<INodoLista<T>,Double> darPesos();
	void agregarAdyacente(INodoLista<T> n);
	void agregarPeso(INodoLista<T> adyacente, Double peso);
}
