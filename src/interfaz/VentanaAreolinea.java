package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaAreolinea extends JFrame {

	public VentanaAreolinea(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		VentanaAreolinea ven= new VentanaAreolinea();
		ven.setVisible(true);
	}
}
