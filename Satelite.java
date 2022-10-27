package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Satelite extends AbstractJunkObject {
	
	
	public Satelite(int x, int y, int xSpeed, int ySpeed, int size, Texture tx) {
		super(x,y,xSpeed,ySpeed,size,tx);
		setVida(2);
	}
}
