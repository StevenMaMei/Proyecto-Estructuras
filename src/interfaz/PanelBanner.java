package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelBanner extends JPanel {

	private JLabel espacio;
	private JLabel banner;
	
	public PanelBanner() {
	
		//TitledBorder br = BorderFactory.createTitledBorder("");
		//br.setTitleColor(Color.BLACK);
		//setBorder(br);
		
		
		espacio = new JLabel("                          GALCHINER S.A.");
		espacio.setFont(new java.awt.Font("Antique Olive Co",0,70));
		
		banner = new JLabel();
		ImageIcon icono= new ImageIcon("./docs/titulo.png"); 
		banner.setIcon(icono);
		
		//add(banner);
		setLayout(new BorderLayout());
		add(espacio, BorderLayout.CENTER);
	}
	
	
	
	
}
