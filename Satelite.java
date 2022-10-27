package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Satelite extends AbstractJunkObject {
	
	
	public Satelite(int x, int y, int xSpeed, int ySpeed, int size, Texture tx) {
		super(x,y,xSpeed,ySpeed,size,tx);
		setVida(2);
	}
	
	public void update() {
		
		//actualizar movimiento X
		setX(getX() - getXSpeed());
		
		//actualizar movimiento en eje Y
		double k = Math.random();
		
		if (k < 0.5) {
			//mover hacia abajo
			setY(getY() - getXSpeed());
		}else {
			if (k > 0.5) {
				//mover hacia arriba
				setY(getY() + getXSpeed());
			}else {
				//mantener posicion
				setY(getY());
			}
		}
		
		//manejo de colision X
		if (getX() < - bordeDer || getX()+bordeDer > bordeDer)
        	this.destroyed();
		
		
	
        this.setPos(getX(), getY());
		
	}

	@Override
	public float checkBorders(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float mover(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPos(Sprite spr, float x, float y) {
		// TODO Auto-generated method stub
		
	}
}
