package objectsPackage;

import java.util.Random;

//clase de manejo de la basura

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import abstractInterfacePackage.AbstractJunkObject;

import java.util.ArrayList;

public class ObjetosBasura {
	private ArrayList<AbstractJunkObject> list;
	private Sound explosionSound;
	private int movementModifier;
	private int contBasura;

	public ObjetosBasura() {
		list = new ArrayList<AbstractJunkObject>();
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1, 0.5f);
		movementModifier = 1;
		contBasura = 0;
	}
	
	// Generar basura
	public void generarBasura() {
		Random r = new Random();

		if (Math.random() >= 0.20) {
			add(new Asteroide(0, 250 + r.nextInt((int) Gdx.graphics.getHeight() - 350),
					movementModifier + r.nextInt(2), new Texture(Gdx.files.internal("aGreyMedium4.png"))));
		} else {
			add(new Satelite(Gdx.graphics.getWidth(), 250 + r.nextInt((int) Gdx.graphics.getHeight() - 350), 
					movementModifier + r.nextInt(3), new Texture(Gdx.files.internal("satelite.png"))));
		}

	}
	
	
	//Actualizar Movimiento.
	public void updateMovement (SpriteBatch batch) {
		 for(int i = 0; i < list.size();i++) {
	            AbstractJunkObject obj = list.get(i);
	            obj.update();
	            obj.draw(batch);
	            if (obj.getOut() == true) {
	            	remove(obj);
	            	contBasura++;
	            }
	     }
	}
	
	//Buscar colisiones.
	public int checkColisionBala(SpriteBatch batch, GrupoBalas bullets) {
		int cantColisiones = 0;
		for (int i = 0; i < list.size(); i++) {
			AbstractJunkObject obj = list.get(i);
			for (int j = 0; j < bullets.getSize(); j++) {
				Bullet b = bullets.getBullet(j);
				if (b.checkCollision(obj) == true) {
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

	public void draw(SpriteBatch batch) {
		for (int i = 0; i < list.size(); i++) {
			AbstractJunkObject b = list.get(i);
			b.draw(batch);
		}
	}

	public boolean dispose() {
		this.explosionSound.dispose();
		list.clear();
		return true;
	}

	public int cantidadDeAsteroides() {
		return list.size();
	}

	private void add(AbstractJunkObject b) {
		list.add(b);
	}

	public void remove (AbstractJunkObject obj) {
		list.remove(obj);
	}
	
	public int getCantBasura () {
		return this.contBasura;
	}
}