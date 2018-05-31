package interfaz;

import javax.swing.JFrame;


	

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
		
		
		private VentanaAgregarRutas relacionVentAgregarRutas;
		private VentanaAreolinea relacionVentAerolinea;
		
		public static final String LISTA = "Lista";
		public static final String MATRIZ = "Matriz";
		
		//Relacion con el mundo
		private Principal conexionMundo;
		
		
		public VentanaInicio() {
		
			relacionVentAerolinea = new VentanaAreolinea(this);
			relacionVentAgregarRutas = new VentanaAgregarRutas(this);
			 
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
				VentanaAreolinea ventPrin = new VentanaAreolinea(this);
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
				VentanaAreolinea ventPrin = new VentanaAreolinea(this);
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
		
		
		public void agregarVuelo() {
			
			int indice1 = relacionVentAgregarRutas.getPanelCiudades().getCiudadActual();
			int indice2 = relacionVentAgregarRutas.getPanelCiudades().getCiudadDestino();
			double precio = Double.parseDouble(relacionVentAgregarRutas.getPanelCiudades().getPrecio());
			double velocidad = relacionVentAerolinea.getPanelAereolinea().getVelocidadVuelos();
			
			try {
				conexionMundo.agregarVueloDirecto(indice1, indice2, precio, velocidad);
				JOptionPane.showMessageDialog(null, "Se agrego el vuelo correctamente");
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "No se pudo agregar el vuelo correctamente");
				e.printStackTrace();
			}
			
			
		}
		
		
		public void eliminarVuelo() {
			
			int indice1 = relacionVentAgregarRutas.getPanelCiudades().getCiudadActual();
			int indice2 = relacionVentAgregarRutas.getPanelCiudades().getCiudadDestino();
			
			try {
				conexionMundo.eliminarVueloDirecto(indice1, indice2);
				JOptionPane.showMessageDialog(null, "Se eliminó el vuelo correctamente");
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar el vuelo correctamente");
				
				
				e.printStackTrace();
			}
			
		}
		
		
		public void cerrar() {
			dispose();
		}
		
		public void check() {
			
			try {
				conexionMundo.agregarAerolinea(relacionVentAerolinea.getPanelAereolinea().getNombreAerolinea());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Todas las ciudades deben estar conexas para poder agregar la ruta");
//				e.printStackTrace();
			}
			
			
		}
		
		
		public void cambiarGrafo() {
			
			try {
				conexionMundo.cambiarRepresentacion();
				JOptionPane.showMessageDialog(null, "Se cambio la representación del grafo correctamente");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error en el cambio de grafo");
				e.printStackTrace();
			}
			
		}
		
		public void abrirSeleccionadorRutas() {
			relacionVentAgregarRutas.setVisible(true);
			try {
				conexionMundo.reiniciarGrafo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	

