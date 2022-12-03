package objectsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import abstractInterfacePackage.AbstractJunkObject;
import abstractInterfacePackage.Movible;

public class Bullet implements Movible {

	// Atributos
	private int movementModifier = 6;
	private boolean destroyed = false;
	private Sprite spr;
	
	// Constructor
	public Bullet(float x, float y, int xSpeed, int ySpeed, Texture tx) {
		spr = new Sprite(tx);
		spr.setPosition(x, y);
	}
	
	// Cambiar los valores de posición de la entidad.
	public float mover(float x, float y) {
		// Mover constantemente.
		y = y + movementModifier;
		return y;

	}
	
	// Verificar si la entidad sobrepasa los límites de la pantalla.
	public float checkBorders(float x, float y) {
		// Verificar si pasó el borde.
		if (y > bordeSup) {
			destroyed = true;
		}
		return y;
	}
	
	// Colocar en los nuevos valores de posición a la entidad.
	public void setPos(Sprite spr, float x, float y) {
		y = mover(x, y);
		y = checkBorders(x, y);
		spr.setPosition(x, y);
	}
	
	// Actualizar el estado de la bala
	public void update() {
		float x = spr.getX();
		float y = spr.getY();
		setPos(spr, x, y);
	}
	
	// Dibujar la bala
	public void draw(SpriteBatch batch) {
		spr.draw(batch);
	}
	
	// Verificar si la bala choca con algún objeto
	public boolean checkCollision(AbstractJunkObject obj) {
		if (spr.getBoundingRectangle().overlaps(obj.getArea())) {
			return true;
		}
		return false;
	}
	
	public boolean checkCollisionPowerUp(PowerUp pp) {
		if (spr.getBoundingRectangle().overlaps(pp.getArea())) {
			return true;
		}
		return false;
	}
	
	// Verificar si la bala se destruyó o está destruida
	public boolean isDestroyed() {
		return destroyed;
	}
}
