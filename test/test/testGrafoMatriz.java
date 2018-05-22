package test;

import static org.junit.Assert.*;


import org.junit.jupiter.api.Test;

import estructuras.GrafoMatriz;

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
			fail ("No se debe de generar excepcion");
		}
	}
	
	@Test
	public void testAgregarNodoNoVacio() {
		setupEscenario2();
		try {
			grafo.agregarNodo("F");
			assertTrue(grafo.estaNodo("F"));
		} catch (Exception e) {
			fail ("No debe de generar excepcion");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAgregarExistente() {
		setupEscenario2();
		try {
			grafo.agregarNodo("A");
			fail ("Debe de generarse una excepcion");
		} catch (Exception e) {
			assertEquals (e.getMessage(), "El nodo ya existe");
		}
	}
	
	@Test
	public void testAgregarLimite() {
		setupEscenario4();
		try {
			grafo.agregarNodo("C");
			fail ("Se debe de generar excepcion");
		} catch (Exception e) {
			assertEquals (e.getMessage(), "Ha excedido el maximo de nodos permitidos");
		}
	}
}
