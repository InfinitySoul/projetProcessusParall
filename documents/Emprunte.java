package documents;

import bibliotheque.IUtilisateur;
import exceptions.*;

public class Emprunte implements EtatLivre {

	private IUtilisateur ut;
	
	public Emprunte(IUtilisateur ab) {
		ut = ab;
	}
	
	public void reserver(Document document, IUtilisateur ab) throws InterditEmpruntException, PasLibreException {
		if(ab.estInterditEmprunt())
			throw new InterditEmpruntException();
		document.enAttente(ab);
		throw new PasLibreException();
	}

	public void emprunter(Document document, IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException {
		if(ab.estInterditEmprunt())
			throw new InterditEmpruntException();
		if(ut == ab) throw new MemeUtilisateurException();
		throw new PasLibreException();
	}
	
	public void rendreDispo(Document d) {
		d.setEtat(new Disponible());
	}

	public boolean estDisponible() {
		return false;
	}

	public boolean estReserve(IUtilisateur ab) {
		return false;
	}

	public boolean estEmprunte(IUtilisateur ab) {
		return ut == ab;
	}

}
