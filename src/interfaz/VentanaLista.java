package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaLista extends JFrame implements ActionListener {

	private JLabel titulo; 
	private PanelTablaLista tabla;
	
	public static final String VOLVER = "Volver";
	private JButton butVolver;
	
	public VentanaLista(String tipoLista, String [][] matriz) {


		setTitle("Galchiner S.A");
		setSize(500, 300);
		//setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		setLayout(new BorderLayout());
		
		butVolver = new JButton("Volver");
		butVolver.setFont(new java.awt.Font("Antique Olive Co", 0,20));
		
		butVolver.setActionCommand(VOLVER);
		butVolver.addActionListener(this );
		
		
		titulo = new JLabel(tipoLista);
		titulo.setFont(new java.awt.Font("Antique Olive Co", 0,20));
		
		tabla = new PanelTablaLista(matriz);
		
		JPanel aux= new JPanel();
		aux.setLayout(new GridLayout(1, 3));
		aux.add(butVolver);
		
		add(titulo, BorderLayout.NORTH);
		add(tabla, BorderLayout.CENTER);
		add(aux, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals(VOLVER)) {
			dispose();
		}
		
	}
	
	
}
