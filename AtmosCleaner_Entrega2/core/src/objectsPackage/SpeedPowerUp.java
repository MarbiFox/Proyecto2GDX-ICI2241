package objectsPackage;

import abstractInterfacePackage.Strategy;

public class SpeedPowerUp implements Strategy {
	//Métodos
	public void executePowerUp (Nave nave) {
		nave.changeSpeed(10);
	}
	public void disablePowerUp (Nave nave) {
		nave.changeSpeed(5);
	}
}
