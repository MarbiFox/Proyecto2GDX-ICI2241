package entidad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Ball2 {
	private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private Sprite spr;
    private boolean isOut = false;

    public Ball2(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
    	//Coloca el sprite.
    	spr = new Sprite(tx);
    	
    	//Inicia el valor de x.
    	this.x = 0;
        
        this.y = y;
        if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight()) this.y = y-size;
        spr.setPosition(x, y);
        this.setXSpeed(xSpeed);
        //this.setySpeed(ySpeed);
    }
    public void update() {
    	//Desplazamiento en x.
        x += xSpeed;

        if (x < -spr.getWidth() || x+spr.getWidth() > Gdx.graphics.getWidth())
        	this.isOut = true;
        	//x = 800;
        	
        spr.setPosition(x, y);
    }
    
    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }
    
    /*
    public void checkCollision(Ball2 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.spr.getBoundingRectangle())){
        	// rebote
            if (getXSpeed() ==0) setXSpeed(getXSpeed() + b2.getXSpeed()/2);
            if (b2.getXSpeed() ==0) b2.setXSpeed(b2.getXSpeed() + getXSpeed()/2);
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());
            
            if (getySpeed() ==0) setySpeed(getySpeed() + b2.getySpeed()/2);
            if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed() + getySpeed()/2);
            setySpeed(- getySpeed());
            b2.setySpeed(- b2.getySpeed()); 
        }
    }
    */
	public int getXSpeed() {
		return xSpeed;
	}
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
    
}
