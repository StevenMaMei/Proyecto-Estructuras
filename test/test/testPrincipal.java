package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import estructuras.GrafoListaAdyacente;
import estructuras.GrafoMatriz;
import estructuras.NodoListaAdyacente;
import mundo.Ciudad;
import mundo.Principal;

public class testPrincipal {

	private Principal principal;
	private void setupEscenario1(){
		try {
			principal=new Principal(Principal.LISTA);
		} catch (Exception e) {
			fail();
		}
	}
	
	private void setupEscenario2(){
		try {
			principal=new Principal(Principal.MATRIZ);
		} catch (Exception e) {
			fail();
		}
	}
	
	private void setupEscenario3(){
		try {
			principal= new Principal(Principal.LISTA);
			principal.agregarVueloDirecto(Principal.POS_BRASILIA, Principal.POS_CALI, 100, 300);
			principal.agregarVueloDirecto(Principal.POS_CARACAS, Principal.POS_LIMA, 200, 500);
		} catch (Exception e) {
			fail();
		}
	}
	
	private void setupEscenario4(){
		try {
			principal= new Principal(Principal.MATRIZ);
			principal.agregarVueloDirecto(Principal.POS_BRASILIA, Principal.POS_CALI, 100, 300);
			principal.agregarVueloDirecto(Principal.POS_CARACAS, Principal.POS_LIMA, 200, 500);
		} catch (Exception e) {
			fail();
		}
		
	}
	
	private void setupEscenario5(){
		try {
			principal= new Principal(Principal.LISTA);
			principal.agregarVueloDirecto(0, 2, 100, 300);
			principal.agregarVueloDirecto(2, 3, 93, 300);
			principal.agregarVueloDirecto(2, 4, 90, 300);
			principal.agregarVueloDirecto(4, 5, 50, 300);
			principal.agregarVueloDirecto(5, 6, 15, 300);
			principal.agregarVueloDirecto(6, 1, 10, 300);
			principal.agregarVueloDirecto(1, 7, 200, 300);
			principal.agregarVueloDirecto(7, 11, 28, 300);
			principal.agregarVueloDirecto(11, 8, 80, 300);
			principal.agregarVueloDirecto(8, 9, 90, 300);
			principal.agregarVueloDirecto(9, 10, 11, 300);
			principal.agregarVueloDirecto(11, 10, 19, 300);
			principal.agregarVueloDirecto(11, 12, 15, 300);
			principal.agregarVueloDirecto(12, 13, 35, 300);
			principal.agregarAerolinea("Avianca");
			
			principal.reiniciarGrafo();
			
			principal.agregarVueloDirecto(0, 1, 130, 250);
			principal.agregarVueloDirecto(1, 6, 18, 250);
			principal.agregarVueloDirecto(6, 8, 73, 250);
			principal.agregarVueloDirecto(8, 9, 120, 250);
			principal.agregarVueloDirecto(9, 4, 90, 250);
			principal.agregarVueloDirecto(4, 5, 12, 250);
			principal.agregarVueloDirecto(5, 2, 32, 250);
			principal.agregarVueloDirecto(2, 3, 212, 250);
			principal.agregarVueloDirecto(8, 7, 104, 250);
			principal.agregarVueloDirecto(7, 11, 20, 250);
			principal.agregarVueloDirecto(11, 10, 210, 250);
			principal.agregarVueloDirecto(11, 13, 98, 250);
			principal.agregarVueloDirecto(7, 12, 105, 250);
			principal.agregarVueloDirecto(12, 13, 79, 250);
			principal.agregarAerolinea("Despegar");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			
		}
	}
	
	@Test
	public void pruebaCambiarRepresentacion(){
		setupEscenario1();
		try {
			principal.cambiarRepresentacion();
			assertTrue(principal.tipoGrafo==Principal.MATRIZ);
		} catch (Exception e) {
			fail();
		}
		setupEscenario2();
		try {
			principal.cambiarRepresentacion();
			assertTrue(principal.tipoGrafo==Principal.LISTA);
		} catch (Exception e) {
			fail();
		}
		
		setupEscenario3();
		try {
			principal.cambiarRepresentacion();
			GrafoMatriz<Ciudad> grafo= (GrafoMatriz<Ciudad>) principal.getGrafoCandidato()[principal.IND_PRECIO];
			GrafoMatriz<Ciudad> grafo1= (GrafoMatriz<Ciudad>) principal.getGrafoCandidato()[principal.IND_PRECIO];
			assertTrue(grafo.equals(grafo1));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			
		}
		
	}
	
