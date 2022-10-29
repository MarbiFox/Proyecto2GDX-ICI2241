package objectsPackage;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import abstractInterfacePackage.AbstractJunkObject;

public class Satelite extends AbstractJunkObject {
	
	// Atributos
	private Sprite spr;
	
	// Constructor
	public Satelite (int x, int y, int xSpeed, Texture tx) {
		super(x, y, xSpeed);
		spr = new Sprite(tx);
		spr.setX(x);
		spr.setY(y);
	}
	
	// Obtener el área/superficie en el que está el sprite de un objeto.
	public Rectangle getArea() {
		return spr.getBoundingRectangle();
	}
	
	// Dibujar el objeto en el batch.
	public void draw (SpriteBatch batch) {
		spr.draw(batch);
	}
	
	// Actualizar el movimiento del objeto.
	public void update() {
		float x = spr.getX();
		float y = spr.getY();
		this.setPos(spr, x, y);
	}

	@Override
	// Verificar si la entidad sobrepasa los límites de la pantalla.
	public float checkBorders (float x, float y) {
		// TODO Auto-generated method stub
		if (x + spr.getWidth() < bordeIzq) {
			this.setDestroyed();
			this.setOut();
		}
		return x;
		
	}

	@Override
	// Cambiar los valores de posición de la entidad.
	public float mover(float x, float y) {
		// TODO Auto-generated method stub
		// Actualizar posición
		Random rand = new Random();
		int r = rand.nextInt(5);
		x = x - getXSpeed() - r;
		return x;
	}

	@Override
	// Colocar en los nuevos valores de posición a la entidad.
	public void setPos(Sprite spr, float x, float y) {
		// TODO Auto-generated method stub
		x = mover(x, y);
		x = checkBorders(x, y);
		spr.setPosition(x, y);
	}
}