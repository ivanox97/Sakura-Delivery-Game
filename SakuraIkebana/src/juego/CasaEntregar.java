package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class CasaEntregar {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	private static final Image ENTREGA = Herramientas.cargarImagen("casaflores.png");

	public CasaEntregar(double x, double y) {
		this.x = x;
		this.y = y;
		this.alto = ENTREGA.getHeight(null);
		this.ancho = ENTREGA.getWidth(null);
	}

	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(ENTREGA, x, y, 0);
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

	public boolean esEntregado(Sakura s) {
		boolean tocaEnHorizontal = ((this.getX() + this.getAncho() / 2) >= s.getX() - s.getAncho() / 2)
				&& ((this.getX() - this.getAncho() / 2) <= s.getX() + s.getAncho() / 2);
		boolean tocaEnVertical = ((this.getY() + this.getAlto() / 2) >= s.getY() - s.getAlto() / 2)
				&& (this.getY() - this.getAlto() <= s.getY() + s.getAlto() / 2);
		return tocaEnHorizontal && tocaEnVertical;
	}

}