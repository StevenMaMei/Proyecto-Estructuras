package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import mundo.Principal;

public class VentanaAreolinea extends JFrame {

	private PanelUsuario panelUsuario;
	private PanelAerolinea panelAereolinea;
	private PanelBanner panelBanner;
	private PanelMapaBoton panelMapa;
	
	private Principal conexion;
	
	public VentanaAreolinea(){
		setLayout(new BorderLayout());
		
		
		setTitle("Galchiner S.A");
		setSize(800, 750);
		//setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelUsuario= new PanelUsuario(this);
		panelAereolinea= new PanelAerolinea(this);
		panelBanner = new PanelBanner();
		panelMapa = new PanelMapaBoton(this);
		
		
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
	
	
	
	//Listas companias 
	
	public String[][] listaDistancia(){
		String [][] matriz = null;
		//conexion.
		return matriz;
	}
	
	
	public String[][] listaPrecio(){
		String [][] matriz = null;
		
		
		
		return matriz;
	}
	
	public String[][] listaTiempo(){
		String [][] matriz = null;
		
		return matriz;
	}
	
	
}
