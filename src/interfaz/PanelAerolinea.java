package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAerolinea extends JPanel implements ActionListener {

	private VentanaAreolinea principal;
	private JTextField textCantCiudadesSeleccionadas;
	private JButton btonSeleccionarCiudades;
	private JButton btonAgregarEscalas;
	private JButton btonCrearFinalizar;
	private ArrayList<String> ciudadesSeleccionadas;
	private ArrayList<String> relaciones;
	
	public final static String BTON_SELECCIONAR_CIUDADES= "seleCiudades";
	public final static String BTON_AGREGAR_ESCALAS="agrEscalas";
	public final static String BTON_FINALIZAR="fnl";
	
	public PanelAerolinea(VentanaAreolinea p){
		principal= p;
		setLayout(new BorderLayout());
		
		textCantCiudadesSeleccionadas= new JTextField();
		textCantCiudadesSeleccionadas.setEditable(false);
		
		ciudadesSeleccionadas= new ArrayList<>();
		relaciones = new ArrayList<>();
		
		btonSeleccionarCiudades= new JButton("Seleccionar ciudades disponibles");
		btonSeleccionarCiudades.addActionListener(this);
		btonSeleccionarCiudades.setActionCommand(BTON_SELECCIONAR_CIUDADES);
		
		
		
		
		
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
		// TODO Auto-generated method stub
		
	}
	
}
