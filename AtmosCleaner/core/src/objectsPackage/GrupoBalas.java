package objectsPackage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GrupoBalas {
	// Atributos
	private ArrayList<Bullet> balas;
	private Sound soundBala;
	private Texture txBala;
	
	// Constructor
	public GrupoBalas() {
		this.balas = new ArrayList<>();
		soundBala = Gdx.audio.newSound(Gdx.files.internal("flaunch.wav"));
		txBala = new Texture(Gdx.files.internal("Rocket2.png"));
	}
	
	// Crear y agregar una bala nueva a la colección
	public void crearBala(float x, float y, int velX, int velY) {
		// Crear bullet
		Bullet bala = new Bullet(x, y, velX, velY, txBala);
		// Agregar bala
		balas.add(bala);
		// Reproducir sonido
		soundBala.play(0.25f);
	}
	
	// Actualizar y dibujar cada bala de la colección
	public void updateBullets (SpriteBatch batch) {
		// Actualizar estado de las balas.
		for (int i = 0; i < this.getSize(); i++) {
			Bullet b = this.getBullet(i);
			b.update(); // Actualizar
			// Si la bala esta destruida, eliminarla 
			if (b.isDestroyed()) {
				this.eliminarBala(b);
				i--; // para no saltarse 1 tras eliminar del arraylist
			}
			else {
				b.draw(batch); // Dibujar
			}
		}
	}
	
	// Eliminar una bala del grupo
	public void eliminarBala(Bullet b) {
		balas.remove(b);
	}
	
	// Obtener la cantidad de balas en pantalla
	public int getSize() {
		return balas.size();
	}
	
	// Obtener una bala específica
	public Bullet getBullet(int i) {
		return balas.get(i);
	}

}
