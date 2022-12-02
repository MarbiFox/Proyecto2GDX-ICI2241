package objectsPackage;

import abstractInterfacePackage.Strategy;

public class SpeedPowerUp implements Strategy {
	//Método
	public void executePowerUp (Nave nave) {
		nave.changeSpeed(10);
	}
}
