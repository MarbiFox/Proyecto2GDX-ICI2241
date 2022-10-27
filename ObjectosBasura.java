package com.mygdx.game;

import java.util.Random;

//clase de manejo de la basura

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ObjectosBasura {
	private Array<AbstractJunkObject> list;
	private Array<AbstractJunkObject> list1;
	private Sound explosionSound;
	
	//Velocidades iniciales de los objetos basura
	private int velXBasura;
	private int velYBasura;

	public ObjectosBasura(int velXBasura,int velYBasura,String sound) {
		list = new Array<AbstractJunkObject>();
		explosionSound = Gdx.audio.newSound(Gdx.files.internal(sound));
		explosionSound.setVolume(1,0.5f);
		this.velXBasura = velXBasura;
		this.velYBasura = velYBasura;
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
				remove(i);
				//agregar un metodo de la bala para que se destruya a ella misma cuando colisione
				//estoy pensando en tener distintos tipos de bala
				return true;
			}
		}
		return false;
	}
	
	
	//Generar basura 
	
	public void generarBasura() {
		Random r = new Random();
		
		if (Math.random() >= 0.1) {
			add( new Asteroide(0,
					250 + r.nextInt((int) Gdx.graphics.getHeight() - 350),
					20 + r.nextInt(10),
					velXBasura + r.nextInt(4),
					velYBasura + r.nextInt(4),
					new Texture(Gdx.files.internal("aGreyMedium4.png"))));
		}
		else {
			add( new Satelite(r.nextInt((int) Gdx.graphics.getWidth()),
					250 + r.nextInt((int) Gdx.graphics.getHeight() - 350),
					20 + r.nextInt(10),
					velXBasura + r.nextInt(4),
					velYBasura + r.nextInt(4),
					new Texture(Gdx.files.internal("aGreyMedium4.png"))));
		}
		
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
	
	public int cantidadDeAsteroides() {
		return list.size;
	}
	
	private void add(AbstractJunkObject b) {
		list.add(b);
		list1.add(b);
	}
	
	private void remove(int i) {
		list.removeIndex(i);
		list1.removeIndex(i);
	}
	
	
	//setters y getters de velocidad
	public int getVelXBasura() {
		return velXBasura;
	}

	public void setVelXBasura(int velXBasura) {
		this.velXBasura = velXBasura;
	}

	public int getVelYBasura() {
		return velYBasura;
	}

	public void setVelYBasura(int velYBasura) {
		this.velYBasura = velYBasura;
	}
}
