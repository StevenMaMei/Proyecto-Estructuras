package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelSeleccionadorCiudades extends JFrame implements ActionListener{
	
	private VentanaAreolinea principal;
	private PanelAerolinea panel;
	private JCheckBox[] ciudades;
	private JButton aceptar;
	public final static String BTON_ACEPTAR= "ACEP";
	public PanelSeleccionadorCiudades(VentanaAreolinea p, PanelAerolinea a){
		principal = p;
		panel= a;
		String[] disponibles = principal.darCiudades();

		ciudades= new JCheckBox[disponibles.length];
		JPanel aux= new JPanel();
		JScrollPane scroll= new JScrollPane(aux);
		aux.setLayout(new GridLayout(disponibles.length/2, 2));
		for(int i=0;i<disponibles.length;i++){
			ciudades[i]=new JCheckBox(disponibles[i]);
			aux.add(ciudades[i]);
		}
		
		aceptar= new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(BTON_ACEPTAR);
		
		setLayout(new BorderLayout());
		add(scroll,BorderLayout.CENTER);
		add(aceptar,BorderLayout.SOUTH);
		
		pack();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String e= arg0.getActionCommand();
		if(e.equals(BTON_ACEPTAR)){
			ArrayList<String> c= new ArrayList<>();
			for(int i=0;i<ciudades.length;i++){
				if(ciudades[i].isSelected()){
					c.add(ciudades[i].toString());
				}
			}
			panel.modificarCiudadesSeleccionadas(c);
			panel.modificarTextCantCiudades(c.size()+"");
		}
	}
}
