package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Ninja {
	private double x;
	private double y;
	private int alto;
	private int ancho;
	private double angulo;
	private double velocidad;
	private boolean revivir = true;
	private static final Image IMAGEN = Herramientas.cargarImagen("ninja.png");

	public Ninja(double pos_X, double pos_Y, double angulo) {
		// TODO Auto-generated constructor stub
		this.x = pos_X;
		this.y = pos_Y;
		this.alto = IMAGEN.getHeight(null);
		this.ancho = IMAGEN.getWidth(null);
		this.angulo = angulo;
		this.velocidad = 1.0;
	}

	void dibNinja(Entorno e) {
		e.dibujarImagen(IMAGEN, x, y, 0);
	}

	void movHorizontal() {
		this.x += this.velocidad * 0.7 * Math.cos(this.angulo);
		if (this.x < 20) {
			this.x = 20;
			this.angulo = Math.PI - this.angulo;
		}
		if (this.x > 780) {
			this.x = 780;
			this.angulo = Math.PI - this.angulo;
		}
	}

	void movVertical() {
		this.y += this.velocidad * 1.5 * Math.sin(this.angulo);
		if (this.y < 20) {
			this.y = 20;
			this.angulo = Math.PI + this.angulo;
		}
		if (this.y > 580) {
			this.y = 580;
			this.angulo = Math.PI + this.angulo;
		}
	}

	public void moverIzquierda() {
		x -= velocidad;
	}

	public void moverDerecha() {
		x += velocidad;
	}

	void acelerar() {
		velocidad += 0.1;
	}

	void desacelerar() {
		velocidad -= 0.1;
	}

	void cambiarDireccion() {
		angulo += Math.PI / 2;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public boolean isRevivir() {
		return revivir;
	}

	public void setRevivir(boolean revivir) {
		this.revivir = revivir;
	}

	
}
