package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelUsuario extends JPanel implements ActionListener{

	private JComboBox<String> ciudadesActuales;
	private JComboBox<String> ciudadesDestino;
	private JButton btonPorPrecio;
	private JButton btonPorDistancia;
	private JButton btonPorTiempo;
	private JLabel lblCiudadActual;
	private JLabel lblCiudadDestino;
	private VentanaAreolinea principal;
	
	private static final String BTON_PRECIO="lprecio";
	private static final String BTON_DISTANCIA="ldistancia";
	private static final String BTON_TIEMPO="ltiempo";
	
	
	public PanelUsuario(VentanaAreolinea p){
		principal = p;
		
		ciudadesActuales = new JComboBox<>();
		ciudadesDestino = new JComboBox<>();
		
		lblCiudadActual= new JLabel("Ciudad de partida");
		lblCiudadDestino= new JLabel("Ciudad de destino");
		
		btonPorPrecio = new JButton("Listar escalas por precio");
		btonPorPrecio.addActionListener(this);
		btonPorPrecio.setActionCommand(BTON_PRECIO);
		
		btonPorDistancia = new JButton("Listar escalas por distancia");
		btonPorDistancia.addActionListener(this);
		btonPorDistancia.setActionCommand(BTON_DISTANCIA);
		
		btonPorTiempo= new JButton("Listar escalas por tiempo");
		btonPorTiempo.addActionListener(this);
		btonPorTiempo.setActionCommand(BTON_TIEMPO);
		
		setLayout(new BorderLayout());
		
		JPanel aux1= new JPanel();
		aux1.setLayout(new GridLayout(4,1));
		aux1.add(lblCiudadActual);
		aux1.add(ciudadesActuales);
		aux1.add(lblCiudadDestino);
		aux1.add(ciudadesDestino);
		
		JPanel aux2= new JPanel();
		aux2.setLayout(new GridLayout(1, 3));
		aux2.add(btonPorPrecio);
		aux2.add(btonPorDistancia);
		aux2.add(btonPorTiempo);
		
		add(aux1,BorderLayout.CENTER);
		add(aux2, BorderLayout.SOUTH);
	}
	
	public void actualizarCiudadesPartida(){
		String[] ciudades= principal.darCiudades();
		ciudadesActuales=new JComboBox<>(ciudades);
		ciudadesDestino= new JComboBox<>(ciudades);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando= e.getActionCommand();
		String partida= ciudadesActuales.getSelectedItem().toString();
		String destino= ciudadesDestino.getSelectedItem().toString();
		if(comando.equals(BTON_DISTANCIA)){
			
		}else if(comando.equals(BTON_PRECIO)){
			
		}else if(comando.equals(BTON_TIEMPO)){
			
		}
		
	}
	
	
}
