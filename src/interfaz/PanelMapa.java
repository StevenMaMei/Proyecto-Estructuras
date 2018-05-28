package interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMapa extends JPanel {

	
	private JLabel mapa;
	
	
	public PanelMapa() {
		
		mapa = new JLabel();
		ImageIcon icono = new ImageIcon("./docs/mapa.png");
		mapa.setIcon(icono);
		
		add(mapa);
		
	}
	
	
}
