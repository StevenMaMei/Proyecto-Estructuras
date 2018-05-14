package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelBanner extends JPanel {

	private JLabel espacio;
	
	
	public PanelBanner() {
	
		TitledBorder br = BorderFactory.createTitledBorder("");
		br.setTitleColor(Color.BLACK);
		setBorder(br);
		
		
		espacio = new JLabel("         GALCHINER S.A.");
		espacio.setFont(new java.awt.Font("Bauhaus 93",0,70));
		
		
		
		setLayout(new BorderLayout());
		add(espacio, BorderLayout.CENTER);
	}
	
	
	
	
}
