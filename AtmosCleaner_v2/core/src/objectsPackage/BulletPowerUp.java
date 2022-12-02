package objectsPackage;

import abstractInterfacePackage.Strategy;

public class BulletPowerUp implements Strategy{
	//Método
	public void executePowerUp (Nave nave) {
		nave.changeSpeed(3);
	}
}
