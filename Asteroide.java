package objectsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import abstractInterfacePackage.AbstractJunkObject;

public class Asteroide extends AbstractJunkObject {

    // Atributo específico
    private Sprite spr;

    // Constructor
    public Asteroide(int x, int y, int xSpeed, Texture tx) {
        super(x, y, xSpeed);
        spr = new Sprite(tx);
        spr.setX(x);
        spr.setY(y);
    }

    // Obtener el área/superficie en el que está el sprite de un objeto.
    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }

    // Dibujar el objeto en el batch.
    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }

    // Actualizar el movimiento del objeto.
    public void update() {
        float x = spr.getX();
        float y = spr.getY();
        this.setPos(spr, x, y);
    }

    @Override
    // Verificar si la entidad sobrepasa los límites de la pantalla.
    public float checkBorders(float x, float y) {
        // TODO Auto-generated method stub
        if (x - spr.getWidth() / 2 > bordeDer) {
            this.setDestroyed();
            this.setOut();
        }
        return x;

    }
    @Override
    // Cambiar los valores de posición de la entidad.
    public float moverObjeto(float x) {
        return mover(x,getXSpeed());
    }

    @Override
    // Colocar en los nuevos valores de posición a la entidad.
    public void setPos(Sprite spr, float x, float y) {
        // TODO Auto-generated method stub
        x = moverObjeto(x);
        x = checkBorders(x, y);
        spr.setPosition(x, y);
    }
}