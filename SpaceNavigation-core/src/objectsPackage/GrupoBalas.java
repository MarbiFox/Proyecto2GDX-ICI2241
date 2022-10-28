package objectsPackage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GrupoBalas {
	private ArrayList<Bullet> balas;
	private Sound soundBala;
	private Texture txBala;

	public GrupoBalas() {
		this.balas = new ArrayList<>();
		soundBala = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
		txBala = new Texture(Gdx.files.internal("Rocket2.png"));
	}

	public void crearBala(float x, float y, int velX, int velY) {
		// Crear bullet
		Bullet bala = new Bullet(x, y, velX, velY, txBala);
		// Agregar bala
		balas.add(bala);
		// Reproducir sonido
		soundBala.play();
	}

	public void updateBullets (SpriteBatch batch) {
		// Actualizar estado de las balas.
		for (int i = 0; i < this.getSize(); i++) {
			Bullet b = this.getBullet(i);
			b.update();
			if (b.isDestroyed()) {
				this.eliminarBala(b);
				i--; // para no saltarse 1 tras eliminar del arraylist
			}
			else {
				b.draw(batch);
			}
		}
	}

	public void eliminarBala(Bullet b) {
		balas.remove(b);
	}

	public int getSize() {
		return balas.size();
	}

	public Bullet getBullet(int i) {
		return balas.get(i);
	}

}
