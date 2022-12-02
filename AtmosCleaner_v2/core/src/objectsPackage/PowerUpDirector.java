package objectsPackage;

import com.badlogic.gdx.Gdx;

import abstractInterfacePackage.Builder;

public class PowerUpDirector {
	public void createSpeedPowerUp (Builder b) {
		b.setSprite(null);
		b.setSpeed(1);
		b.setSound(Gdx.audio.newSound(Gdx.files.internal("velocidad.mp3")));
	}
	public void createBulletPowerUp (Builder b) {
		b.setSprite(null);
		b.setSpeed(1);
		b.setSound(Gdx.audio.newSound(Gdx.files.internal("recarga.mp3")));
	}
}
