package objectsPackage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

import abstractInterfacePackage.Builder;

public class PowerUpBuilder implements Builder {
	//Atributos
	private Sprite spr;
	private int speed;
	private Sound sound;
	
	//Métodos
	@Override
	public void setSprite(Sprite spr) {
		this.spr = spr;
	}
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override
	public void setSound(Sound sound) {
		this.sound = sound;
	}
	
	public PowerUp getPowerUp () {
		return new PowerUp(spr, speed, sound);
	}

}
