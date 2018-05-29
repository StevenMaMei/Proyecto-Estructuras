package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PanelMapaBoton extends JPanel implements ActionListener{

	public final static String SIGUIENTE = "sig";
	
	
	private PanelMapa mapa;
	private JButton siguiente;
	private VentanaAreolinea ventana;
	private JLabel agencia;
	
	public PanelMapaBoton(VentanaAreolinea v) {
		ventana = v;
		setLayout (new BorderLayout());
		mapa = new PanelMapa();
		
		agencia = new JLabel("Avianca");
		
		siguiente = new JButton("Siguiente aerolinea");
		siguiente.addActionListener(this);
		siguiente.setActionCommand(SIGUIENTE);
		
		add (mapa, BorderLayout.CENTER);
		add (siguiente, BorderLayout.SOUTH);
		add (agencia, BorderLayout.NORTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
