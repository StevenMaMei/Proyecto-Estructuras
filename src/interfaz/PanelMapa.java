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
	private JLabel nombreCompa�ia;
	private JButton butSiguiente;
	public static final String SIGUIENTE = "Siguiente";
	
	
	private VentanaAreolinea vr;
	
	public PanelMapa(VentanaAreolinea vr) {
		
		this.vr = vr;
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Mapa");
		titulo.setTitleColor(Color.BLACK);
		setBorder(titulo);
		
		setLayout( new BorderLayout());
		
		nombreCompa�ia = new JLabel("Avianca");
		nombreCompa�ia.setFont(new java.awt.Font("Antique Olive Co", 0,30));
		
		butSiguiente = new JButton("Siguiente Compa�ia");
		butSiguiente.setActionCommand(SIGUIENTE);
		butSiguiente.addActionListener(this);
		butSiguiente.setFont(new java.awt.Font("Antique Olive Co", 0,30));
		
		mapa = new JLabel();
		ImageIcon icono = new ImageIcon("./docs/mapa2.png");
		mapa.setIcon(icono);
		
		add(nombreCompa�ia, BorderLayout.NORTH);
		add(mapa, BorderLayout.CENTER);
		add(butSiguiente, BorderLayout.SOUTH);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comand = e.getActionCommand();
		if(comand.equals(SIGUIENTE)) {
			
		}
		
	}
	
	
}
