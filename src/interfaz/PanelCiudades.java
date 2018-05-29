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
	
	private VentanaAgregarRutas vr;
	
	private JComboBox<String> ciudadesActuales;
	private JComboBox<String> ciudadesDestino;
	
	public PanelCiudades(VentanaAgregarRutas vr) {
	
		this.vr = vr;
		setLayout(new GridLayout(8, 1));
		
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Opciones");
		setBorder(titulo);
		
		panelbotones = new PanelBotonesCiudades(vr);
		ciudadesActuales = new JComboBox<>();
		ciudadesDestino = new JComboBox<>();
		
		ciudadesDestino.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
		ciudadesActuales.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
	
		espacio = new JLabel("");
		
		ciudadesActuales.addItem("Asunci�n (Paraguay)");
		ciudadesActuales.addItem("Buenos Aires (Argentina)");
		ciudadesActuales.addItem("Brasilia (Brasil)");
		ciudadesActuales.addItem("Cali (Colombia)");
		ciudadesActuales.addItem("Car�cas (Venezuela)");
		ciudadesActuales.addItem("Ciudad de Mexico (Mexico)");
		ciudadesActuales.addItem("Montevideo (Uruguay)");
		ciudadesActuales.addItem("Lima (Per�)");
		ciudadesActuales.addItem("Ottawa (Canada)");
		ciudadesActuales.addItem("Panam� (Panam�)");
		ciudadesActuales.addItem("Quito (Ecuador)");
		ciudadesActuales.addItem("Santiago de Chile (Chile)");
		ciudadesActuales.addItem("Sucre (Bolivia)");
		ciudadesActuales.addItem("Washington D.C. (Estados Unidos)");
		
		ciudadesDestino.addItem("Asunci�n (Paraguay)");
		ciudadesDestino.addItem("Buenos Aires (Argentina)");
		ciudadesDestino.addItem("Brasilia (Brasil)");
		ciudadesDestino.addItem("Cali (Colombia)");
		ciudadesDestino.addItem("Car�cas (Venezuela)");
		ciudadesDestino.addItem("Ciudad de Mexico (Mexico)");
		ciudadesDestino.addItem("Montevideo (Uruguay)");
		ciudadesDestino.addItem("Lima (Per�)");
		ciudadesDestino.addItem("Ottawa (Canada)");
		ciudadesDestino.addItem("Panam� (Panam�)");
		ciudadesDestino.addItem("Quito (Ecuador)");
		ciudadesDestino.addItem("Santiago de Chile (Chile)");
		ciudadesDestino.addItem("Sucre (Bolivia)");
		ciudadesDestino.addItem("Washington D.C. (Estados Unidos)");
		
		
		
		
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
	
	public String getPrecio() {
		return txtPrecio.getText();
	}
	
	
	
}
