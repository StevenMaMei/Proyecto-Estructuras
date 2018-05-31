package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonesCiudades extends JPanel implements ActionListener {



	public static final String AGREGAR = "Agregar";
	public static final String ELIMINAR = "Eliminar";
	public static final String VOLVER = "Volver";
	public static final String CHECK = "Check";
	
	
	private JButton butAgregarVueloDirecto;
	private JButton butEliminarVueloDirecto;
	private JButton butCheck;
	private JButton butVolver;

	//relacion 
	private VentanaInicio vr;
	
	
	
	public PanelBotonesCiudades(VentanaInicio vr) {
		this.vr = vr;
		
		
		setLayout(new GridLayout(2, 2));
		
		butCheck = new JButton("Check");
		butAgregarVueloDirecto = new JButton("Agregar vuelo directo");
		butEliminarVueloDirecto = new JButton("Eliminar vuelo directo");
		butVolver = new JButton("Volver");
		
		butCheck.setFont(new java.awt.Font("Antique Olive Co",0,30));
		butAgregarVueloDirecto.setFont(new java.awt.Font("Antique Olive Co",0,30));
		butEliminarVueloDirecto.setFont(new java.awt.Font("Antique Olive Co",0,30));
		butVolver.setFont(new java.awt.Font("Antique Olive Co",0,30));
		
		butCheck.setActionCommand(CHECK);
		butAgregarVueloDirecto.setActionCommand(AGREGAR);
		butEliminarVueloDirecto.setActionCommand(ELIMINAR);
		butVolver.setActionCommand(VOLVER);
		
		butCheck.addActionListener(this);
		butAgregarVueloDirecto.addActionListener(this);
		butEliminarVueloDirecto.addActionListener(this);
		butVolver.addActionListener(this);
		
		add(butAgregarVueloDirecto);
		add(butEliminarVueloDirecto);
		add(butVolver);
		add(butCheck);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	String comando = e.getActionCommand();
		
		if (comando.equals(AGREGAR)) {
		
			vr.agregarVuelo();
			
			
		}else if (comando.equals(ELIMINAR)) {
			
			vr.eliminarVuelo();
			
		}else if(comando.equals(VOLVER)) {
			 vr.cerrar();
		}else if(comando.equals(CHECK)) {
			vr.check();
		}
		
		
		
	}

	
	
	
	
}
