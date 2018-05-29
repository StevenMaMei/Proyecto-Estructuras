package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import estructuras.GrafoListaAdyacente;
import estructuras.GrafoMatriz;
import estructuras.IGrafo;
import estructuras.ListaPeso;

public class Principal {
	public final static char MATRIZ = 'm';
	public final static char LISTA = 'l';
	
	public final static int IND_TIEMPO = 0;
	public final static int IND_PRECIO = 1;
	public final static int IND_DISTANCIA = 2;
	
	public final static int POS_CALI = 0;
	public final static int POS_BS_AIRES = 1;
	public final static int POS_BRASILIA = 2;
	public final static int POS_SANT_CHILE = 3;
	public final static int POS_QUITO = 4;
	public final static int POS_ASUNNCION = 5;
	public final static int POS_LIMA = 6;
	public final static int POS_MONTEVIDEO = 7;
	public final static int POS_CARACAS = 8;
	public final static int POS_CIUDAD_MEXICO = 9;
	public final static int POS_WASHINGTON = 10;
	public final static int POS_PANAMA = 11;
	public final static int POS_OTTAWA = 12;
	public final static int POS_SUCRE = 13;
	public final static int[][] MATRIZ_DISTANCIAS = {{0, 6495, 3810, 4150, 458, 6025, 1725, 4767, 1319, 3030, 3944, 735, 4669, 2787},
			{6495, 0, 2340, 1139, 4361, 1042, 3138, 280, 5094, 7394, 8399, 5346, 9070, 1862},
			{3810, 2340, 0, 3014, 3780, 1458, 3173, 2880, 3596, 6839, 6798, 4523, 7364, 1878},
			{4150, 1139, 3014, 0, 3789, 1560, 2467, 1343, 4902, 6612, 8075, 4791, 8787, 1692},
			{458, 4361, 3780, 3789, 0, 3581, 1329, 4503, 1744, 3137, 4350, 1003, 5079, 2543},
			{6025, 1042, 1458, 1560, 3581, 0, 2518, 1080, 4102, 6702, 7419, 4522, 8073, 1053},
			{1725, 3138, 3173, 2467, 1329, 2528, 0, 3301, 2745, 4256, 5667, 2327, 6393, 1481},
			{4767, 280, 2880, 1343, 4503, 1080, 3301, 0, 5173, 7557, 8486, 5481, 11229, 1981},
			{1319, 5094, 3596, 4902, 1744, 4102, 2745, 5173, 0, 3596, 3318, 1538, 3976, 3286},
			{3030, 7394, 6839, 6612, 3137, 6702, 4256, 7557, 3596, 0, 3033, 2318, 3604, 5652},
			{3944, 8399, 6798, 8075, 4350, 7419, 5667, 8486, 3318, 3033, 0, 3399, 6944, 6559},
			{735, 5346, 4523, 4791, 1003, 4522, 2327, 5481, 1538, 2318, 3399, 0, 4131, 3506}, 
			{4669, 9070, 7364, 8787, 5079, 8073, 6393, 11229, 3976, 3604, 6944, 4131, 0, 7245},
			{2787, 1862, 1878, 1692, 2543, 1053, 1481, 1981, 3286, 5652, 6559, 3506, 7245, 0}};
	
	public final static Ciudad[] CIUDADES = {new Ciudad("Cali", 0, 0), new Ciudad("Buenos Aires", 0, 0), new Ciudad("Brasilia", 0, 0),
			new Ciudad("Santiago de Chile", 0, 0), new Ciudad("Quito", 0, 0), new Ciudad("Asuncion", 0, 0), new Ciudad("Lima", 0, 0),
			new Ciudad("Montevideo", 0, 0), new Ciudad("Caracas", 0, 0), new Ciudad("Ciudad de Mexico", 0, 0), new Ciudad("Washington", 0, 0),
			new Ciudad("Panama", 0, 0), new Ciudad("Ottawa", 0, 0), new Ciudad("Sucre", 0, 0)};
	
	
	private HashSerialisable<String, GrafoMatriz<Ciudad>[] > rutasMatriz;
	private HashSerialisable<String, GrafoListaAdyacente<Ciudad> []> rutasLista;
	
