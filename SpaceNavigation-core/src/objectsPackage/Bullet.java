package objectsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import abstractInterfacePackage.AbstractJunkObject;
import abstractInterfacePackage.Movible;


public class Bullet implements Movible {
	private int movementModifier = 6;
	private boolean destroyed = false;
	private Sprite spr;
	    
	    public Bullet(float x, float y, int xSpeed, int ySpeed, Texture tx) {
	    	spr = new Sprite(tx);
	    	spr.setPosition(x, y);
	    }
	    
		public float mover(float x, float y) {
			// Mover constantemente.
			y = y + movementModifier;
			return y;
			
		}

		public float checkBorders(float x, float y) {
			// Verificar si pasó el borde.
			if (y > bordeSup) {
				destroyed = true;
			}
			return y;
		}
		
		public void setPos (Sprite spr, float x, float y) {
			y = mover(x, y);
			y = checkBorders(x, y);
			spr.setPosition(x, y);
		}
		
	    public void update() {
	    	//Actualizar el estado de la bala.
	    	float x = spr.getX();
			float y = spr.getY();
	        setPos(spr, x, y);
	    }
	    
	    public void draw(SpriteBatch batch) {
	    	spr.draw(batch);
	    }
	    
	    public boolean checkCollision(AbstractJunkObject obj) {
	        if(spr.getBoundingRectangle().overlaps(obj.getArea())){
	            return true;
	        }
	        return false;
	    }
	    
		public int getX() {
			return (int) spr.getX();
		}

		public int getY() {
			return (int) spr.getY();
		}

	    public boolean isDestroyed() {return destroyed;}
}
