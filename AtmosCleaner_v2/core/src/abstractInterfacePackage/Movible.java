package abstractInterfacePackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

//Interface que se encarga de definir valores y funciones clave
//para las entidades que se mueven en el juego.
public interface Movible {
	// Valores de bordes de pantalla.
	public static final int bordeIzq = 0;
	public static final int bordeDer = Gdx.graphics.getWidth();
	public static final int bordeSup = Gdx.graphics.getHeight();
	// Verificar si la entidad sobrepasa los límites de la pantalla.
	public float checkBorders (float x, float y); 
	// Cambiar los valores de posición de la entidad.
	public float mover (float x, float y);
	// Colocar en los nuevos valores de posición a la entidad.
	public void setPos (Sprite spr, float x, float y);
}
