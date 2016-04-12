package timers;

import java.util.Timer;
import java.util.TimerTask;

import bibliotheque.IUtilisateur;

public class InterdictionEmprunt extends TimerTask {

	private IUtilisateur ut;
	private Timer timer;
	
	public InterdictionEmprunt(IUtilisateur ab, Timer t) {
		ut = ab;
		timer = t;
	}

	public void run() {
		ut.libererEmprunt();
		timer.cancel();
	}

}
