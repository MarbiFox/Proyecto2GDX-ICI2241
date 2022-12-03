package screensPackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.AtmosCleaner;

public class PantallaMenu implements Screen {
	// Atributos
	private AtmosCleaner game;
	private OrthographicCamera camera;

	// Constructor
	public PantallaMenu(AtmosCleaner game) {
		this.game = game;
		//Preparar cámara
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}

	@Override
	public void render(float delta) {
		// Limpiar pantalla
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		// Configurar cámara
		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);
		
		// Iniciar y mostrar mensaje
		game.getBatch().begin();
		game.getFont().draw(game.getBatch(), "Bienvenido a AtmosCleaner !", Gdx.graphics.getWidth() / 2 - 200,
				Gdx.graphics.getHeight() / 2 + 50);
		game.getFont().draw(game.getBatch(), "Presiona cualquier tecla para comenzar ...",
				Gdx.graphics.getWidth() / 2 - 280, Gdx.graphics.getHeight() / 2 - 50);
		
		// Terminar batch
		game.getBatch().end();
		
		// Verificar si se pulsó un botón
		if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaTutorial(game);
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