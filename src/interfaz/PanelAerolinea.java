package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelAerolinea extends JPanel implements ActionListener {

	private String nombreAerolinea ;
	private double velocidadVuelos;
	
	private VentanaAreolinea principal;
	private JTextField textCantCiudadesSeleccionadas;
	private JButton btonSeleccionarCiudades;
	private JButton btonAgregarEscalas;
	private JButton btonCrearFinalizar;
	private ArrayList<String> ciudadesSeleccionadas;
	private ArrayList<String> relaciones;
	
	private JLabel espacio1;
	private JLabel espacio2;
	private JLabel espacio3;
	private JLabel espacio4;
	
	
	
	public final static String BTON_SELECCIONAR_CIUDADES= "seleCiudades";
	public final static String BTON_AGREGAR_ESCALAS="agrEscalas";
	public final static String BTON_FINALIZAR="fnl";
	
	public PanelAerolinea(VentanaAreolinea p){
		principal= p;
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Aerolinea");
		titulo.setTitleColor(Color.BLACK);
		setBorder(titulo);
		
		setLayout(new GridLayout(7, 1));
		
		espacio1 = new JLabel("");
		espacio2 = new JLabel("");
		espacio3 = new JLabel("");
		espacio4 = new JLabel("");
		
		
		textCantCiudadesSeleccionadas= new JTextField();
		textCantCiudadesSeleccionadas.setEditable(false);
		
		ciudadesSeleccionadas= new ArrayList<>();
		relaciones = new ArrayList<>();
		
		btonSeleccionarCiudades= new JButton("Seleccionar ciudades disponibles");
		btonSeleccionarCiudades.addActionListener(this);
		btonSeleccionarCiudades.setActionCommand(BTON_SELECCIONAR_CIUDADES);
		
		btonAgregarEscalas= new JButton("Agregar las rutas");
		btonAgregarEscalas.addActionListener(this);
		btonAgregarEscalas.setActionCommand(BTON_AGREGAR_ESCALAS);
		
		btonCrearFinalizar= new JButton("Finalizar");
		btonCrearFinalizar.addActionListener(this);
		btonCrearFinalizar.setActionCommand(BTON_FINALIZAR);
		
		btonAgregarEscalas.setFont(new java.awt.Font("Antique Olive Co", 0, 20));
		btonCrearFinalizar.setFont(new java.awt.Font("Antique Olive Co", 0, 20));
		btonSeleccionarCiudades.setFont(new java.awt.Font("Antique Olive Co", 0, 20));
		
		add(espacio1);
		add(btonSeleccionarCiudades);
		add(espacio2);
		add(btonAgregarEscalas);
		add(espacio3);
		add(btonCrearFinalizar);
		add(espacio4);
	}
	
	public void modificarTextCantCiudades(String m){
		textCantCiudadesSeleccionadas.setText(m);
	}
	public void modificarCiudadesSeleccionadas(ArrayList<String> s){
		ciudadesSeleccionadas= s;
	}
	public void modificarRelaciones(ArrayList<String> r){
		relaciones = r;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String c= arg0.getActionCommand();
		if(BTON_SELECCIONAR_CIUDADES.equals(c)){
			PanelSeleccionadorCiudades p= new PanelSeleccionadorCiudades(principal, this);
			p.setVisible(true);
			
		}else if(BTON_AGREGAR_ESCALAS.equals(c)){
			//PanelRelacionesCiudades pa= new PanelRelacionesCiudades();
			//pa.setVisible(true);
			
			nombreAerolinea = JOptionPane.showInputDialog(null, "Ingrese el nombre de la aerolinea: ");
			
			try {
				velocidadVuelos = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la velocidad  de vuelo"));
				VentanaAgregarRutas vent = new VentanaAgregarRutas(); 
				vent.setVisible(true);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Por favor ingrese un valor correcto");
			}
			
			
			
			
		}else if(BTON_FINALIZAR.equals(c)){
			
		}
	}

	public String getNombreAerolinea() {
		return nombreAerolinea;
	}

	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}

	public double getVelocidadVuelos() {
		return velocidadVuelos;
	}

	public void setVelocidadVuelos(double velocidadVuelos) {
		this.velocidadVuelos = velocidadVuelos;
	}
	
}
