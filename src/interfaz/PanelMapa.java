package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelMapa extends JPanel implements ActionListener{

	
	private JLabel mapa;
	private JLabel nombreCompaņia;
	private JButton butSiguiente;
	public static final String SIGUIENTE = "Siguiente";
	
	
	private VentanaInicio relacionInicio;
	
	public PanelMapa(VentanaInicio ventInicio) {
		
		relacionInicio = ventInicio;
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Mapa");
		titulo.setTitleColor(Color.BLACK);
		setBorder(titulo);
		
		setLayout( new BorderLayout());
		
		nombreCompaņia = new JLabel("Avianca");
		nombreCompaņia.setFont(new java.awt.Font("Antique Olive Co", 0,30));
		
		butSiguiente = new JButton("Siguiente Compaņia");
		butSiguiente.setActionCommand(SIGUIENTE);
		butSiguiente.addActionListener(this);
		butSiguiente.setFont(new java.awt.Font("Antique Olive Co", 0,30));
		
		mapa = new JLabel();
		ImageIcon icono = new ImageIcon("./docs/mapa2.png");
		mapa.setIcon(icono);
		
		add(nombreCompaņia, BorderLayout.NORTH);
		add(mapa, BorderLayout.CENTER);
		add(butSiguiente, BorderLayout.SOUTH);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comand = e.getActionCommand();
		if(comand.equals(SIGUIENTE)) {
			//vr.siguiente 
		}
		
	}
	
	
}
