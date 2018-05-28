package mundo;

import java.io.Serializable;

public class Ciudad implements Serializable{

	private String  nombreCiudad;
	private int posX;
	private int posY;
	
	public Ciudad (String nom, int x, int y) {
		nombreCiudad = nom;
		posX = x;
		posY = y;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
