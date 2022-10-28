package objectsPackage;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import abstractInterfacePackage.AbstractJunkObject;

public class Satelite extends AbstractJunkObject {

	private Sprite spr;

	public Satelite (int x, int y, int xSpeed, Texture tx) {
		super(x, y, xSpeed);
		setVida(2);
		spr = new Sprite(tx);
		spr.setX(x);
		spr.setY(y);
	}
	
	public Rectangle getArea() {
		return spr.getBoundingRectangle();
	}
	
	public void draw (SpriteBatch batch) {
		spr.draw(batch);
	}
	
	public void update() {
		float x = spr.getX();
		float y = spr.getY();
		this.setPos(spr, x, y);
	}

	@Override
	public float checkBorders (float x, float y) {
		// TODO Auto-generated method stub
		if (x + spr.getWidth() < bordeIzq) {
			this.setDestroyed();
			this.setOut();
		}
		return x;
		
	}

	@Override
	public float mover(float x, float y) {
		// TODO Auto-generated method stub
		// actualizar posición
		Random rand = new Random();
		int r = rand.nextInt(10);
		x = x - getXSpeed() - r;
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