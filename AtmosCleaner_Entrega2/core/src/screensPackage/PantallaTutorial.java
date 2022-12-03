package screensPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AtmosCleaner;

public class PantallaTutorial implements Screen {
	// Atributos
	private AtmosCleaner game;
	private OrthographicCamera camera;

	// Constructor
	public PantallaTutorial(AtmosCleaner game) {
		this.game = game;
		//Preparar cámara
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}
	
	private void mostrarTutorial (SpriteBatch batch) {
		// Título
		game.getFont().getData().setScale(2f);
		game.getFont().draw(game.getBatch(), "Tutorial", 550, 775);
		
		// Mensaje Inicial
		game.getFont().getData().setScale(1.5f);
		String story = "Eres un astronauta cuyo deber es acabar con la basura que se encuentra en la atmósfera terrestre.\n"
				+ "Para cumplir tu objetivo tendrás que utilizar tu nave para dispararle a los desechos flotantes.";
		game.getFont().draw(game.getBatch(), story, 100, 725);
		
		// Control Nave
		String nave = "Usa las teclas izquierda y derecha para\n"
				+ "mover tu nave a través de la órbita.";
		game.getFont().draw(game.getBatch(), nave, 100, 600);
		Sprite storySpr = new Sprite(new Texture(Gdx.files.internal("Tutorial1.png")));
		storySpr.setPosition(630, 525);
		storySpr.draw(batch);
		
		// Control Bala
		String bala = "Usa la SpaceBar para disparar. ¡Cuidado!\n"
				+ "Sólo puedes disparar 5 balas a la vez.";
		game.getFont().draw(game.getBatch(), bala, 100, 450);
		Sprite balaSpr = new Sprite(new Texture(Gdx.files.internal("Tutorial2.png")));
		balaSpr.setPosition(725, 375);
		balaSpr.draw(batch);
		
		// Objetos
		String obj = "Habrán objetos basura flotando en distinto\n"
				+ "sentido. ¡Dales con una bala para destruirlos!\n"
				+ "Si se salen de la órbita se acumularán como basura.";
		game.getFont().draw(game.getBatch(), obj, 100, 300);
		Sprite objSpr = new Sprite(new Texture(Gdx.files.internal("Tutorial3.png")));
		objSpr.setPosition(700, 220);
		objSpr.draw(batch);
		
		// Objetivo
		String lose = "Si acumulas 20 objetos basura, ¡Perderás! Evita a toda costa que\n"
				+ "los objetos se salgan y destruye la mayor cantidad de basura posible.";
		game.getFont().draw(game.getBatch(), lose, 250, 150);
		
		// Final
		game.getFont().draw(game.getBatch(), "Presiona SpaceBar para comenzar tu misión.", 350, 75);
	}

	@Override
	public void render(float delta) {
		// Limpiar pantalla
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		// Configurar cámara
		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);
		
		// Iniciar y mostrar tutorial
		game.getBatch().begin();
		mostrarTutorial(game.getBatch());
		
		// Terminar batch
		game.getBatch().end();
		
		// Verificar si se pulsó un botón
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			Screen ss = new PantallaJuego(game, 0);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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

	}

}