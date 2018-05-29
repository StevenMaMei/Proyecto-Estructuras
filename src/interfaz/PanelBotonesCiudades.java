package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonesCiudades extends JPanel implements ActionListener {


	public static final String AGREGAR = "Agregar";
	public static final String ELIMINAR = "Eliminar";
	
	private JButton butAgregarVueloDirecto;
	private JButton butEliminarVueloDirecto;
	//relacion 
	private VentanaAgregarRutas vr;
	
	public PanelBotonesCiudades(VentanaAgregarRutas vr) {
		this.vr = vr;
		
		setLayout(new GridLayout(1, 2));
		
		butAgregarVueloDirecto = new JButton("Agregar vuelo directo");
		butEliminarVueloDirecto = new JButton("Eliminar vuelo directo");
		
		butAgregarVueloDirecto.setFont(new java.awt.Font("Antique Olive Co",0,30));
		butEliminarVueloDirecto.setFont(new java.awt.Font("Antique Olive Co",0,30));
		
		
		butAgregarVueloDirecto.setActionCommand(AGREGAR);
		butEliminarVueloDirecto.setActionCommand(ELIMINAR);
		
		butAgregarVueloDirecto.addActionListener(this);
		butEliminarVueloDirecto.addActionListener(this);
		
		add(butAgregarVueloDirecto);
		add(butEliminarVueloDirecto);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	String comando = e.getActionCommand();
		
		if (comando.equals(AGREGAR)) {
			
		}else if (comando.equals(ELIMINAR)) {
			
		}
		
		
		
	}

	
	
	
	
}
