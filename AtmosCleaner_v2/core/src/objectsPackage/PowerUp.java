package objectsPackage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

import abstractInterfacePackage.Strategy;

public class PowerUp {
	//Atributos
	private Sprite spr;
	private int speed;
	private Sound sound;
	private Strategy s;
	
	//Constructor
	public PowerUp (Sprite spr, int speed, Sound sound) {
		this.spr = spr;
		this.speed = speed;
		this.sound = sound;
	}
	
	//Métodos
	public void hacerAlgo () {
		System.out.println("Ola");
		sound.play();
	}
	
	public void setStrategy (Strategy s) {
		this.s = s;
	}
	
	public void execute (Nave nave) {
		s.executePowerUp(nave);
	}
}
