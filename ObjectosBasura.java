package com.mygdx.game;
import java.util.ArrayList;


//clase de manejo de la basura

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ObjectosBasura {
	public Array<AbstractJunkObject> list;
	public Array<AbstractJunkObject> list1;
	private Sound explosionSound;

	public ObjectosBasura() {
		list = new Array<AbstractJunkObject>();
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.5f);
	}
	
	
	//Esta es la estandar entregada por el profe, si se realizo algo o directamente cambio borrarla
	public void colisionEntreBasura() {
		for (int i=0;i<list.size;i++) {
			AbstractJunkObject b1 = list.get(i);   
	        for (int j=0;j<list1.size;j++) {
	        	AbstractJunkObject b2 = list1.get(j); 
	          if (i<j) {
	        	  b1.colision(b2);
	          }
	        }
	    } 
	}
	
	public boolean checkColisionBala(Bullet bullet){
		for(int i = 0; i < list.size;i++) {
			AbstractJunkObject b = list.get(i);
			
			//Cambiar la firma de bullet para que reciba AbstractJunkObject
			if(bullet.checkCollision(b)) {
				explosionSound.play();
				b.destroyed();
				list.removeIndex(i);
				list1.removeIndex(i);
				//agregar un metodo de la bala para que se destruya a ella misma cuando colisione
				//estoy pensando en tener distintos tipos de bala
				return true;
			}
		}
		return false;
	}
	
	public void draw(SpriteBatch batch) {
		for(int i = 0; i < list.size;i++) {
			AbstractJunkObject b = list.get(i);
			b.draw(batch);
		}
	}
	
	public boolean dispose(){
		this.explosionSound.dispose();
		list.clear();
		list1.clear();
		return true;
	}
}
