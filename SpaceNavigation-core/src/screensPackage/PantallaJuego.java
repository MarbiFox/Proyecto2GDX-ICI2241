package screensPackage;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceNavigation;

import objectsPackage.GrupoBalas;
import objectsPackage.Nave;
import objectsPackage.ObjetosBasura;

public class PantallaJuego implements Screen {
	//Atributos.
	private SpaceNavigation game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Music gameMusic;
	private int score;
	private float time = 0;
	private float spawnTime = 1.2f;
	private Texture tx;
	private Nave nave;
	private ObjetosBasura trash = new ObjetosBasura();
	private GrupoBalas balas = new GrupoBalas();

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, int cantAsteroides) {
		// Obtener valores.
		this.game = game;
		this.score = score;
		batch = game.getBatch();
		
		// Crear Cámara.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);
		
		// Inicializar música de fondo.
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav")); //
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.2f);
		gameMusic.play();

		// Inicializar nave.
		nave = new Nave(Gdx.graphics.getWidth() / 2 - 50, 30, new Texture(Gdx.files.internal("MainShip3.png")), balas);
		nave.setVidas(vidas);
	}

	public void crearFondo() {
		// Dibujar fondo.
		batch.draw(tx,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		// Ajustar Letra.
		game.getFont().getData().setScale(2f);
		// Dibujar información.
		game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth() - 150, 795);
		game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth() / 2 - 100, 795);
		game.getFont().draw(batch, "Basura Acumulada:" + trash.getCantBasura(), 10, 795);
	}
	
	@Override
	public void render(float delta) {
		// Preparación y Limpieza de Batch.
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		// Dibujar Fondo.
		crearFondo();

		// Crear Asteroides
		time += delta;
		if (time >= spawnTime) {
			// Crear Basura.
			trash.generarBasura();
			time = 0;
		}
		
		// Actualizar Movimientos.
		balas.updateBullets(batch);
		trash.updateMovement(batch);
		nave.draw(batch, this);
		
		// Revisar las colisiones entre balas y objetos.
		score += trash.checkColisionBala(batch, balas);
		
		// Revisar si el jugador perdió.
		if (trash.getCantBasura() >= 100) {
			if (score > game.getHighScore())
				game.setHighScore(score);
			Screen ss = new PantallaGameOver(game);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
		
		//Terminar Batch.
		batch.end();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
		tx = new Texture(Gdx.files.internal("fondo1.png"));
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
		this.gameMusic.dispose();
		this.tx.dispose();
	}
	
	
	//CharSequence str = "Vidas: " + nave.getVidas() + " Ronda: " + ronda;
	//game.getFont().draw(batch, str, 10, 30);

}
