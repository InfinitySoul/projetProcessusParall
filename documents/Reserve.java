package documents;

import bibliotheque.IUtilisateur;
import exceptions.*;

public class Reserve implements EtatLivre {

	private IUtilisateur ut;

	public Reserve(IUtilisateur u) {
		ut = u;
	}

	public void reserver(Document d, IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException {
		if(ab.estInterditEmprunt())
			throw new InterditEmpruntException();
		if(ut == ab)
			throw new MemeUtilisateurException();
		d.enAttente(ab);
		throw new PasLibreException();	
	}

	public void emprunter(Document d, IUtilisateur ab) throws InterditEmpruntException, PasLibreException {
		if(ab.estInterditEmprunt())
			throw new InterditEmpruntException();
		if(ut != ab)
			throw new PasLibreException();
		d.setEtat(new Emprunte(ab));
	}

	public void rendreDispo(Document d) {
		d.setEtat(new Disponible());
	}

	public boolean estDisponible() {
		return false;
	}

	public boolean estReserve(IUtilisateur ab) {
		return ut == ab;
	}

	public boolean estEmprunte(IUtilisateur ab) {
		return false;
	}

}
