package objectsPackage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import abstractInterfacePackage.Strategy;

public class Timer {
	// Atributos
	private float initTime;
	private float limiter;
	private PowerUp pp;
	private boolean isCounting;

	// Constructor
	public Timer(PowerUp pp) {
		this.initTime = 0;
		this.limiter = 5;
		this.pp = pp;
		this.isCounting = false;
	}

	// --- Métodos ---

	// Checkear si el PowerUp se pasó del tiempo.
	public boolean checkPowerUpTime(float time) {
		if (time - initTime > limiter) {
			return true;
		}
		return false;
	}
	
	//Crear PowerUp (Builder) 
	public void createPowerUp() {
		// Crear el director.
		PowerUpDirector d = new PowerUpDirector();
		// Crear el Builder Genérico.
		PowerUpBuilder b = new PowerUpBuilder();
		// Modificar atributos según el PowerUp.
		Strategy str;
		if (Math.random() > 0.5) {
			d.createSpeedPowerUp(b);
			str = new SpeedPowerUp();

		} else {
			d.createBulletPowerUp(b);
			str = new BulletPowerUp();
		}
		pp = b.getPowerUp();
		pp.setStrategy(str); //Strategy
	}
	
	// Actualizar el movimiento del PowerUp.
	public void updatePowerUp(SpriteBatch batch) {
		// Actualizar Movimientos.
		if (pp != null && this.isCounting == false) {
			pp.update();
			pp.draw(batch);
			if (pp.getIsDestoyed() == true) {
				pp = null;
			}
		}
	}
	
	// Checkear si el PowerUp colisiona con una bala.
	public void checkPowerUp(GrupoBalas balas, Nave nave, float currTime) {
		if (pp != null && this.isCounting == false) {
			boolean flag = pp.checkCollision(balas, nave);
			if (flag == true) {
				this.isCounting = true;
				this.initTime = currTime;
			}
		}
	}
	
	//Regresar la nave a la normalidad.
	public void getToNormal(Nave nave) {
		pp.getToNormal(nave);
	}
	
	public boolean getIsCounting() {
		return isCounting;
	}
	
	public void setIsCounting(boolean b) {
		this.isCounting = b;
	}
	
	public PowerUp getPp() {
		return pp;
	}
	
	public void setPp(PowerUp pp) {
		this.pp = pp;
	}

}
