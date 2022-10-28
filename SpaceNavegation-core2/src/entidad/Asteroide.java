package entidad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import interfaces_abs.AbstractJunkObject;

public class Asteroide extends AbstractJunkObject {

	private Sprite spr;

	public Asteroide(int x, int y, int xSpeed, int ySpeed, int size, Texture tx) {
		super(x, y, xSpeed, ySpeed, size, tx);
		setVida(1);
		spr = new Sprite(new Texture(Gdx.files.internal("aGreyMedium4.png")));
		spr.setX(x);
		spr.setY(y);
	}
	
	public Rectangle getArea() {
		System.out.println(spr.getX() + ' ' + spr.getY() + ' ' + spr.getWidth() + ' ' + spr.getHeight());
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
		if (x - spr.getWidth()/2 > bordeDer) {
			this.setDestroyed();
		}
		return x;
		
	}

	@Override
	public float mover(float x, float y) {
		// TODO Auto-generated method stub
		// actualizar posición
		x = x + getXSpeed();
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