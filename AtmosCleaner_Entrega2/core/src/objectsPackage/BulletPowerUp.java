package objectsPackage;

import abstractInterfacePackage.Strategy;

public class BulletPowerUp implements Strategy{
	//Métodos
	public void executePowerUp (Nave nave) {
		nave.changeBulletsLimit(10);
	}
	public void disablePowerUp (Nave nave) {
		nave.changeBulletsLimit(5);
	}
}
