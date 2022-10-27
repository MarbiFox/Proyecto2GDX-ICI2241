package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Asteroide extends AbstractJunkObject {
	
	public Asteroide(int x, int y, int xSpeed, int ySpeed, int size, Texture tx) {
		super(x,y,xSpeed,ySpeed,size,tx);
		setVida(1);
	}
	
	public void update() {
	}
	
}
