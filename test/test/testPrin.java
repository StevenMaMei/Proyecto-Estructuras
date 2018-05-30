package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Principal;

public class testPrin {

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
			fail();
		}
	}

}
