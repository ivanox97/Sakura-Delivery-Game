package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {

	private double ancho;
	private double alto;

	private double x;
	private double y;
	private double angulo;
	private static final int VELOCIDAD = 5;
	private static final Image RASENGAN = Herramientas.cargarImagen("rasengan.png");
	private boolean disparar;
	private boolean dibujar;

	public Rasengan(Sakura p) {
		// TODO Auto-generated constructor stub
		this.x = p.getX();
		this.y = p.getY();
		this.alto = RASENGAN.getHeight(null);
		this.ancho = RASENGAN.getWidth(null);
		angulo = 0;
		setDisparar(false);
	}

	// CONTROL DE MANDO DEL DISPARO
	void dispararVeces(Entorno e) {
		if (e.sePresiono(e.TECLA_ESPACIO)) {
			if (isDisparar() == false) {
				setDisparar(true);
			}
			if (isDibujar() == false) {
				setDibujar(true);
			}     
		}
	}

	public void disparaSakura(Sakura p) {
		if (this.isDisparar() && this.angulo == -Math.PI / 2) {// vertical hacia arriba
			if (this.isDibujar()) {
				y -= VELOCIDAD;
			}
			if (this.y <= p.getY() - 100) {// Condicion para que que recorra solamente 20 pixeles
				this.setDisparar(false);
				this.setDibujar(false);
				this.y = p.getY();// vuelve a la posicion de sakura
				this.x = p.getX();

			}
		} else if (this.isDisparar() && this.angulo == Math.PI / 2) {// vertical hacia abajo
			if (this.isDibujar()) {
				y += VELOCIDAD;
			}
			if (this.y >= p.getY() + 100) {// Condicion para que que recorra solamente 20 pixeles
				this.setDisparar(false);
				this.setDibujar(false);
				this.y = p.getY();// vuelve la posicion de sakura
				this.x = p.getX();

			}
		} else if (this.isDisparar() && this.angulo == Math.PI) {// horizontal hacia izquierda
			if (this.isDibujar()) {
				x -= VELOCIDAD;
			}
			if (this.x <= p.getX() - 100) {// Condicion para que que recorra solamente 20 pixeles
				this.setDisparar(false);
				this.setDibujar(false);
				this.y = p.getY();// vuelve la posicion de sakura
				this.x = p.getX();

			}
		}

		else if (this.isDisparar() && this.angulo == 0) {// Horizontal hacia derecha
			if (this.isDibujar()) {
				x += VELOCIDAD;
			}
			if (this.x >= p.getX() + 100) {// Condicion para que que recorra solamente 20 pixeles
				this.setDisparar(false);
				this.setDibujar(false);
				this.y = p.getY();// vuelve la posicion de sakura
				this.x = p.getX();
			}

		}

	}

	public boolean esDisparado(Ninja r) {
		boolean tocaEnHorizontal = ((r.getX() + r.getAncho() / 2) >= (x - this.getAlto() / 2))
				&& ((r.getX() - r.getAncho() / 2) <= (getX() + this.getAlto() / 2));
		boolean tocaEnVertical = ((r.getY() + r.getAlto() / 2) >= (y - this.getAlto() / 2))
				&& ((r.getY() - r.getAlto() / 2) <= (y + this.getAlto() / 2));
		return tocaEnHorizontal && tocaEnVertical;
	}

	// DUBUJAMOS RASENGAN
	public void dibujarse(Entorno e, Sakura p) {
		if (this.dibujar == true) {
			e.dibujarImagen(RASENGAN, x, y, angulo);
		}
	}

	// GETTERS Y SETTERS
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

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

	public boolean isDisparar() {
		return disparar;
	}

	public void setDisparar(boolean disparar) {
		this.disparar = disparar;
	}

	public boolean isDibujar() {
		return dibujar;
	}

	public void setDibujar(boolean dibujar) {
		this.dibujar = dibujar;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
}