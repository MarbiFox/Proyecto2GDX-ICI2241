package objectsPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import abstractInterfacePackage.Movible;
import screensPackage.PantallaJuego;

public class Nave implements Movible {

	// Atributos
	private int movementModifier;
	private Sprite spr;
	private GrupoBalas bullets;

	// Constructor
	public Nave(int x, int y, Texture tx, GrupoBalas group) {
		spr = new Sprite(tx);
		spr.setPosition(x, y);
		spr.setBounds(x, y, 45, 45);
		bullets = group;
		movementModifier = 5;
	}

	// Cambiar los valores de posición de la entidad.
	public float mover(float x, float y) {
		// Mover con teclado.
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			x = x - movementModifier;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			x = x + movementModifier;
		}
		return x;

	}

	// Verificar si la entidad sobrepasa los límites de la pantalla.
	public float checkBorders(float x, float y) {
		// Mantener en los bordes.
		if (x < bordeIzq) {
			x = bordeIzq;
		}
		if (x + spr.getWidth() > bordeDer) {
			x = bordeDer - spr.getWidth();
		}
		return x;
	}

	// Colocar en los nuevos valores de posición a la entidad.
	public void setPos(Sprite spr, float x, float y) {
		x = mover(x, y);
		x = checkBorders(x, y);
		spr.setPosition(x, y);
	}

	public void disparar(float x, float y) {
		bullets.crearBala(x + spr.getWidth() / 2 - 5, y + spr.getHeight() - 5, 0, 5);
	}

	public void draw(SpriteBatch batch, PantallaJuego juego) {
		float x = spr.getX();
		float y = spr.getY();
		setPos(spr, x, y);
		spr.draw(batch);

		// Disparar si se oprime el botón y si la cantidad de balas por pantalla es
		// menor o igual a 5.
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && bullets.getSize() < 5) {
			disparar(x, y);
		}
	}

}
