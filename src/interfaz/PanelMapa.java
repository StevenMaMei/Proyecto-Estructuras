package interfaz;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelMapa extends JPanel {

	
	private JLabel mapa;
	
	
	public PanelMapa() {
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Mapa");
		titulo.setTitleColor(Color.BLACK);
		setBorder(titulo);
		
		mapa = new JLabel();
		ImageIcon icono = new ImageIcon("./docs/mapa2.png");
		mapa.setIcon(icono);
		
		add(mapa);
		
	}
	
	
}
