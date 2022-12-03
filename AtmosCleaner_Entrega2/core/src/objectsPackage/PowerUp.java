package objectsPackage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import abstractInterfacePackage.Movible;
import abstractInterfacePackage.Strategy;

public class PowerUp implements Movible {
	// Atributos
	private Sprite spr;
	private int speed;
	private Sound sound;
	private Strategy s;
	private boolean isDestroyed;

	// Constructor
	public PowerUp(Sprite spr, int speed, Sound sound) {
		this.spr = spr;
		spr.setX(0);
		spr.setY(650);
		this.speed = speed;
		this.sound = sound;
		this.isDestroyed = false;
	}

	// Métodos
	public void setSprite (Sprite spr) {
		this.spr = spr;
	}
	
	// Colisión con bala.
	public boolean checkCollision (GrupoBalas balas, Nave nave) {
		for (int i = 0; i < balas.getSize(); i++) {
			Bullet b = balas.getBullet(i);
			if (b.checkCollisionPowerUp(this) == true) {
				this.execute(nave);
				//this.isDestroyed = true;
				this.spr = null;
				return true;
			}
		}
		return false;
	}
	
	// Obtener el área del Sprite.
	public Rectangle getArea () {
		return spr.getBoundingRectangle();
	}

	public void setStrategy(Strategy s) {
		this.s = s;
	}
	
	public void setIsDestroyed(boolean b) {
		this.isDestroyed = b;
	}
	
	//Ejecutar el PowerUp (Strategy)
	public void execute(Nave nave) {
		s.executePowerUp(nave);
		sound.play();
	}
	
	//Desabilitar el PowerUp (Strategy)
	public void getToNormal(Nave nave) {
		s.disablePowerUp(nave);
		this.isDestroyed = true;
	}

	public void update() {
		float x = spr.getX();
		float y = spr.getY();
		this.setPos(spr, x, y);
	}

	public void draw(SpriteBatch batch) {
		spr.draw(batch);
	}

	public boolean getIsDestoyed() {
		return this.isDestroyed;
	}

	public float getX() {
		return spr.getY();
	}

	@Override
	public float checkBorders(float x, float y) {
		if (x - spr.getWidth() / 2 > bordeDer) {
			this.isDestroyed = true;
		}
		return x;
	}

	@Override
	public float mover(float x, float y) {
		// Actualizar posición
		x = x + speed;
		return x;
	}

	@Override
	public void setPos(Sprite spr, float x, float y) {
		// TODO Auto-generated method stub
		x = mover(x, y);
		x = checkBorders(x, y);
		spr.setPosition(x, y);
	}
}
