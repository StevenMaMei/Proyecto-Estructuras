package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelTablaLista extends JPanel {

	
	private JTable tabla; 
	
	public PanelTablaLista(String [][] tabla, String tipo) {
	
		setLayout(new BorderLayout());
		
		
		String [] encabezado = new String [tabla[0].length];
		encabezado[0] = "Nombre compañía";
		encabezado [1] = tipo;
		for (int i = 2; i < tabla[0].length; i++) {
			encabezado[i] = "Ciudad "+ (i-1);
		}
		
		this.tabla = new JTable(tabla, encabezado);
	
		add(this.tabla, BorderLayout.CENTER);
		
		
	}
	
	
	
}
