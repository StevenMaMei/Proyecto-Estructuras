package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaAgregarRutas extends JFrame{
	
	private PanelCiudades panelCiudades;
	private PanelMapa panelMapa;
	private JLabel titulo;
	
	public VentanaAgregarRutas() {

		setTitle("Galchiner S.A");
		setSize(1500, 900);
		//setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		titulo = new JLabel("GALCHINER S.A.");
		titulo.setFont(new java.awt.Font("Antique Olive Co",0,70));
		
		
		panelCiudades = new PanelCiudades(this);
		panelMapa = new PanelMapa();
		
		
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		aux.add(titulo, BorderLayout.NORTH);
		aux.add(panelCiudades, BorderLayout.CENTER);
		
		add(aux, BorderLayout.CENTER);
		add(panelMapa, BorderLayout.EAST);
		
	
	}

	
	
	
}
