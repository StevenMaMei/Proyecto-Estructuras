package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mundo.Principal;

public class VentanaAreolinea extends JFrame {

	private PanelUsuario panelUsuario;
	private PanelAerolinea panelAereolinea;
	private PanelBanner panelBanner;
	private PanelMapa panelMapa;
	
	private VentanaInicio relacionInicio;

	
	
	public VentanaAreolinea(VentanaInicio ventInicio){
		
		relacionInicio = ventInicio;
		
		setLayout(new BorderLayout());
		
		
		setTitle("Galchiner S.A");
		setSize(800, 750);
		//setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panelUsuario= new PanelUsuario(relacionInicio);
		panelAereolinea= new PanelAerolinea(relacionInicio);
		panelBanner = new PanelBanner();
		panelMapa = new PanelMapa(relacionInicio);
		
		
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
	
	
	public void cambiarGrafo() {
		try {
			//conexion.cambiarRepresentacion();
			JOptionPane.showMessageDialog(null, "Se ha cambiado el grafo correctamente");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el cambio de grafo");
			e.printStackTrace();
		}
	}
	public PanelAerolinea getPanelAereolinea() {
		return panelAereolinea;
	}
	public void setPanelAereolinea(PanelAerolinea panelAereolinea) {
		this.panelAereolinea = panelAereolinea;
	}
	
	
	

	
	
	
}
