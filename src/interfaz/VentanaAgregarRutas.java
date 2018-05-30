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
	
	private Principal conexion;
	private VentanaAreolinea vent;
	
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
		panelMapa = new PanelMapa(vent);
		
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
	
	public void agregarVueloDirecto(int ciudad1, int ciudad2, int precio, double velocidad) {
		
		
		try {
			conexion.agregarVueloDirecto(ciudad1, ciudad2, precio, velocidad);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "El vuelo no se a podido agregar correctamente, intente de nuevo");
		}
	}
	
	public void eliminarVueloDirecto(int ciudad1, int ciudad2) {
		
		
		try {
			conexion.eliminarVueloDirecto(ciudad1, ciudad2);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "El vuelo no se a podido agregar correctamente, intente de nuevo");
		}
	}
	
	
}
