package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundo.Principal;

public class VentanaInicio extends JFrame implements ActionListener{

	private JLabel lbBienvenido;
	private JButton butLista;
	private JButton butMatriz;
	
	public static final String LISTA = "Lista";
	public static final String MATRIZ = "Matriz";
	
	//Relacion con el mundo
	private Principal conexionMundo;
	
	
	public VentanaInicio() {
	
		
		 
		setTitle("Galchiner S.A");
		setSize(800, 400);
		setLocationRelativeTo(null);
		setResizable(false);

		
		lbBienvenido = new JLabel();
		ImageIcon icono = new ImageIcon("./docs/titulo.png");
		lbBienvenido.setIcon(icono);
		
		butLista = new JButton("Grafo por Lista");
		butMatriz = new JButton("Grafo Matriz");
		
		butLista.setFont(new java.awt.Font("Antique Olive Co", 0,30));
		butMatriz.setFont(new java.awt.Font("Antique Olive Co", 0,30));
		
		butLista.addActionListener(this);
		butMatriz.addActionListener(this);
		
		butLista.setActionCommand(LISTA);
		butMatriz.setActionCommand(MATRIZ);
		
		setLayout(new BorderLayout());
		
		add(lbBienvenido, BorderLayout.NORTH);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2, 4));
		
		aux.add(butLista);
		aux.add(butMatriz);
		
		add(aux, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(LISTA)) {
			//Mandar a la interfaz Principal
			VentanaAreolinea ventPrin = new VentanaAreolinea();
			ventPrin.setVisible(true);
			
			try {
				conexionMundo = new Principal(Principal.LISTA);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se pudo crear el gafro correctamente");
				e1.printStackTrace();
			}
			
			dispose();	
			
			
		}else if(comando.equals(MATRIZ)) {
			//Mandar a la interfaz Principal
			VentanaAreolinea ventPrin = new VentanaAreolinea();
			ventPrin.setVisible(true);
			
			try {
				conexionMundo = new Principal(Principal.MATRIZ);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "No se pudo crear el grafo correctamente");
				e1.printStackTrace();
			}
			
			
			dispose();
			
		}
		
		
		
	}
	
	
	public static void main(String[] args){
		VentanaInicio vent = new VentanaInicio();
		vent.setVisible(true);
	}
	
	
}
