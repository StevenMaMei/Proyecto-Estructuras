package interfaz;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelCiudades extends JPanel {

	private JTextField txtPrecio;
	private JLabel lbPrecio;
	private JLabel ciudadActual;
	private JLabel ciudadDestino;
	private JLabel espacio;
	
	private PanelBotonesCiudades panelbotones;
	
	
	private VentanaInicio relacionInicio;
	
	private JComboBox<String> ciudadesActuales;
	private JComboBox<String> ciudadesDestino;
	
	public PanelCiudades(VentanaInicio ventInicio ) {
	
		
		relacionInicio = ventInicio;
		
		setLayout(new GridLayout(8, 1));
		
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Opciones");
		setBorder(titulo);
		
		panelbotones = new PanelBotonesCiudades(relacionInicio);
		ciudadesActuales = new JComboBox<>();
		ciudadesDestino = new JComboBox<>();
		
		ciudadesDestino.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
		ciudadesActuales.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
	
		espacio = new JLabel("");
		
		ciudadesActuales.addItem("Cali (Colombia)");
		ciudadesActuales.addItem("Buenos Aires (Argentina)");
		ciudadesActuales.addItem("Brasilia (Brasil)");
		ciudadesActuales.addItem("Santiago de Chile (Chile)");
		ciudadesActuales.addItem("Quito (Ecuador)");
		ciudadesActuales.addItem("Asunción (Paraguay)");
		ciudadesActuales.addItem("Lima (Perú)");
		ciudadesActuales.addItem("Carácas (Venezuela)");
		ciudadesActuales.addItem("Ciudad de Mexico (Mexico)");
		ciudadesActuales.addItem("Washington D.C. (Estados Unidos)");
		ciudadesActuales.addItem("Panamá (Panamá)");
		ciudadesActuales.addItem("Ottawa (Canada)");
		ciudadesActuales.addItem("Sucre (Bolivia)");
		
		
		ciudadesDestino.addItem("Cali (Colombia)");
		ciudadesDestino.addItem("Buenos Aires (Argentina)");
		ciudadesDestino.addItem("Brasilia (Brasil)");
		ciudadesDestino.addItem("Santiago de Chile (Chile)");
		ciudadesDestino.addItem("Quito (Ecuador)");
		ciudadesDestino.addItem("Asunción (Paraguay)");
		ciudadesDestino.addItem("Lima (Perú)");
		ciudadesDestino.addItem("Carácas (Venezuela)");
		ciudadesDestino.addItem("Ciudad de Mexico (Mexico)");
		ciudadesDestino.addItem("Washington D.C. (Estados Unidos)");
		ciudadesDestino.addItem("Panamá (Panamá)");
		ciudadesDestino.addItem("Ottawa (Canada)");
		ciudadesDestino.addItem("Sucre (Bolivia)");
		
		lbPrecio = new JLabel("Precio de viaje : ");
		lbPrecio.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
		
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
	
		ciudadActual = new JLabel("Ciudad Actual: ");
		ciudadActual.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
		
		ciudadDestino = new JLabel("Ciudad Destino: ");
		ciudadDestino.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
		
		
		add(ciudadActual);
		add(ciudadesActuales);
		add(ciudadDestino);
		add(ciudadesDestino);
		add(lbPrecio);
		add(txtPrecio);
		add(espacio);
		add(panelbotones);
		
	}
	
	
	
	
	
	public int getCiudadActual() {
		int indiceCiudad = ciudadesActuales.getSelectedIndex();
		return indiceCiudad;
	}
	
	public int getCiudadDestino() {
		int indiceCiudad = ciudadesActuales.getSelectedIndex();
		return indiceCiudad;
	}


	public String getPrecio() {
		return txtPrecio.getText();
	}
	
	
	
}
