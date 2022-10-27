package com.mygdx.game;

import org.w3c.dom.Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class AbstractJunkObject implements Movible {
	private int vida;
	private boolean destroyed;

	
	// dibujo
	private Sprite spr;
	private Text txBasura;

	// posicion en pantalla
	private int x;
	private int y;
	
	//velocidad
	private int xSpeed;
	private int ySpeed;
	
	public AbstractJunkObject(int x, int y, int xSpeed, int ySpeed, int size, Texture tx) {
    	//Inicia el valor de x.
    	this.x = x;
        this.y = y;
        this.setXSpeed(xSpeed);
        this.destroyed = false;
        //if (y-size < 0) this.y = y+size;
    	//if (y+size > Gdx.graphics.getHeight()) this.y = y-size;
	}
	
	
	// ESTO DEBE SER ABSTRACTO.
    public abstract Rectangle getArea ();
    	//return spr.getBoundingRectangle();
	
	//metodos abstractos
	
	
	//Podria haber movimiento diferente para algunos objetos
	public abstract void update();
	
	
	//permitir generar basura en una posición x,y determinada
	public abstract void draw(SpriteBatch batch);
	
	public void setDestroyed() {
		this.destroyed = true;
	}
	
	public boolean isDestroyed () {
		return this.destroyed;
	}
	
	//getters y setters
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXSpeed() {
		return xSpeed;
	}

	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

}