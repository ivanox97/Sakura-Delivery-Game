package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Sakura {

	private double x;
	private double y;
	private static final double VELOCIDAD = 2;
	private Image IMAGE = Herramientas.cargarImagen("sakura.png");
	private boolean vivo = true;
	private int alto;
	private int ancho;

	public Sakura(double x, double y) {
		this.x = x;
		this.y = y;
		this.alto = IMAGE.getHeight(null);
		this.ancho = IMAGE.getWidth(null);
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(IMAGE, x, y, 0);
	}

	public boolean meMatoNinja(Ninja c) {
		boolean tocaEnHorizontal = (this.x + this.getAncho() / 2 >= c.getX() - c.getAncho() / 2)
				&& (this.x - this.getAncho() / 2 <= c.getX() + c.getAncho() / 2);
		boolean tocaEnVertical = (this.y + this.getAlto() / 2 >= c.getY() - c.getAlto() / 2)
				&& (this.y - this.getAlto() / 2 <= c.getY() + c.getAlto() / 2);
		boolean estaVivoElNinja = c.isRevivir() == true;
		return tocaEnHorizontal && tocaEnVertical && estaVivoElNinja;
	}

	public boolean estoyEnHorizonal() {
		return this.y >= 95 && this.y <= 105 || this.y >= 245 && this.y <= 255 || this.y >= 395 && this.y <= 405
				|| this.y >= 540 && this.y <= 550;
	}

	public boolean estoyEnVertical() {
		return this.x >= 95 && this.x <= 105 || this.x >= 295 && this.x <= 305 || this.x >= 495 && this.x <= 505
				|| this.x >= 695 && this.x <= 705;
	}

	public boolean limiteX() {
		return this.x < 780 && this.x > 20;
	}

	public boolean limiteY() {
		return this.y < 580 && this.y > 20;
	}

	public void meMuevo(Entorno e, Rasengan r) {

		if (e.estaPresionada(e.TECLA_DERECHA) && estoyEnHorizonal() && limiteX()) {
			moverDerecha();
			r.setAngulo(0);
			// reaparezco en la otra punta del mapa
			if (this.x >= 780) {
				this.setX(21);
			}
		} else if (e.estaPresionada(e.TECLA_IZQUIERDA) && estoyEnHorizonal() && limiteX()) {
			moverIzquierda();
			r.setAngulo(Math.PI);
			// reaparezco en la otra punta del mapa
			if (this.x <= 20) {
				this.setX(779);
			}
		} else if (e.estaPresionada(e.TECLA_ARRIBA) && estoyEnVertical() && limiteY()) {
			moverArriba();
			r.setAngulo(-Math.PI / 2);
			// reaparezco en la otra punta del mapa
			if (this.y <= 20) {
				this.setY(579);
			}
		} else if (e.estaPresionada(e.TECLA_ABAJO) && estoyEnVertical() && limiteY()) {
			moverAbajo();
			r.setAngulo(Math.PI / 2);
			// reaparezco en la otra punta del mapa
			if (this.y >= 580) {
				this.setY(21);
			}
		}
		r.dibujarse(e, this);
	}

	// verifico si tengo flor

	public boolean tengoFlor(Image imagen) {
		if (this.getImage() == imagen) {
			return true;
		} else {
			return false;
		}
	}

	// simplifique el codigo, corregi la direccion.
	public void moverDerecha() {
		x += VELOCIDAD;
	}

	public void moverIzquierda() {
		x -= VELOCIDAD;
	}

	public void moverArriba() {
		y -= VELOCIDAD;
	}

	public void moverAbajo() {
		y += VELOCIDAD;
	}

	public double getX() {
		return x;
	}

	public double setX(double x) {
		return this.x = x;
	}

	public double getY() {
		return y;
	}

	public double setY(double y) {
		return this.y = y;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public Image getImage() {
		return IMAGE;
	}

	public void setIMAGE(Image iMAGE) {
		IMAGE = iMAGE;
	}

}