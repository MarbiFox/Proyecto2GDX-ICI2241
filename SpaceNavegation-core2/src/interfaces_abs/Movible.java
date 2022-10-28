package interfaces_abs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;


public interface Movible {
	public static final int bordeIzq = 0;
	public static final int bordeDer = Gdx.graphics.getWidth();
	public static final int bordeSup = Gdx.graphics.getHeight();
	public float checkBorders (float x, float y);
	public float mover (float x, float y);
	public void setPos (Sprite spr, float x, float y);
}
