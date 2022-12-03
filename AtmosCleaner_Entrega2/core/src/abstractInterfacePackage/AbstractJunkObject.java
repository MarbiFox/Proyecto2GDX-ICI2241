package abstractInterfacePackage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class AbstractJunkObject implements Movible {
	// Atributos
	private boolean destroyed;
	private boolean isOut;
	private int xSpeed;

	// Constructor
	public AbstractJunkObject(int x, int y, int xSpeed) {
		// Inicia los valores.
		this.setXSpeed(xSpeed);
		this.destroyed = false;
		this.isOut = false;
	}

	// ---- Métodos Abstractos ----//

	// Obtener el área/superficie en el que está el sprite de un objeto.
	public abstract Rectangle getArea();

	// Actualizar el movimiento del objeto.
	public abstract void update();

	// Dibujar el objeto en el batch.
	public abstract void draw(SpriteBatch batch);

	// Mover el objeto. (Template Method)
	public abstract float moverObjeto(float x);

	// ---- Métodos Concretos ----//

	// Indicar que el objeto fue destruido.
	public void setDestroyed() {
		this.destroyed = true;
	}

	// Indicar que el objeto se salió de los límites de la pantalla.
	public void setOut() {
		this.isOut = true;
	}
	
	// Mover objeto concreto (Template Method)
	public float mover(float x, float y) {
		x = x + y;
		return x;
	}

	// ---- Getters y Setters ----//

	public boolean isDestroyed() {
		return this.destroyed;
	}

	public boolean getOut() {
		return this.isOut;
	}

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
}