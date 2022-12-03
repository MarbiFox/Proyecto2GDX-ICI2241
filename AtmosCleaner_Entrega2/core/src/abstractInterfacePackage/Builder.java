package abstractInterfacePackage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Builder {
	//Métodos Setters que todo PowerUp tendrá.
	public void setSprite(Sprite spr);
	public void setSpeed(int speed);
	public void setSound(Sound sound);
}
