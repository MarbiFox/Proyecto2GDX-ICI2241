package screensPackage;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AtmosCleaner;

import abstractInterfacePackage.Strategy;
import objectsPackage.BulletPowerUp;
import objectsPackage.GrupoBalas;
import objectsPackage.Nave;
import objectsPackage.ObjetosBasura;
import objectsPackage.PowerUp;
import objectsPackage.PowerUpBuilder;
import objectsPackage.PowerUpDirector;
import objectsPackage.SpeedPowerUp;

public class PantallaJuego implements Screen {
	
	// Atributos.
	private AtmosCleaner game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Music gameMusic;
	private int score;
	private float time = 0;
	private float spawnTime = 1.25f;
	private Texture tx;
	private Nave nave;
	private ObjetosBasura trash = new ObjetosBasura();
	private GrupoBalas balas = new GrupoBalas();
	private PowerUp currPowerUp = null;
	
	// Constructor.
	public PantallaJuego(AtmosCleaner game, int score) {
		// Obtener valores.
		this.game = game;
		this.score = score;
		batch = game.getBatch();
		
		// Crear Cámara.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);
		
		// Inicializar música de fondo.
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("level1.ogg")); //
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.8f);
		gameMusic.play();

		// Inicializar nave.
		nave = new Nave(Gdx.graphics.getWidth() / 2 - 50, 30, new Texture(Gdx.files.internal("ship.png")), balas);
	}
	
	// Crear Fondo
	public void crearFondo() {
		// Dibujar fondo.
		batch.draw(tx,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		// Ajustar Letra.
		game.getFont().getData().setScale(2f);
		// Dibujar información.
		game.getFont().draw(batch, "Score:" + this.score, Gdx.graphics.getWidth() - 180, 790);
		game.getFont().draw(batch, "HighScore:" + game.getHighScore(), Gdx.graphics.getWidth() / 2 - 80, 790);
		game.getFont().draw(batch, "Basura Acumulada:" + trash.getCantBasura() + "/20", 10, 790);
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
		
		// Crear PowerUp.
		if (Math.random() >= 0.95 && Math.random() >= 0.95) {
			System.out.println("OLA");
			// Crear el director.
			PowerUpDirector d = new PowerUpDirector();
			// Crear el Builder Genérico.
			PowerUpBuilder b = new PowerUpBuilder();
			// Modificar atributos según el PowerUp.
			Strategy str;
			if (Math.random() > 0.5) {
				d.createSpeedPowerUp(b);
				System.out.println("OH NO");
				str = new SpeedPowerUp();
				
			}
			else {
				d.createBulletPowerUp(b);
				str = new BulletPowerUp();
			}
			currPowerUp = b.getPowerUp();
			currPowerUp.setStrategy(str);
			currPowerUp.hacerAlgo();
			currPowerUp.execute(nave);
		}
		
		// Actualizar Movimientos.
		balas.updateBullets(batch);
		trash.updateMovement(batch);
		nave.draw(batch, this);
		
		// Revisar las colisiones entre balas y objetos.
		score += trash.checkColisionBala(batch, balas);
		
		// Revisar si el jugador perdió.
		if (trash.getCantBasura() >= 20) {
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
