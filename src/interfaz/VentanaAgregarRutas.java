package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mundo.Principal;

public class VentanaAgregarRutas extends JFrame{
	
	private PanelCiudades panelCiudades;
	private PanelMapa panelMapa;
	private JLabel titulo;
	
	private VentanaInicio relacionInicio;
	
	
	public VentanaAgregarRutas(VentanaInicio ventInicio) {

		relacionInicio = ventInicio;
		
		
		setTitle("Galchiner S.A");
		setSize(1500, 900);
		//setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		titulo = new JLabel("GALCHINER S.A.");
		titulo.setFont(new java.awt.Font("Antique Olive Co",0,70));
		
		
		panelCiudades = new PanelCiudades(relacionInicio);
		panelMapa = new PanelMapa(relacionInicio);
		
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		aux.add(titulo, BorderLayout.NORTH);
		aux.add(panelCiudades, BorderLayout.CENTER);
		
		add(aux, BorderLayout.CENTER);
		add(panelMapa, BorderLayout.EAST);
		
		
	
	}

	public void cerrar() {
		dispose();
	}

	public PanelCiudades getPanelCiudades() {
		return panelCiudades;
	}

	public void setPanelCiudades(PanelCiudades panelCiudades) {
		this.panelCiudades = panelCiudades;
	}
	
	
	
	
	
}
