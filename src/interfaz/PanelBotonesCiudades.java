package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonesCiudades extends JPanel implements ActionListener {


	private PanelCiudades relacionPanelCiudades;
	
	private PanelAerolinea relacionPanelAerolinea;
	
	public static final String AGREGAR = "Agregar";
	public static final String ELIMINAR = "Eliminar";
	public static final String VOLVER = "Volver";
	
	private JButton butAgregarVueloDirecto;
	private JButton butEliminarVueloDirecto;
	private JButton butVolver;

	//relacion 
	private VentanaAgregarRutas vr;
	
	public PanelBotonesCiudades(VentanaAgregarRutas vr) {
		this.vr = vr;
		
		
		setLayout(new GridLayout(2, 2));
		
		
		butAgregarVueloDirecto = new JButton("Agregar vuelo directo");
		butEliminarVueloDirecto = new JButton("Eliminar vuelo directo");
		butVolver = new JButton("Volver");
		
		butAgregarVueloDirecto.setFont(new java.awt.Font("Antique Olive Co",0,30));
		butEliminarVueloDirecto.setFont(new java.awt.Font("Antique Olive Co",0,30));
		butVolver.setFont(new java.awt.Font("Antique Olive Co",0,30));
		
		butAgregarVueloDirecto.setActionCommand(AGREGAR);
		butEliminarVueloDirecto.setActionCommand(ELIMINAR);
		butVolver.setActionCommand(VOLVER);
		
		butAgregarVueloDirecto.addActionListener(this);
		butEliminarVueloDirecto.addActionListener(this);
		butVolver.addActionListener(this);
		
		add(butAgregarVueloDirecto);
		add(butEliminarVueloDirecto);
		add(butVolver);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	String comando = e.getActionCommand();
		
		if (comando.equals(AGREGAR)) {
			int ciudad1 = relacionPanelCiudades.getCiudadActual();
			int ciudad2 = relacionPanelCiudades.getCiudadDestino();
			int precio = Integer.parseInt(relacionPanelCiudades.getPrecio());
			double velocidad = relacionPanelAerolinea.getVelocidadVuelos();
			vr.agregarVueloDirecto(ciudad1, ciudad2, precio, velocidad);
			
		}else if (comando.equals(ELIMINAR)) {
			
			int ciudad1=relacionPanelCiudades.getCiudadActual();
			int ciudad2 = relacionPanelCiudades.getCiudadDestino();
			vr.eliminarVueloDirecto(ciudad1, ciudad2);
			
		}else if(comando.equals(VOLVER)) {
			vr.cerrar();
		}
		
		
		
	}

	
	
	
	
}