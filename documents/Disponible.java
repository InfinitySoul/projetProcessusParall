package documents;

import bibliotheque.IUtilisateur;
import exceptions.*;

public class Disponible implements EtatLivre {

	public void reserver(Document d, IUtilisateur u) throws InterditEmpruntException {
		if(u.estInterditEmprunt())
			throw new InterditEmpruntException();
		d.setEtat(new Reserve(u));
	}

	public void emprunter(Document d, IUtilisateur u) throws InterditEmpruntException {
		if(u.estInterditEmprunt())
			throw new InterditEmpruntException();
		d.setEtat(new Emprunte(u));
	}

	public void rendreDispo(Document d) throws DejaLibreException {
		throw new DejaLibreException();
	}

	public boolean estDisponible() {
		return true;
	}

	public boolean estReserve(IUtilisateur ab) {
		return false;
	}

	public boolean estEmprunte(IUtilisateur ab) {
		return false;
	}
}
