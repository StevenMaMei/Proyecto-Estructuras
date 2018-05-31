package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelUsuario extends JPanel implements ActionListener{

	private JComboBox<String> ciudadesActuales;
	private JComboBox<String> ciudadesDestino;
	private JButton btonPorPrecio;
	private JButton btonPorDistancia;
	private JButton btonPorTiempo;
	private JLabel lblCiudadActual;
	private JLabel lblCiudadDestino;
	
	private JLabel espacio1;
	private JLabel espacio2;
	
	
	private static final String BTON_PRECIO="lprecio";
	private static final String BTON_DISTANCIA="ldistancia";
	private static final String BTON_TIEMPO="ltiempo";
	
	private VentanaInicio relacionInicio;
	
	
	public PanelUsuario(VentanaInicio p){
	
		relacionInicio = p;
		
		TitledBorder titulo = BorderFactory.createTitledBorder("Usuario");
		titulo.setTitleColor(Color.BLACK);
		setBorder(titulo);
		
		ciudadesActuales = new JComboBox<>();
		ciudadesDestino = new JComboBox<>();
		
		ciudadesDestino.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
		ciudadesActuales.setFont(new java.awt.Font("Antique Olive Co", 0, 30));
	
		
		ciudadesActuales.addItem("Cali (Colombia)");
		ciudadesActuales.addItem("Buenos Aires (Argentina)");
		ciudadesActuales.addItem("Brasilia (Brasil)");
		ciudadesActuales.addItem("Santiago de Chile (Chile)");
		ciudadesActuales.addItem("Quito (Ecuador)");
		ciudadesActuales.addItem("Asunción (Paraguay)");
		ciudadesActuales.addItem("Lima (Perú)");
		ciudadesActuales.addItem("Carácas (Venezuela)");
		ciudadesActuales.addItem("Ciudad de Mexico (Mexico)");
		ciudadesActuales.addItem("Washington D.C. (Estados Unidos)");
		ciudadesActuales.addItem("Panamá (Panamá)");
		ciudadesActuales.addItem("Ottawa (Canada)");
		ciudadesActuales.addItem("Sucre (Bolivia)");
		
		
		ciudadesDestino.addItem("Cali (Colombia)");
		ciudadesDestino.addItem("Buenos Aires (Argentina)");
		ciudadesDestino.addItem("Brasilia (Brasil)");
		ciudadesDestino.addItem("Santiago de Chile (Chile)");
		ciudadesDestino.addItem("Quito (Ecuador)");
		ciudadesDestino.addItem("Asunción (Paraguay)");
		ciudadesDestino.addItem("Lima (Perú)");
		ciudadesDestino.addItem("Carácas (Venezuela)");
		ciudadesDestino.addItem("Ciudad de Mexico (Mexico)");
		ciudadesDestino.addItem("Washington D.C. (Estados Unidos)");
		ciudadesDestino.addItem("Panamá (Panamá)");
		ciudadesDestino.addItem("Ottawa (Canada)");
		ciudadesDestino.addItem("Sucre (Bolivia)");
		
		
		lblCiudadActual= new JLabel("Ciudad de partida");
		lblCiudadDestino= new JLabel("Ciudad de destino");

		lblCiudadActual.setFont(new java.awt.Font("Antique Olive Co",0,20));
		lblCiudadDestino.setFont(new java.awt.Font("Antique Olive Co", 0,20));
		
		
		btonPorPrecio = new JButton("Listar escalas por precio");
		btonPorPrecio.addActionListener(this);
		btonPorPrecio.setActionCommand(BTON_PRECIO);
		
		btonPorDistancia = new JButton("Listar escalas por distancia");
		btonPorDistancia.addActionListener(this);
		btonPorDistancia.setActionCommand(BTON_DISTANCIA);
		
		btonPorTiempo= new JButton("Listar escalas por tiempo");
		btonPorTiempo.addActionListener(this);
		btonPorTiempo.setActionCommand(BTON_TIEMPO);
		 espacio1 = new JLabel("");
		 espacio2 = new JLabel("");
		
		 btonPorDistancia.setFont(new java.awt.Font("Antique Olive Co", 0, 20));
		 btonPorPrecio.setFont(new java.awt.Font("Antique Olive Co", 0, 20));
		 btonPorTiempo.setFont(new java.awt.Font("Antique Olive Co", 0, 20));
		 
		setLayout(new GridLayout(2, 1));
		
		JPanel aux1= new JPanel();
		aux1.setLayout(new GridLayout(5,1));
		aux1.add(lblCiudadActual);
		aux1.add(ciudadesActuales);
		aux1.add(lblCiudadDestino);
		aux1.add(ciudadesDestino);
		
		JPanel aux2= new JPanel();
		aux2.setLayout(new GridLayout(5, 1));
		aux2.add(btonPorPrecio);
		aux2.add(espacio1);
		aux2.add(btonPorDistancia);
		aux2.add(espacio2);
		aux2.add(btonPorTiempo);
		
		add(aux1);
		add(aux2);
	}
	
	public void actualizarCiudadesPartida(){
		
		//String[] ciudades= principal.darCiudades();
		//ciudadesActuales=new JComboBox<>(ciudades);
		//ciudadesDestino= new JComboBox<>(ciudades);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando= e.getActionCommand();
		String partida= ciudadesActuales.getSelectedItem().toString();
		String destino= ciudadesDestino.getSelectedItem().toString();
		
		if(comando.equals(BTON_DISTANCIA)){
			
			VentanaLista ventDis = new VentanaLista("Listado por Distancia minima");
			ventDis.setVisible(true);
			

		}else if(comando.equals(BTON_PRECIO)){
			
			VentanaLista ventDis = new VentanaLista("Listado por Precio minimo");
			ventDis.setVisible(true);
			
			
		}else if(comando.equals(BTON_TIEMPO)){
			VentanaLista ventDis = new VentanaLista("Listado por Tiempo minimo");
			ventDis.setVisible(true);
			
			
		}
		
	}
	
	
}
