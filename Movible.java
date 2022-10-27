package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;


public interface Movible {
	public static final int bordeIzq = 0;
	public static final int bordeDer = Gdx.graphics.getWidth();
	public float keepInBorder (float x);
	public float mover (float x);
	public void setPos (Sprite spr, float x, float y);
}
