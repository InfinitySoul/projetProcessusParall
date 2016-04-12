package timers;

import java.util.Timer;
import java.util.TimerTask;

import bibliotheque.IDocument;
import bibliotheque.IUtilisateur;

public class RetardEmprunt extends TimerTask {

	private static final int TEMPS_INTERDICTION = 2100000000;
	//1 mois = 2 592 000 000 ms est un long...
	
	private IDocument doc;
	private IUtilisateur ut;
	private Timer timer;
	
	public RetardEmprunt(IDocument d, IUtilisateur ab, Timer t) {
		doc = d;
		ut = ab;
		timer = t;
	}

	public void run() {
		synchronized(doc) {
			if(doc.estEmprunte(ut)) { //si toujours emprunte par le meme utilisateur
				ut.interdireEmprunt();
				Timer timerInterdiction = new Timer();
				timerInterdiction.schedule(new InterdictionEmprunt(ut, timerInterdiction), TEMPS_INTERDICTION);
			}
		}
		timer.cancel();
	}

}
