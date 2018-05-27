package test;

import static org.junit.Assert.*;

import org.junit.Test;

import estructuras.GrafoListaAdyacente;

public class TestGrafoListaAdyacente {

	private GrafoListaAdyacente<Character> grafo;
	
	private void setupEscenario1(){
		grafo= new GrafoListaAdyacente<>(6);
	}
	private void setupEscenario2(){
		grafo=new GrafoListaAdyacente<>(6);
		try {
			grafo.agregarNodo('A');
			grafo.agregarNodo('B');
			grafo.agregarNodo('C');
			grafo.agregarNodo('D');
			grafo.agregarNodo('E');
			grafo.generarArista('A', 'B', 7);
			grafo.generarArista('A', 'C', 3);
			grafo.generarArista('B', 'C', 2);
			grafo.generarArista('D', 'E', 5);
		} catch (Exception e) {
			fail("No debería sacar excepción");
		}
	}
	private void setupEscenario3(){
		grafo= new GrafoListaAdyacente<>(6);
		
		try {
			grafo.agregarNodo('A');
			grafo.agregarNodo('B');
			grafo.agregarNodo('C');
			grafo.agregarNodo('D');
			grafo.agregarNodo('E');
			grafo.generarArista('A', 'B', 7);
			grafo.generarArista('A', 'C', 3);
			grafo.generarArista('B', 'C', 2);
			grafo.generarArista('B', 'D', 4);
			grafo.generarArista('C', 'E', 1);
			grafo.generarArista('D', 'E', 5);
		} catch (Exception e) {
			fail("No debería salir excepción");
		}
	}
	private void setupEscenario4(){
		grafo= new GrafoListaAdyacente<>(2);
		try {
			grafo.agregarNodo('A');
			grafo.agregarNodo('B');
		} catch (Exception e) {
			fail("No debería sacar excepción");
		}
	}
	
	@Test
	public void testAgregarNodo(){
		setupEscenario1();
		try {
			grafo.agregarNodo('A');
			assertTrue(grafo.darNodo('A')!= null);
		} catch (Exception e) {
			fail("No debe haber error");
		}
		setupEscenario2();
		try {
			grafo.agregarNodo('F');
			assertTrue(grafo.darNodo('F')!= null);
		} catch (Exception e) {
			fail("No debe haber error");
		}
		setupEscenario2();
		try {
			grafo.agregarNodo('A');
			fail("Debe tirar excepción");
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Nodo ya existente"));
			
		}
		setupEscenario4();
		try {
			grafo.agregarNodo('C');
			fail("Debe tirar excepción");
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Número máximo de nodos alcanzados"));
		}
		
	}
	
	@Test
	public void testGenerarArista(){
		setupEscenario4();
		try {
			grafo.generarArista('A', 'B', 1);
			assertTrue(grafo.darNodo('A').darPesoAdyacente(grafo.darNodo('B'))==1.0);
		} catch (Exception e) {
			fail("No genera excepción");
		}
		setupEscenario4();
		try {
			grafo.generarArista('A', 'C',1);
			fail("Debe tirar excepción");
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Uno de los nodos no existe"));
		}
	}
	
	@Test
	public void testBfsConParametro(){
		setupEscenario2();
		try {
			assertTrue(grafo.recorridoBFS('A')==3);
//			System.out.println(grafo.recorridoBFS('A'));
		} catch (Exception e) {
		}
		setupEscenario3();
		try {
			assertTrue(grafo.recorridoBFS('C')==5);
			assertTrue(grafo.darNodo('C').getPadre()==null);
			assertTrue(grafo.darNodo('A').getPadre().getElemento()=='C');
			assertTrue(grafo.darNodo('B').getPadre().getElemento()=='C');
			assertTrue(grafo.darNodo('D').getPadre().getElemento()=='B');
			assertTrue(grafo.darNodo('E').getPadre().getElemento()=='C');
			
			
		} catch (Exception e) {
			fail("No debe haber excepción");
		}
		
		setupEscenario3();
		try {
			grafo.recorridoBFS('H');
			fail();
		} catch (Exception e) {
			assertTrue("Nodo existe el nodo".equals(e.getMessage()));
		}
	}
	
	@Test
	public void testRecorridoBfs(){
		setupEscenario2();
		assertTrue(grafo.recorridoBFS()==3);
		assertTrue(grafo.darNodo('C').getPadre().getElemento()=='A');
		assertTrue(grafo.darNodo('B').getPadre().getElemento()=='A');
		assertTrue(grafo.darNodo('A').getPadre()==null);
		
		setupEscenario1();
		assertTrue(grafo.recorridoBFS()==0);
	}
	
	@Test
	public void testDFS(){
		setupEscenario2();
		grafo.recorridoDFS();
		assertTrue(grafo.darNodo('A').getPadre()==null);
		assertTrue(grafo.darNodo('C').getPadre().getElemento()=='A');
		assertTrue(grafo.darNodo('B').getPadre().getElemento()=='C');
		assertTrue(grafo.darNodo('D').getPadre()==null);
		assertTrue(grafo.darNodo('E').getPadre().getElemento()=='D');
		
		setupEscenario3();
		grafo.recorridoDFS();
		assertTrue(grafo.darNodo('A').getPadre()==null);
		assertTrue(grafo.darNodo('C').getPadre().getElemento()=='A');
		assertTrue(grafo.darNodo('E').getPadre().getElemento()=='C');
		assertTrue(grafo.darNodo('D').getPadre().getElemento()=='E');
		assertTrue(grafo.darNodo('B').getPadre().getElemento()=='D');
	}
	

}
