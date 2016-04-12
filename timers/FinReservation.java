package timers;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import bibliotheque.*;
import exceptions.DejaLibreException;

public class FinReservation extends TimerTask {

	private Bibliotheque biblio;
	private IDocument doc;
	private IUtilisateur ut;
	private Timer timer;

	public FinReservation(Bibliotheque b, IDocument d, IUtilisateur ab, Timer t) {
		biblio = b;
		doc = d;
		ut = ab;
		timer = t;
	}

	public void run() {
		synchronized (doc) {
			if (doc.estReserve(ut))
				try {
					doc.rendreDispo();
					if(doc.hasNext())
						try {
							biblio.notifier(doc);
						} catch (FileNotFoundException e) {
							
						}
				} catch (DejaLibreException e) {
					
				}
		}
		timer.cancel();
	}

}
