package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaAreolinea extends JFrame {

	private PanelUsuario panelUsuario;
	private PanelAerolinea panelAereolinea;
	private PanelBanner panelBanner;
	private PanelMapa panelMapa;
	
	
	
	public VentanaAreolinea(){
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelUsuario= new PanelUsuario(this);
		panelAereolinea= new PanelAerolinea(this);
		panelBanner = new PanelBanner();
		panelMapa = new PanelMapa();
		
		
		add(panelBanner, BorderLayout.NORTH);
		add(panelAereolinea,BorderLayout.WEST);
		add(panelUsuario, BorderLayout.CENTER);
		add(panelMapa, BorderLayout.EAST);
		pack();
		
	}
	//TODO
	public String[] darCiudades(){
		return null;
	}
	
	
}
