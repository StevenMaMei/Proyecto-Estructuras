package test;


import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import estructuras.GrafoListaAdyacente;
import estructuras.INodoLista;
import estructuras.ListaPeso;
import estructuras.NodoListaAdyacente;

import static org.junit.Assert.*;

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
	
	@Test
	public void testPrim(){
		setupEscenario2();
		try {
			grafo.prim();
			fail("Excepción expectada");
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Grafo no conexo"));
		}
		setupEscenario3();
		try {
			GrafoListaAdyacente<Character> g=(GrafoListaAdyacente<Character>) grafo.prim();
			assertTrue(g.darNodo('A').darPesoAdyacente(g.darNodo('C'))!= null);
			assertTrue(g.darNodo('A').darAdyacentes().size()==1);
			
			assertTrue(g.darNodo('B').darPesoAdyacente(g.darNodo('C'))!= null);
			assertTrue(g.darNodo('B').darPesoAdyacente(g.darNodo('D'))!= null);
			assertTrue(g.darNodo('B').darAdyacentes().size()==2);
			
			assertTrue(g.darNodo('C').darPesoAdyacente(g.darNodo('A'))!= null);
			assertTrue(g.darNodo('C').darPesoAdyacente(g.darNodo('B'))!= null);
			assertTrue(g.darNodo('C').darPesoAdyacente(g.darNodo('E'))!= null);
			assertTrue(g.darNodo('C').darAdyacentes().size()==3);
			
			assertTrue(g.darNodo('D').darPesoAdyacente(g.darNodo('B'))!= null);
			assertTrue(g.darNodo('A').darAdyacentes().size()==1);
			
			assertTrue(g.darNodo('E').darPesoAdyacente(g.darNodo('C'))!= null);
			assertTrue(g.darNodo('A').darAdyacentes().size()==1);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("No se espera excepción");
		}
	}
	
	@Test
	public void testDijkstra(){
		setupEscenario3();
		try {
			grafo.Dijkstra('A', 'H');
			fail("Debe haber una excepción");
		} catch (Exception e) {
			e.getMessage().equals("Uno de los nodos no existe");
		}
		setupEscenario3();
		try {
			ListaPeso r=grafo.Dijkstra('A', 'D');
			assertTrue(r.getTotal()==9.0);
			LinkedList<Character> camino=r.getList();
			assertTrue(camino.size()==4);
			Character[] comparacion={'A','C','E','D'};
			Iterator<Character> it=camino.iterator();
			int i=0;
			while(it.hasNext()){
				assertTrue(comparacion[i]==it.next());
				i++;
			}
			
		} catch (Exception e) {
			fail("No debe haber excepción");
		}
		setupEscenario2();
		try {
			grafo.Dijkstra('A', 'D');
			fail("Excepción esperada");
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Imposible llegar del nodo1 al nodo2"));
		}
	}
	
	@Test
	public void testFloyd(){
		setupEscenario3();
		double[][] comparacion={{0.0,5.0,3.0,9.0,4.0},{5.0,0.0,2.0,4.0,3.0},{3.0,2.0,0.0,6.0,1.0},{9.0,4.0,6.0,0.0,5.0},
				{4.0,3.0,1.0,5.0,0.0}};
		try {
			double[][] r=grafo.FloydWarshall();
			for(int i=0;i<comparacion.length;i++){
				for(int j=0;j<comparacion.length;j++){
					assertTrue(r[i][j]==comparacion[i][j]);
				}
			}
		} catch (Exception e) {
			fail("Excepción no esperada");
		}
		
		setupEscenario2();
		double[][] comparacion2={{0.0,5.0,3.0,Double.MAX_VALUE,Double.MAX_VALUE},{5.0,0.0,2.0,Double.MAX_VALUE,Double.MAX_VALUE},
				{3.0,2.0,0.0,Double.MAX_VALUE,Double.MAX_VALUE},{Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,0.0,5.0},
				{Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,5.0,0.0}};
		try {
			double[][] r= grafo.FloydWarshall();
			for(int i=0;i<comparacion2.length;i++){
				for(int j=0;j<comparacion2.length;j++){
					assertTrue(r[i][j]==comparacion2[i][j]);
				}
			}
		} catch (Exception e) {
			fail("Excepción no esperada");
		}
		
	}
	
	@Test
	public void testDarAdyacentes(){
		setupEscenario2();
		try {
			grafo.darAdyacentes('H');
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Nodo no encontrado"));
		}
		setupEscenario2();
		try {
			ArrayList<Character> adyacentes=grafo.darAdyacentes('A');
			Character[] comparacion={'B','C'};
			for(int i=0;i<adyacentes.size();i++){
				assertTrue(adyacentes.get(i)==comparacion[i]);
			}
		} catch (Exception e) {
			fail("Excepción no esperada");
		}
		setupEscenario4();
		try {
			assertTrue(grafo.darAdyacentes('A').size()==0);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEliminarNodo(){
		setupEscenario3();
		try {
			grafo.eliminarNodo('K');
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Nodo no existente"));
		}
		setupEscenario3();
		try {
			NodoListaAdyacente<Character> eliminado=grafo.darNodo('B');
			grafo.eliminarNodo('B');
			HashMap<Character, NodoListaAdyacente<Character>> nodos=grafo.darNodos();
			for(Character actual: nodos.keySet()){
				assertTrue(actual!= 'B');
				assertTrue(nodos.get(actual).darPesoAdyacente(eliminado)==null);
			}
		} catch (Exception e) {
			fail();
			
		}
	}
	
	@Test
	public void testEliminarArista(){
		setupEscenario3();
		try {
			grafo.eliminarArista('K', 'A');
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().equals("Arista o nodo no existente"));
		}
		setupEscenario2();
		try {
			grafo.eliminarArista('A', 'C');
			HashMap<Character, NodoListaAdyacente<Character>> nodos=grafo.darNodos();
			NodoListaAdyacente<Character> nodo1=grafo.darNodo('A');
			NodoListaAdyacente<Character> nodo2=grafo.darNodo('C');
			assertTrue(nodo1.darPesoAdyacente(nodo2)==null);
			assertTrue(nodo1.darAdyacentes().contains(nodo2)==false);
			assertTrue(nodo2.darPesoAdyacente(nodo1)==null);
			assertTrue(nodo2.darAdyacentes().contains(nodo1)==false);
		} catch (Exception e) {
			fail();
		}
	}
	

}
