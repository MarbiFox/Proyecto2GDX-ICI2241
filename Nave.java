package entidad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import interfaces_abs.Movible;
import pantalla.PantallaJuego;
import java.util.Timer;

public class Nave implements Movible {

	private boolean derrota = false;
	private int vidas = 3;
	private int movementModifier;
	private Sprite spr;
	private GrupoBalas bullets;

	public Nave(int x, int y, Texture tx, GrupoBalas group) {
		spr = new Sprite(tx);
		spr.setPosition(x, y);
		spr.setBounds(x, y, 45, 45);
		bullets = group;
		movementModifier = 3;
	}

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
	
	public void setPos (Sprite spr, float x, float y) {
		x = mover(x, y);
		x = checkBorders(x, y);
		spr.setPosition(x, y);
	}
	
	public void disparar (float x, float y) {
		bullets.crearBala(x + spr.getWidth() / 2 - 5,
				y + spr.getHeight() - 5, 0, 5);
	}
	
	public void draw(SpriteBatch batch, PantallaJuego juego) {
		float x = spr.getX();
		float y = spr.getY();
		setPos(spr, x, y);
		spr.draw(batch);
		// Disparar si se oprime el botón y si la cantidad de balas por pantalla es menor a 4.
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && bullets.getSize() < 4) {
			//System.out.println(t);
			disparar(x, y);
		}
	}

	public boolean estaDestruido() {
		return derrota;
	}

	public int getVidas() {
		return vidas;
	}

	public int getX() {
		return (int) spr.getX();
	}

	public int getY() {
		return (int) spr.getY();
	}

	public void setVidas(int vidas2) {
		vidas = vidas2;
	}
}
