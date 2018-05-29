package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class PanelTablaLista extends JPanel {

	
	private JTable tabla; 
	
	public PanelTablaLista(String [][] tabla) {
	
		setLayout(new BorderLayout());
		
		
		String [] encabezado = {"Nombre Compañia","Ciudad Actual","Ciudad Destino","Precio","Ruta"};
		
		this.tabla = new JTable(tabla, encabezado);
	
		add(this.tabla, BorderLayout.CENTER);
		
		
	}
	
	
	
}
