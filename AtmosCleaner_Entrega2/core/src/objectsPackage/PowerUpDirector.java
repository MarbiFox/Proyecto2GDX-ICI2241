package objectsPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import abstractInterfacePackage.Builder;

public class PowerUpDirector {
	public void createSpeedPowerUp (Builder b) {
		b.setSprite(new Sprite (new Texture(Gdx.files.internal("powerUpEnergy_1.png"))));
		b.setSpeed(1);
		b.setSound(Gdx.audio.newSound(Gdx.files.internal("velocidad.mp3")));
	}
	public void createBulletPowerUp (Builder b) {
		b.setSprite(new Sprite (new Texture(Gdx.files.internal("powerUpBullet_1.png"))));
		b.setSpeed(1);
		b.setSound(Gdx.audio.newSound(Gdx.files.internal("recarga.mp3")));
	}
}
