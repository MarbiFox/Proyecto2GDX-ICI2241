package screensPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AtmosCleaner;

import objectsPackage.GrupoBalas;
import objectsPackage.Nave;
import objectsPackage.ObjetosBasura;
import objectsPackage.Timer;

public class PantallaJuego implements Screen {
	
	// Atributos.
	private AtmosCleaner game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Music gameMusic;
	private int score;
	private float timeObjects = 0;
	private float timePowerUp = 0;
	private float spawnTime = 1.25f;
	private Texture tx;
	private Nave nave;
	private ObjetosBasura trash = new ObjetosBasura();
	private GrupoBalas balas = new GrupoBalas();
	private Timer timer = null;
	
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
        nave = Nave.getNave(Gdx.graphics.getWidth() / 2 - 50, 30,new Texture(Gdx.files.internal("ship.png")), balas);
		
		// Inicializar Timer.
		timer = new Timer(null);
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
		
		// Actualizar tiempo de juego.
		timeObjects += delta;
		timePowerUp += delta;
		
		// Crear Asteroides
		if (timeObjects >= spawnTime) {
			// Crear Basura.
			trash.generarBasura();
			timeObjects = 0;
		}
		
		// Crear PowerUp.
		if (Math.random() >= 0.95 && Math.random() >= 0.50 && timer.getPp() == null && timer.getIsCounting() == false) {
			timer.createPowerUp();
		}
		
		// Actualizar Movimientos.
		if (timer != null) {
			timer.updatePowerUp(batch);
		}
		balas.updateBullets(batch);
		trash.updateMovement(batch);
		nave.draw(batch, this);
		
		// Revisar las colisiones entre balas y objetos.
		score += trash.checkColisionBala(batch, balas);
		if (timer != null) {
			timer.checkPowerUp(balas, nave, timePowerUp);
		}
		
		// Revisar si se acabó el PowerUp se acabó (si es que está activo).
		if (timer != null && timer.getIsCounting() == true) {
			if (timer.checkPowerUpTime(timePowerUp)) {
				timer.getToNormal(nave);
				timer.setIsCounting(false);
				timer.setPp(null);
			}
		}
		
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
}
