package objectsPackage;

import java.util.Random;

//clase de manejo de la basura

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import abstractInterfacePackage.AbstractJunkObject;

import java.util.ArrayList;

//Clase que encapsula y maneja una colección de objetos abstractos.
public class ObjetosBasura {
	// Atributos
	private ArrayList<AbstractJunkObject> list;
	private Sound explosionSound;
	private int movementModifier;
	private int contBasura;

	// Constructor
	public ObjetosBasura() {
		// Crear lista
		list = new ArrayList<AbstractJunkObject>();
		// Inicializar el sonido de explosión
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("atari_fire_1.wav"));
		explosionSound.setVolume(1, 0.5f);
		// Inicializar el modificador de movimiento
		movementModifier = 1;
		// Inicializar el contador para la basura acumulada
		contBasura = 0;
	}

	// Generar un objeto basura específico según una probabilidad random
	public void generarBasura() {
		// Definir una variable random
		Random r = new Random();

		// Condicionar la generación de objetos
		if (Math.random() >= 0.20) {
			// Crear un asteroide
			add(new Asteroide(0, 250 + r.nextInt((int) Gdx.graphics.getHeight() - 350), 
					movementModifier + r.nextInt(2), new Texture(Gdx.files.internal("aGreyMedium4.png"))));
		} else {
			// Crear un Sátelite Basura
			add(new Satelite(Gdx.graphics.getWidth(), 250 + r.nextInt((int) Gdx.graphics.getHeight() - 350),
					movementModifier + r.nextInt(2), new Texture(Gdx.files.internal("chatarra.png"))));
		}

	}

	// Actualizar el movimiento de cada objeto basura.
	public void updateMovement(SpriteBatch batch) {
		// Recorrer cada objeto destruible
		for (int i = 0; i < list.size(); i++) {
			AbstractJunkObject obj = list.get(i);
			obj.update(); // Actualizar posición
			obj.draw(batch); // Dibujar
			// Verificar si el objeto se salió de los límites
			if (obj.getOut() == true) {
				// Eliminar objeto y aumentar la basura acumulada
				remove(obj);
				contBasura++;
			}
		}
	}

	// Buscar colisiones.
	public int checkColisionBala(SpriteBatch batch, GrupoBalas bullets) {
		// Declarar un contador de colisiones
		int cantColisiones = 0;
		// Recorrer los objetos basura
		for (int i = 0; i < list.size(); i++) {
			AbstractJunkObject obj = list.get(i);
			// Recorrer las balas en pantalla
			for (int j = 0; j < bullets.getSize(); j++) {
				Bullet b = bullets.getBullet(j);
				// Verificar si existe una colisión entre algun objeto con la bala
				if (b.checkCollision(obj) == true) {
					// Efectuar la colisión
					explosionSound.play();
					obj.setDestroyed();
					remove(obj);
					bullets.eliminarBala(b);
					cantColisiones++;
				}
			}
		}
		return cantColisiones;
	}
	
	// Recorrer y dibujar cada objeto
	public void draw(SpriteBatch batch) {
		for (int i = 0; i < list.size(); i++) {
			AbstractJunkObject b = list.get(i);
			b.draw(batch);
		}
	}
	
	// Destruir cada los datos de la clase
	public boolean dispose() {
		this.explosionSound.dispose();
		list.clear();
		return true;
	}
	
	// Obtener la cantidad de asteroides
	public int cantidadDeAsteroides() {
		return list.size();
	}
	
	// Agregar un elemento a la lista de objetos
	private void add(AbstractJunkObject b) {
		list.add(b);
	}
	
	// Eliminar un elemento de la lista de objetos
	public void remove(AbstractJunkObject obj) {
		list.remove(obj);
	}
	
	// Obtener la cantidad de basura acumulada
	public int getCantBasura() {
		return this.contBasura;
	}
}