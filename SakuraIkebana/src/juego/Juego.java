package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

@SuppressWarnings("unused")
public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	// ...

	private Image fondoImg;
	private Image gameOver;
	private Image sakuraConFlor;
	private Image sakuraSinFlor;

	private CasaEntregar casaEntrega;
	private Sakura sakura;
	private Rasengan rasengan;
	private Casa[] casas = new Casa[28];
	private Floreria floreria;
	private Ninja[] ninjas;

	private int contTiempo = 0;
	private int puntaje = 0;

	// random para la casa a Entregar
	int random = (int) Math.floor(Math.random() * casas.length);

	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 5 - maximiliano-arevalo-tp-1", 800, 600);
		fondoImg = Herramientas.cargarImagen("fondo.png");
		gameOver = Herramientas.cargarImagen("gameover.png");
		sakuraConFlor = Herramientas.cargarImagen("sakuraConFlor.png");
		sakuraSinFlor = Herramientas.cargarImagen("sakura.png");
		// Inicializar lo que haga falta para el juego
		// ...

		// Ninjas:

		ninjas = new Ninja[6];
		// Ninjas horizontales
		ninjas[0] = new Ninja(100, 100, 0);// (X+200,Y+150,angulo,w,h,c)
		ninjas[1] = new Ninja(300, 250, 0);// (X,Y,angulo,w,h,c)
		ninjas[2] = new Ninja(500, 550, 0);// (X,Y,angulo,w,h,c)
		// Ninjas verticales
		ninjas[3] = new Ninja(100, 50, Math.PI / 2);// (X,Y,angulo,w,h,c)
		ninjas[4] = new Ninja(300, 300, Math.PI / 2);// (X,Y,angulo,w,h,c)
		ninjas[5] = new Ninja(700, 300, Math.PI / 2);// (X,Y,angulo,w,h,c)

		// posicion de las casas
		this.casas[0] = new Casa(200, 50);
		this.casas[1] = new Casa(400, 50);
		this.casas[2] = new Casa(600, 50);
		this.casas[3] = new Casa(200, 205);
		// this.casas[4] = new Casa(400, 205);
		this.casas[4] = new Casa(600, 205);
		this.casas[5] = new Casa(200, 290);
		this.casas[6] = new Casa(400, 290);
		this.casas[7] = new Casa(600, 290);
		this.casas[8] = new Casa(200, 440);
		this.casas[9] = new Casa(400, 440);
		this.casas[10] = new Casa(600, 440);
		this.casas[11] = new Casa(200, 140);
		this.casas[12] = new Casa(400, 140);
		this.casas[13] = new Casa(600, 140);
		this.casas[14] = new Casa(200, 350);
		this.casas[15] = new Casa(400, 350);
		this.casas[16] = new Casa(600, 350);
		this.casas[17] = new Casa(200, 500);
		this.casas[18] = new Casa(400, 500);
		this.casas[19] = new Casa(600, 500);
		// EXTREMOS////////////////////////////
		this.casas[20] = new Casa(55, 50);
		this.casas[21] = new Casa(745, 50);
		this.casas[22] = new Casa(55, 175);
		this.casas[23] = new Casa(745, 175);
		this.casas[24] = new Casa(55, 325);
		this.casas[25] = new Casa(745, 325);
		this.casas[26] = new Casa(55, 475);
		this.casas[27] = new Casa(745, 475);

		// floreria
		this.floreria = new Floreria(400, 205);

		// sakura
		this.sakura = new Sakura(400, 400);

		// rasengan
		this.rasengan = new Rasengan(sakura);

		// Inicia el juego!
		this.entorno.iniciar();

	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		if (sakura.isVivo()) {
			entorno.dibujarImagen(fondoImg, entorno.ancho() / 2, entorno.alto() / 2, 0);

			// Dibujamos ninjas y deszplazamiento correspondiente.

			// REVIVEN LOS NINJAS
			for (int i = 0; i < ninjas.length; i++) {
				if (ninjas[i].isRevivir()) {
					ninjas[i].dibNinja(entorno);
					if (i == 0 || i == 1 || i == 2) {
						ninjas[i].movHorizontal();
					} else {
						ninjas[i].movVertical();
					}
					if (rasengan.esDisparado(ninjas[i])) {
						ninjas[i].setRevivir(false);
					}
					if (sakura.meMatoNinja(ninjas[i])) {
						sakura.setVivo(false);
					}
				}
			}

			// CONTADOR DE TIEMPO
			contTiempo++;
			if (contTiempo == 300) {
				for (int j = 0; j < ninjas.length; j++) {
					if (j == 0 && j == 1 && j == 2) {
						ninjas[j].movHorizontal();
					} else {
						ninjas[j].movVertical();
					}
					ninjas[j].setRevivir(true);
				}
				contTiempo = 0;
			}

			// dibujo casas;
			for (int i = 0; i < casas.length; i++) {
				if (i == random) {
					CasaEntregar casaEntregada = new CasaEntregar(casas[random].getX(), casas[random].getY());
					casaEntregada.dibujar(entorno);
					if (casaEntregada.esEntregado(sakura) && sakura.tengoFlor(sakuraConFlor)) {
						sakura.setIMAGE(sakuraSinFlor);
						puntaje += 5;
						random = (int) Math.floor(Math.random() * casas.length);
					}
				} else {
					casas[i].dibujar(entorno);
				}
			}

			floreria.dibujar(entorno);

			sakura.dibujar(entorno);
			sakura.meMuevo(entorno, rasengan);

			rasengan.dispararVeces(entorno);
			rasengan.disparaSakura(sakura);

			// sakura recoge flor de floreria
			if (floreria.agarraFlor(sakura)) {
				sakura.setIMAGE(sakuraConFlor);
			}

			entorno.cambiarFont("Arial", 18, Color.white);
			entorno.escribirTexto("puntaje: " + puntaje, 100, 30);

			// ninja mata a sakura
			for (int i = 0; i < ninjas.length; i++) {
				if (sakura.meMatoNinja(ninjas[i])) {
					sakura.setVivo(false);
				}
			}
		}
		if (!sakura.isVivo()) {
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.cambiarFont("Arial", 25, Color.white);
			entorno.escribirTexto("Tu puntaje fue: " + puntaje, 300, 100);
			entorno.escribirTexto("Apretá al tecla ENTER para jugar de nuevo", 200, 500);
		}
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			this.sakura = new Sakura(400, 400);
			sakura.setVivo(true);
			puntaje = 0;
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
