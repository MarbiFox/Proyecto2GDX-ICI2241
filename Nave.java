package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.MathUtils;

public class Nave implements Movible {

	private boolean destruida = false;
	private int vidas = 3;
	private int movementModifier = 3;
	private Sprite spr;
	private Sound soundBala;
	private Texture txBala;
	private boolean herido = false;

	public Nave(int x, int y, Texture tx, Texture txBala, Sound soundBala) {
		this.soundBala = soundBala;
		this.txBala = txBala;
		spr = new Sprite(tx);
		spr.setPosition(x, y);
		spr.setBounds(x, y, 45, 45);
	}

	public float mover(float x) {
		// Mover con teclado.
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			x = x - movementModifier;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			x = x + movementModifier;
		}
		return x;
		
	}

	public float keepInBorder(float x) {
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
		x = mover(x);
		x = keepInBorder(x);
		spr.setPosition(x, y);
	}

	public void draw(SpriteBatch batch, PantallaJuego juego) {
		float x = spr.getX();
		float y = spr.getY();
		setPos(spr, x, y);
		spr.draw(batch);

		// disparo
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Bullet bala = new Bullet(spr.getX() + spr.getWidth() / 2 - 5, spr.getY() + spr.getHeight() - 5, 0, 5,
					txBala);
			juego.agregarBala(bala);
			soundBala.play();
		}

	}

	public boolean checkCollision(Ball2 b) {
		return true;
	}

	public boolean estaDestruido() {
		return !herido && destruida;
	}

	public boolean estaHerido() {
		return herido;
	}

	public int getVidas() {
		return vidas;
	}

	// public boolean isDestruida() {return destruida;}
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
