package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjectosBasura {
	public ArrayList<BasuraEspacial> list;
	public ArrayList<BasuraEspacial> list1;
	private Sound explosionSound;

	public ObjectosBasura() {
		list = new ArrayList<BasuraEspacial>();
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
	}
	
	
	//Esta es la estandar entregada por el profe, si se realizo algo o directamente cambio borrarla
	public void colisionEntreBasura() {
		for (int i=0;i<list.size();i++) {
	    	BasuraEspacial b1 = list.get(i);   
	        for (int j=0;j<list1.size();j++) {
	          BasuraEspacial b2 = list1.get(j); 
	          if (i<j) {
	        	  b1.colision(b2);
	          }
	        }
	    } 
	}
	
	public boolean checkColisionBala(Bullet bullet){
		for(int i = 0; i < list.size();i++) {
			BasuraEspacial b = list.get(i);
			if(bullet.checkCollision(b)) {
				explosionSound.play();
				b.destroyed();
				list.remove(i);
				//agregar un metodo de la bala para que se destruya a ella misma cuando colisione
				//estoy pensando en tener distintos tipos de bala
				return true;
			}
		}
		return false;
	}
	
	public void draw(SpriteBatch batch) {
		for(int i = 0; i < list.size();i++) {
			BasuraEspacial b = list.get(i);
			b.draw(batch);
		}
	}
	
	public boolean dispose(){
		this.explosionSound.dispose();
		list.clear();
		return true;
	}
}
