package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelRelacionesCiudades extends JFrame implements ActionListener {

	private JPanel panelAux;
	private JButton boton;
	public PanelRelacionesCiudades(){
		panelAux= new JPanel();
		panelAux.setLayout(new GridLayout(0, 3));
		setLayout(new BorderLayout());
		add(panelAux,BorderLayout.CENTER);
		
		boton = new JButton("Agregar");
		boton.setActionCommand("h");
		boton.addActionListener(this);
		add(boton,BorderLayout.SOUTH);
		pack();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String c= e.getActionCommand();
		if(c.equals("h")){
			panelAux.add(new JButton ("1"));
			panelAux.updateUI();
			pack();
		}
	}
}
