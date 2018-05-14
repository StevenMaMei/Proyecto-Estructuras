package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaAreolinea extends JFrame {

	private PanelUsuario panelUsuario;
	private PanelAerolinea panelAereolinea;
	private PanelBanner panelBanner;
	
	
	
	public VentanaAreolinea(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelUsuario= new PanelUsuario(this);
		panelAereolinea= new PanelAerolinea(this);
		panelBanner = new PanelBanner();
		
		add(panelBanner, BorderLayout.NORTH);
		add(panelAereolinea,BorderLayout.EAST);
		add(panelUsuario, BorderLayout.CENTER);
		pack();
		
	}
	//TODO
	public String[] darCiudades(){
		return null;
	}
	
	public static void main(String[] args){
		VentanaAreolinea ven= new VentanaAreolinea();
		ven.setVisible(true);
	}
}
