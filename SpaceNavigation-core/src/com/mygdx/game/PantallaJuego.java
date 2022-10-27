package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int velXAsteroides;
	private int velYAsteroides;
	private int cantAsteroides;
	private int contAsteroides = 0;
	private float time = 0;
	private float spawnTime = 0.8f;

	private Nave nave;
	private ObjetosBasura trash = new ObjetosBasura();
	private GrupoBalas balas = new GrupoBalas();

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, int velXAsteroides, int velYAsteroides,
			int cantAsteroides) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;
		this.velXAsteroides = velXAsteroides;
		this.velYAsteroides = velYAsteroides;
		this.cantAsteroides = cantAsteroides;

		batch = game.getBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);
		// inicializar assets; musica de fondo y efectos de sonido.
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1, 0.5f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //

		gameMusic.setLooping(true);
		gameMusic.setVolume(0.2f);
		gameMusic.play();

		// cargar imagen de la nave, 64x64
		nave = new Nave(Gdx.graphics.getWidth() / 2 - 50, 30, new Texture(Gdx.files.internal("MainShip3.png")), balas);
		nave.setVidas(vidas);
	}

	public void dibujaEncabezado() {
		CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
		game.getFont().getData().setScale(2f);
		game.getFont().draw(batch, str, 10, 30);
		game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth() - 150, 30);
		game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth() / 2 - 100, 30);
	}

	@Override
	public void render(float delta) {
		// Preparación.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		dibujaEncabezado();

		// Crear Asteroides
		time += delta;
		//System.out.println(time);
		if (time >= spawnTime) {
			// Crear basura.
			trash.generarBasura();
			contAsteroides++;
			time = 0;
		}
		
		//Actualizar movimientos.
		balas.updateBullets(batch);
		trash.updateMovement(batch);
		nave.draw(batch, this);
		
		// Revisar las colisiones entre balas y objetos.
		trash.checkColisionBala(batch, balas);
		
		//Revisar si el jugador perdió.
		if (nave.estaDestruido()) {
			if (score > game.getHighScore())
				game.setHighScore(score);
			Screen ss = new PantallaGameOver(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
		batch.end();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}

}
