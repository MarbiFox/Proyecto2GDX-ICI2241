package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Asteroide extends AbstractJunkObject {
	
	public Asteroide(int x, int y, int xSpeed, int ySpeed, int size, Texture tx) {
		super(x,y,xSpeed,ySpeed,size,tx);
		setVida(1);
	}
	
	public void update() { 
		
		//actualizar movimiento
		setX(getX() + getXSpeed());
		
		
		//manejo de colision
		if (getX() < - bordeDer || getX()+bordeDer > bordeDer)
        	this.destroyed();
		
	
        this.setPos(getX(), getY());
	}

	@Override
	public float checkBorders(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float mover(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPos(Sprite spr, float x, float y) {
		// TODO Auto-generated method stub
		
	}
	
}
