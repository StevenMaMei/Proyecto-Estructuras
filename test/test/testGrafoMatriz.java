package test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.*;

import estructuras.GrafoMatriz;
import estructuras.ListaPeso;

public class testGrafoMatriz {

	private GrafoMatriz<String> grafo;

	private void setupEscenario1() {
		grafo = new GrafoMatriz<>(6);
		
	}

	private void setupEscenario2() {
		setupEscenario1();
		try {
			grafo.agregarNodo("A");
			grafo.agregarNodo("B");
			grafo.agregarNodo("C");
			grafo.agregarNodo("D");
			grafo.agregarNodo("E");
			grafo.generarArista("A", "B", 7);
			grafo.generarArista("A", "C", 3);
			grafo.generarArista("B", "C", 2);
			grafo.generarArista("D", "E", 5);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void setupEscenario3() {
		setupEscenario2();

		try {
			grafo.generarArista("D", "B", 4);
			grafo.generarArista("C", "E", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupEscenario4() {
		grafo = new GrafoMatriz<>(2);
		try {
			grafo.agregarNodo("A");
			grafo.agregarNodo("B");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAgregarNodoVacio() {
		setupEscenario1();
		try {
			grafo.agregarNodo("A");
			assertTrue(grafo.estaNodo("A"));
		} catch (Exception e) {
			fail("No se debe de generar excepcion");
		}
	}

	@Test
	public void testAgregarNodoNoVacio() {
		setupEscenario2();
		try {
			grafo.agregarNodo("F");
			assertTrue(grafo.estaNodo("F"));
		} catch (Exception e) {
			fail("No debe de generar excepcion");
			e.printStackTrace();
		}
	}

	@Test
	public void testAgregarExistente() {
		setupEscenario2();
		try {
			grafo.agregarNodo("A");
			fail("Debe de generarse una excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El nodo ya existe");
		}
	}

	@Test
	public void testAgregarLimite() {
		setupEscenario4();
		try {
			grafo.agregarNodo("C");
			fail("Se debe de generar excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Ha excedido el maximo de nodos permitidos");
		}
	}

	@Test
	public void testGenerarArista() {
		setupEscenario4();

		try {
			grafo.generarArista("A", "B", 1);
			assertEquals(grafo.darAdyacentes("A").size(), grafo.darAdyacentes("B").size());
			assertEquals(grafo.darAdyacentes("A").size(), 1);
			assertEquals(grafo.darAdyacentes("A").get(0), "B");
			assertEquals(grafo.darAdyacentes("B").get(0), "A");
		} catch (Exception e) {
			e.printStackTrace();
			fail("No deben de generarse excepciones");
		}
	}

	@Test
	public void testGenerarAristaFail() {
		setupEscenario4();

		try {
			grafo.generarArista("A", "C", 1);
			fail("Debe de generarse excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Alguno de los dos nodos no existe");
		}
	}

	@Test
	public void testRecorridoBFSNoConexo() {
		setupEscenario2();
		try {
			int x = grafo.recorridoBFS("A");
			assertEquals(x, 3);
			assertEquals(grafo.darPadre("C"), "A");
			assertEquals(grafo.darPadre("B"), "A");
			assertEquals(grafo.darPadre("A"), null);
		} catch (Exception e) {
			e.printStackTrace();
			fail("No debe de generarse excepcion");
		}
	}

	@Test
	public void testRecorridoBFSConexo() {
		setupEscenario3();

		try {
			int x = grafo.recorridoBFS("C");
			assertEquals(x, 5);
			assertEquals(grafo.darPadre("C"), null);
			assertEquals(grafo.darPadre("A"), "C");
			assertEquals(grafo.darPadre("B"), "C");
			assertEquals(grafo.darPadre("D"), "B");
			assertEquals(grafo.darPadre("E"), "C");

		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testRecorridoBFSNoNodo() {
		setupEscenario3();

		try {
			grafo.recorridoBFS("H");
			fail("Debe de generarse excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El nodo no existe");
		}
	}

	@Test
	public void testRecorridoBFS2NoConexo() {
		setupEscenario2();
		int x = grafo.recorridoBFS();
		assertEquals(x, 3);
		try {
			assertEquals(grafo.darPadre("C"), "A");
			assertEquals(grafo.darPadre("B"), "A");
			assertEquals(grafo.darPadre("A"), null);
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testRecorridoBFS2Conexo() {
		setupEscenario3();

		int x = grafo.recorridoBFS();
		assertEquals(x, 5);
		try {
			assertEquals(grafo.darPadre("C"), "A");
			assertEquals(grafo.darPadre("A"), null);
			assertEquals(grafo.darPadre("B"), "A");
			assertEquals(grafo.darPadre("D"), "B");
			assertEquals(grafo.darPadre("E"), "C");
		} catch (Exception e) {
			fail("No debe de generarse excepcion");
		}
	}

	@Test
	public void testRecorridoBFS2Vacio() {
		setupEscenario1();

		int x = grafo.recorridoBFS();
		assertEquals(x, 0);
	}

	@Test
	public void testRecorridoDFSNoConexo() {
		setupEscenario2();
		grafo.recorridoDFS();

		try {
			assertEquals(grafo.darPadre("A"), null);
			assertEquals(grafo.darPadre("B"), "C");
			assertEquals(grafo.darPadre("C"), "A");
			assertEquals(grafo.darPadre("D"), null);
			assertEquals(grafo.darPadre("E"), "D");
		} catch (Exception e) {
			fail("No debe de generarse excepcion");
		}
	}

	@Test
	public void testRecorridoDFSConexo() {
		setupEscenario3();
		grafo.recorridoDFS();

		try {
			assertEquals(grafo.darPadre("A"), null);
			assertEquals(grafo.darPadre("B"), "D");
			assertEquals(grafo.darPadre("C"), "A");
			assertEquals(grafo.darPadre("D"), "E");
			assertEquals(grafo.darPadre("E"), "C");
		} catch (Exception e) {
			fail("No debe de generarse excepcion");
		}
	}

	@Test
	public void testPrimNoConexo() {
		setupEscenario2();
		try {
			grafo.prim();
			fail("Debe de generarse una excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El grafo debe de ser conexo");
		}
	}

	@Test
	public void testPrimConexo() {
		setupEscenario3();
		try {
			GrafoMatriz<String> res = grafo.prim();
			ArrayList<String> ady = res.darAdyacentes("A");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");

			ady = res.darAdyacentes("B");
			assertEquals(ady.size(), 2);
			assertEquals(ady.get(0), "C");
			assertEquals(ady.get(1), "D");

			ady = res.darAdyacentes("C");
			assertEquals(ady.size(), 3);
			assertEquals(ady.get(0), "A");
			assertEquals(ady.get(1), "B");
			assertEquals(ady.get(2), "E");

			ady = res.darAdyacentes("D");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "B");

			ady = res.darAdyacentes("E");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");
		} catch (Exception e) {
			fail("No se debe de generar excepcion");
		}
	}

	@Test
	public void testKruskalNoConexo() {
		setupEscenario2();
		try {
			GrafoMatriz<String> res = grafo.Kruskal();

			ArrayList<String> ady = res.darAdyacentes("A");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");

			ady = res.darAdyacentes("B");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");

			ady = res.darAdyacentes("C");
			assertEquals(ady.size(), 2);
			assertEquals(ady.get(0), "A");
			assertEquals(ady.get(1), "B");

			ady = res.darAdyacentes("D");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "E");

			ady = res.darAdyacentes("E");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "D");
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testKruskalConexo() {
		setupEscenario3();
		try {
			GrafoMatriz<String> res = grafo.Kruskal();

			ArrayList<String> ady = res.darAdyacentes("A");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");

			ady = res.darAdyacentes("B");
			assertEquals(ady.size(), 2);
			assertEquals(ady.get(0), "C");
			assertEquals(ady.get(1), "D");

			ady = res.darAdyacentes("C");
			assertEquals(ady.size(), 3);
			assertEquals(ady.get(0), "A");
			assertEquals(ady.get(1), "B");
			assertEquals(ady.get(2), "E");

			ady = res.darAdyacentes("D");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "B");

			ady = res.darAdyacentes("E");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testDjikstraNodoNoExiste() {
		setupEscenario3();

		try {
			grafo.Dijkstra("A", "H");
			fail("Debe de generarse excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Alguno de los dos nodos no existe");
		}
	}

	@Test
	public void testDjikstraLlega() {
		setupEscenario3();

		try {
			ListaPeso<String> list = grafo.Dijkstra("A", "D");
			LinkedList<String> lista = list.getList();
			double peso = list.getTotal();

			Iterator<String> it = lista.iterator();
			assertEquals(it.next(), "A");
			assertEquals(it.next(), "C");
			assertEquals(it.next(), "E");
			assertEquals(it.next(), "D");

		} catch (Exception e) {
			e.printStackTrace();
			fail("No debe de generarse excepción");
		}
	}

	@Test
	public void testDjikstraImposible() {
		setupEscenario2();
		try {
			grafo.Dijkstra("A", "D");
			fail("Debe de generarse excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Es imposible llegar de un nodo al otro");
		}
	}

	@Test
	public void testFloydWarshallVacio() {
		setupEscenario1();
		try {
			double[][] res = grafo.FloydWarshall();
			assertEquals(res.length, 0);
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testFloydWarshallConexo() {
		setupEscenario3();

		try {
			double[][] res = grafo.FloydWarshall();

			assertEquals(res[0][0], 0.0, 0);
			assertEquals(res[0][1], 5.0, 0);
			assertEquals(res[0][2], 3.0, 0);
			assertEquals(res[0][3], 9.0, 0);
			assertEquals(res[0][4], 4.0, 0);

			assertEquals(res[1][0], 5.0, 0);
			assertEquals(res[1][1], 0.0, 0);
			assertEquals(res[1][2], 2.0, 0);
			assertEquals(res[1][3], 4.0, 0);
			assertEquals(res[1][4], 3.0, 0);

			assertEquals(res[2][0], 3.0, 0);
			assertEquals(res[2][1], 2.0, 0);
			assertEquals(res[2][2], 0.0, 0);
			assertEquals(res[2][3], 6.0, 0);
			assertEquals(res[2][4], 1.0, 0);

			assertEquals(res[3][0], 9.0, 0);
			assertEquals(res[3][1], 4.0, 0);
			assertEquals(res[3][2], 6.0, 0);
			assertEquals(res[3][3], 0.0, 0);
			assertEquals(res[3][4], 5.0, 0);

			assertEquals(res[4][0], 4.0, 0);
			assertEquals(res[4][1], 3.0, 0);
			assertEquals(res[4][2], 1.0, 0);
			assertEquals(res[4][3], 5.0, 0);
			assertEquals(res[4][4], 0.0, 0);
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testFloydWarshallNoConexo() {
		setupEscenario2();

		try {
			double[][] res = grafo.FloydWarshall();

			assertEquals(res[0][0], 0.0, 0);
			assertEquals(res[0][1], 5.0, 0);
			assertEquals(res[0][2], 3.0, 0);
			assertEquals(res[0][3], Double.MAX_VALUE, 0);
			assertEquals(res[0][4], Double.MAX_VALUE, 0);

			assertEquals(res[1][0], 5.0, 0);
			assertEquals(res[1][1], 0.0, 0);
			assertEquals(res[1][2], 2.0, 0);
			assertEquals(res[1][3], Double.MAX_VALUE, 0);
			assertEquals(res[1][4], Double.MAX_VALUE, 0);

			assertEquals(res[2][0], 3.0, 0);
			assertEquals(res[2][1], 2.0, 0);
			assertEquals(res[2][2], 0.0, 0);
			assertEquals(res[2][3], Double.MAX_VALUE, 0);
			assertEquals(res[2][4], Double.MAX_VALUE, 0);

			assertEquals(res[3][0], Double.MAX_VALUE, 0);
			assertEquals(res[3][1], Double.MAX_VALUE, 0);
			assertEquals(res[3][2], Double.MAX_VALUE, 0);
			assertEquals(res[3][3], 0.0, 0);
			assertEquals(res[3][4], 5.0, 0);

			assertEquals(res[4][0], Double.MAX_VALUE, 0);
			assertEquals(res[4][1], Double.MAX_VALUE, 0);
			assertEquals(res[4][2], Double.MAX_VALUE, 0);
			assertEquals(res[4][3], 5.0, 0);
			assertEquals(res[4][4], 0.0, 0);
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testAdyacentesNoExiste() {
		setupEscenario2();

		try {
			grafo.darAdyacentes("H");
			fail("Debe de generar excepcion");
		} catch (Exception e) {
			assertEquals("No existe ningun nodo con el elemento buscado", e.getMessage());
		}
	}

	@Test
	public void testAdyacentes() {
		setupEscenario2();

		try {
			ArrayList<String> arr = grafo.darAdyacentes("A");

			assertEquals(arr.size(), 2);
			assertEquals(arr.get(0), "B");
			assertEquals(arr.get(1), "C");
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}

	@Test
	public void testAdyacentesVacio() {
		setupEscenario4();

		try {
			ArrayList<String> arr = grafo.darAdyacentes("A");

			assertEquals(arr.size(), 0);
		} catch (Exception e) {
			fail("No debe de generar excepcion");
		}
	}
	
	@Test
	public void testEliminarNodoNoExiste() {
		setupEscenario3();
		try {
			grafo.eliminarNodo("K");
			fail ("Debe de lanzar excepcion");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El nodo no existe");
		}
	}
	
	@Test
	public void testEliminarNodo() {
		setupEscenario3();
		try {
			grafo.eliminarNodo("B");
			
			assertFalse(grafo.estaNodo("B"));
			ArrayList <String> ady = grafo.darAdyacentes("A");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "C");
			
			ady = grafo.darAdyacentes("C");
			assertEquals(ady.size(), 2);
			assertEquals(ady.get(0), "A");
			assertEquals(ady.get(1), "E");
			
			ady = grafo.darAdyacentes("D");
			assertEquals(ady.size(), 1);
			assertEquals(ady.get(0), "E");
			
			ady = grafo.darAdyacentes("E");
			assertEquals(ady.size(), 2);
			assertEquals(ady.get(0), "C");
			assertEquals(ady.get(1), "D");
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEliminarAristaExcepcion() {
		setupEscenario3();
		try {
			grafo.eliminarArista("K", "A");
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Algunos de los dos nodos no existe");
		}
		
	}
	
	@Test
	public void testEliminarArista() {
		setupEscenario2();
		try {
			grafo.eliminarArista("A", "C");
			
			ArrayList <String> x = grafo.darAdyacentes("A");
			assertEquals(x.size(), 1);
			assertEquals(x.get(0), "B");
			
			x = grafo.darAdyacentes("C");
			assertEquals(x.size(), 1);
			assertEquals(x.get(0), "B");
		} catch (Exception e) {
			fail();
		}
	}

}
