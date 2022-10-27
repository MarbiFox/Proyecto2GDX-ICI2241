package com.mygdx.game;

import org.w3c.dom.Text;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractJunkObject {
	private int vida;
	private boolean destroyed = false;

	
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
		spr = new Sprite(tx);
		
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
 	
        //validar que borde x
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
         
        //validar que borde y
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;
    	
        spr.setPosition(x, y);
	}
	
	//metodos abstractos
	
	
	//Se me ocurrio que aqui podria haber distintos tipos de asteroide, si chocan, podrian fusionarse y tener más vida
	public void colision(AbstractJunkObject b) {}
	//manejar colision interna del objeto, aqui que ocurre cuando el objeto es destruido?, se va?, deja algo?
	public void colision(Bullet b) {}
	//Podria haber movimiento diferente para algunos objetos
	public void update() {}
	//permitir generar basura en una posición x,y determinada
	

	public void draw(SpriteBatch batch) {
		this.spr.draw(batch);
	}

	public void destroyed() {
		this.destroyed = true;
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

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

}
