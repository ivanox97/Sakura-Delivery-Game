package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private static final Image COMUN = Herramientas.cargarImagen("casacomun.png");

	public Casa(double x, double y) {
		this.x = x;
		this.y = y;
		this.alto = COMUN.getHeight(null);
		this.ancho = COMUN.getWidth(null);
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(COMUN, x, y, 0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