	@Test
	public void pruebaReiniciarGrafo(){
		setupEscenario4();
		try {
			principal.reiniciarGrafo();
			GrafoMatriz<Ciudad> grafo=(GrafoMatriz<Ciudad>) principal.getGrafoCandidato()[Principal.IND_PRECIO];
			double[][] adyacencia=grafo.darMatrizAdyacencia();
			for(int i=0;i<adyacencia.length;i++){
				for(int j=0;j<adyacencia.length;j++){
					assertTrue(adyacencia[i][j]==0||adyacencia[i][j] == Double.MAX_VALUE);
				}
			}
		} catch (Exception e) {
			fail();
		}
		
		setupEscenario3();
		try {
			principal.reiniciarGrafo();
			GrafoListaAdyacente<Ciudad> grafo= (GrafoListaAdyacente<Ciudad>) principal.getGrafoCandidato()[Principal.IND_PRECIO];
			HashMap<Ciudad, NodoListaAdyacente<Ciudad>> nodos=grafo.darNodos();
			for(Ciudad actual: nodos.keySet()){
				assertTrue(nodos.get(actual).darAdyacentes().size()==0);
			}
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void pruebaEliminarVuelo(){
		setupEscenario4();
		try {
			principal.eliminarVueloDirecto(Principal.POS_CALI, Principal.POS_BRASILIA);
			GrafoMatriz<Ciudad> grafo=(GrafoMatriz<Ciudad>) principal.getGrafoCandidato()[Principal.IND_PRECIO];
			double[][] matriz=grafo.darMatrizAdyacencia();
			assertTrue(matriz[Principal.POS_CALI][Principal.POS_BRASILIA]==Double.MAX_VALUE);
			assertTrue(matriz[Principal.POS_BRASILIA][Principal.POS_CALI]==Double.MAX_VALUE);
		} catch (Exception e) {
			fail();
		}
		setupEscenario4();
		try {
			principal.eliminarVueloDirecto(0, 5);
			GrafoMatriz<Ciudad> grafo=(GrafoMatriz<Ciudad>) principal.getGrafoCandidato()[Principal.IND_PRECIO];
			double[][] matriz=grafo.darMatrizAdyacencia();
			assertTrue(matriz[0][5]==Double.MAX_VALUE);
			assertTrue(matriz[5][0]==Double.MAX_VALUE);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void pruebaDarRutaMasEconomica(){
		setupEscenario5();
		try {
			String[][] r=principal.darRutaMasEconomica(9, 13);
			
			for(int i=0;i<r.length;i++){
				for(int j=0;j<r[0].length;j++){
					if(i==0&&j==0){
						assertTrue(r[i][j].equals("Avianca"));
					}else if(i==0&&j==2){
						assertTrue(r[i][j].equals(Principal.CIUDADES[9].getNombreCiudad()));
					
					}else if(i==0&&j==3){
						assertTrue(r[i][j].equals(Principal.CIUDADES[10].getNombreCiudad()));
					
					}else if(i==0&&j==4){
						assertTrue(r[i][j].equals(Principal.CIUDADES[11].getNombreCiudad()));
					
					}else if(i==0&&j==5){
						assertTrue(r[i][j].equals(Principal.CIUDADES[12].getNombreCiudad()));
					
					}else if(i==0&&j==6){
						assertTrue(r[i][j].equals(Principal.CIUDADES[13].getNombreCiudad()));
					}else if(i==1&&j==0){
						assertTrue(r[i][j].equals("Despegar"));
					
					}else if(i==1&&j==1){
						assertTrue(r[i][j].equals("342.0"));
					
					}else if(i==1&&j==2){
						assertTrue(r[i][j].equals(Principal.CIUDADES[9].getNombreCiudad()));
					
					}else if(i==1&&j==3){
						assertTrue(r[i][j].equals(Principal.CIUDADES[8].getNombreCiudad()));
					
					}else if(i==1&&j==4){
						assertTrue(r[i][j].equals(Principal.CIUDADES[7].getNombreCiudad()));
					
					}else if(i==1&&j==5){
						assertTrue(r[i][j].equals(Principal.CIUDADES[11].getNombreCiudad()));
					
					}else if(i==1&&j==6){
						assertTrue(r[i][j].equals(Principal.CIUDADES[13].getNombreCiudad()));
					
					}
				}
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test
	public void probarDarRutaMasCorta(){
		setupEscenario5();
		try {
			String[][] r=principal.darRutaMasCorta(9, 13);
			
			for(int i=0;i<r.length;i++){
				for(int j=0;j<r[0].length;j++){
					if(i==0&&j==0){
						assertTrue(r[i][j].equals("Avianca"));
					}else if(i==0&&j==1){
						assertTrue(r[i][j].equals("16510.0"));
					
					}else if(i==0&&j==2){
						assertTrue(r[i][j].equals(Principal.CIUDADES[9].getNombreCiudad()));
					
					}else if(i==0&&j==3){
						assertTrue(r[i][j].equals(Principal.CIUDADES[8].getNombreCiudad()));
					
					}else if(i==0&&j==4){
						
						assertTrue(r[i][j].equals(Principal.CIUDADES[11].getNombreCiudad()));
					
					}else if(i==0&&j==5){
						assertTrue(r[i][j].equals(Principal.CIUDADES[12].getNombreCiudad()));
					
					}else if(i==0&&j==6){
						assertTrue(r[i][j].equals(Principal.CIUDADES[13].getNombreCiudad()));
					
					}else if(i==1&&j==0){
						assertTrue(r[i][j].equals("Despegar"));
					
					}else if(i==1&&j==1){
						assertTrue(r[i][j].equals("17756.0"));
					
					}else if(i==1&&j==2){
						assertTrue(r[i][j].equals(Principal.CIUDADES[9].getNombreCiudad()));
					
					}else if(i==1&&j==3){
						assertTrue(r[i][j].equals(Principal.CIUDADES[8].getNombreCiudad()));
					
					}else if(i==1&&j==4){
						assertTrue(r[i][j].equals(Principal.CIUDADES[7].getNombreCiudad()));
					
					}else if(i==1&&j==5){
						assertTrue(r[i][j].equals(Principal.CIUDADES[11].getNombreCiudad()));
					
					}else if(i==1&&j==6){
						assertTrue(r[i][j].equals(Principal.CIUDADES[13].getNombreCiudad()));
					
					}
				}
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}
	
	@Test
	public void probarDarRutaMasRapida(){
		setupEscenario5();
		try {
			String[][] r=principal.darRutaMasRapida(9, 13);
			
			for(int i=0;i<r.length;i++){
				for(int j=0;j<r[0].length;j++){
					if(i==0&&j==0){
						assertTrue(r[i][j].equals("Avianca"));
					}else if(i==0&&j==2){
						assertTrue(r[i][j].equals(Principal.CIUDADES[9].getNombreCiudad()));
					
					}else if(i==0&&j==3){
						assertTrue(r[i][j].equals(Principal.CIUDADES[8].getNombreCiudad()));
					
					}else if(i==0&&j==4){
						
						assertTrue(r[i][j].equals(Principal.CIUDADES[11].getNombreCiudad()));
					
					}else if(i==0&&j==5){
						assertTrue(r[i][j].equals(Principal.CIUDADES[12].getNombreCiudad()));
					
					}else if(i==0&&j==6){
						assertTrue(r[i][j].equals(Principal.CIUDADES[13].getNombreCiudad()));
					
					}else if(i==1&&j==0){
						assertTrue(r[i][j].equals("Despegar"));
					
					}else if(i==1&&j==2){
						assertTrue(r[i][j].equals(Principal.CIUDADES[9].getNombreCiudad()));
					
					}else if(i==1&&j==3){
						assertTrue(r[i][j].equals(Principal.CIUDADES[8].getNombreCiudad()));
					
					}else if(i==1&&j==4){
						assertTrue(r[i][j].equals(Principal.CIUDADES[7].getNombreCiudad()));
					
					}else if(i==1&&j==5){
						assertTrue(r[i][j].equals(Principal.CIUDADES[11].getNombreCiudad()));
					
					}else if(i==1&&j==6){
						assertTrue(r[i][j].equals(Principal.CIUDADES[13].getNombreCiudad()));
					
					}
				}
			}
		} catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}
	
	

}