	private Iterator<String> iteradorHash;
	private IGrafo<Ciudad> [] grafoCandidato;
	public char tipoGrafo;
	
	public Principal(char tipo) throws Exception {
		tipoGrafo = tipo;
		File arch = new File("./data/grafo" + MATRIZ);
		if (arch.exists()) {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(arch));
			rutasMatriz = (HashSerialisable<String, GrafoMatriz<Ciudad>[]>) is.readObject();
			
			arch = new File ("./data/grafo" + LISTA);
			is = new ObjectInputStream(new FileInputStream(arch));
			rutasLista = (HashSerialisable<String, GrafoListaAdyacente<Ciudad>[]>) is.readObject();
			is.close();
		} else {
			rutasMatriz = new HashSerialisable<>();
			rutasLista = new HashSerialisable<>();
		}
		
		iteradorHash = rutasMatriz.keySet().iterator();
		
		grafoCandidato = new IGrafo [3];
		if (tipo == MATRIZ) {
			for (int i = 0; i < 3; i ++) {
				grafoCandidato [i] = new GrafoMatriz<>(CIUDADES.length);
			}
		} else {
			for (int i = 0; i < 3; i ++) {
				grafoCandidato [i] = new GrafoListaAdyacente<>(CIUDADES.length);
			}
		}
		llenarGrafo();
		
	}
	
	public void llenarGrafo() throws Exception {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < CIUDADES.length; j++) {
				grafoCandidato[i].agregarNodo(CIUDADES [j]);
			}
		}
	}
	
	public void guardaRutas() throws FileNotFoundException, IOException {
		File arch = new File("./data/grafo" + MATRIZ);
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arch));
		os.writeObject(rutasMatriz);
		
		arch = new File("./data/grafo" + LISTA);
		os = new ObjectOutputStream(new FileOutputStream(arch));
		os.writeObject(rutasLista);
		
		os.close();
	}
	
	public void cambiarRepresentacion() throws Exception {
		reiniciarGrafo();
		
		File arch = new File("./data/grafo" + MATRIZ);
		if (arch.exists()) {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(arch));
			rutasMatriz = (HashSerialisable<String, GrafoMatriz<Ciudad>[]>) is.readObject();
			
			arch = new File("./data/grafo" + LISTA);
			is = new ObjectInputStream(new FileInputStream(arch));
			rutasLista = (HashSerialisable<String, GrafoListaAdyacente<Ciudad>[]>) is.readObject();
			
			is.close();
		} else {
			rutas = new HashSerialisable<>();
		}
		
		iteradorHash = rutas.keySet().iterator();
		
	}
	
	public void reiniciarGrafo () throws Exception {
		if (tipoGrafo == MATRIZ) {
			tipoGrafo = LISTA;
			for (int i = 0; i < 3; i ++) {
				grafoCandidato [i] = new GrafoListaAdyacente<>(CIUDADES.length);
			}
		} else {
			tipoGrafo = MATRIZ;
			for (int i = 0; i < 3; i ++) {
				grafoCandidato [i] = new GrafoMatriz<>(CIUDADES.length);
			}
		}
		llenarGrafo();
	}
	
	
	public void agregarVueloDirecto (int indiceCiudad1, int indiceCiudad2, double precio, double velocidad) throws Exception {
		grafoCandidato[IND_PRECIO].generarArista(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2], precio);
		grafoCandidato [IND_DISTANCIA].generarArista(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2], MATRIZ_DISTANCIAS[indiceCiudad1][indiceCiudad2]);
		grafoCandidato [IND_TIEMPO].generarArista(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2], MATRIZ_DISTANCIAS[indiceCiudad1][indiceCiudad2] * velocidad);
		
	}
	
	public void eliminarVueloDirecto(int indiceCiudad1,int indiceCiudad2) throws Exception{
		grafoCandidato[IND_PRECIO].eliminarArista(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2]);
		grafoCandidato[IND_DISTANCIA].eliminarArista(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2]);
		grafoCandidato[IND_TIEMPO].eliminarArista(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2]);
		
	}
	/**
	 * La primer posición del arreglo es el precio
	 * @param indiceCiudad1
	 * @param indiceCiudad2
	 * @return
	 * @throws Exception
	 */
	public String[][] darRutaMasEconomica(int indiceCiudad1, int indiceCiudad2) throws Exception{
		String [][] retorno = new String [rutas.size()] [CIUDADES.length + 2];
		int i = 0;
		for (String key: rutas.keySet()) {
			IGrafo<Ciudad>[] grafoAct= rutas.get(key);
			ListaPeso<Ciudad> camino = grafoAct[IND_PRECIO].Dijkstra(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2]);
			retorno [i][0] = key;
			retorno [i][1] = camino.getTotal() + "";
			int j = 2;
			for (Ciudad ciudad: camino.getList()) {
				retorno [i][j] = ciudad.getNombreCiudad();
				j++;
			}
			i++;
		}
		return retorno;
	}
	/**
	 * Primera posición es la distancia
	 * @param indiceCiudad1
	 * @param indiceCiudad2
	 * @return
	 * @throws Exception
	 */
	public String[][] darRutaMasCorta(int indiceCiudad1, int indiceCiudad2) throws Exception{
		String [][] retorno = new String [rutas.size()] [CIUDADES.length + 2];
		int i = 0;
		for (String key: rutas.keySet()) {
			IGrafo<Ciudad>[] grafoAct= rutas.get(key);
			ListaPeso<Ciudad> camino = grafoAct[IND_DISTANCIA].Dijkstra(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2]);
			retorno [i][0] = key;
			retorno [i][1] = camino.getTotal() + "";
			int j = 2;
			for (Ciudad ciudad: camino.getList()) {
				retorno [i][j] = ciudad.getNombreCiudad();
				j++;
			}
			i++;
		}
		return retorno;
	}
	/**
	 * Primer posición es el tiempo
	 * @param indiceCiudad1
	 * @param indiceCiudad2
	 * @return
	 * @throws Exception
	 */
	public String[][] darRutaMasRapida(int indiceCiudad1, int indiceCiudad2) throws Exception{
		String [][] retorno = new String [rutas.size()] [CIUDADES.length + 2];
		int i = 0;
		for (String key: rutas.keySet()) {
			IGrafo<Ciudad>[] grafoAct= rutas.get(key);
			ListaPeso<Ciudad> camino = grafoAct[IND_TIEMPO].Dijkstra(CIUDADES[indiceCiudad1], CIUDADES[indiceCiudad2]);
			retorno [i][0] = key;
			retorno [i][1] = camino.getTotal() + "";
			int j = 2;
			for (Ciudad ciudad: camino.getList()) {
				retorno [i][j] = ciudad.getNombreCiudad();
				j++;
			}
			i++;
		}
		return retorno;
	}
	
	public boolean existeAerolinea (String aerolinea) {
		return rutas.containsKey(aerolinea);
	}
	
	public void agregarAerolinea (String aerolinea) throws Exception {
		if (grafoCandidato[0].recorridoBFS() != CIUDADES.length)
			throw new Exception("Debe de ser posible llegar a todas las ciudades");
		
		rutas.put(aerolinea, grafoCandidato);
	}
	
	public IGrafo<Ciudad> siguienteGrafoCompania(){
		IGrafo<Ciudad> graf = null;
		if (iteradorHash.hasNext()) {
			graf = rutas.get(iteradorHash.next())[0];
		} else {
			iteradorHash = rutas.keySet().iterator();
			graf = rutas.get(iteradorHash.next())[0];
		}
		return graf;
	}

	public HashSerialisable<String, IGrafo<Ciudad>[]> getRutas() {
		return rutas;
	}

	public void setRutas(HashSerialisable<String, IGrafo<Ciudad>[]> rutas) {
		this.rutas = rutas;
	}

	public Iterator<String> getIteradorHash() {
		return iteradorHash;
	}

	public void setIteradorHash(Iterator<String> iteradorHash) {
		this.iteradorHash = iteradorHash;
	}

	public IGrafo<Ciudad>[] getGrafoCandidato() {
		return grafoCandidato;
	}

	public void setGrafoCandidato(IGrafo<Ciudad>[] grafoCandidato) {
		this.grafoCandidato = grafoCandidato;
	}
	
}
