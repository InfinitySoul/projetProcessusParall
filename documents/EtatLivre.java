package documents;

import bibliotheque.IUtilisateur;
import exceptions.*;

public interface EtatLivre {

	void reserver(Document document, IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException;

	void emprunter(Document document, IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException;

	void rendreDispo(Document document) throws DejaLibreException;
	
	boolean estDisponible();
	
	boolean estReserve(IUtilisateur ab);
	
	boolean estEmprunte(IUtilisateur ab);
 
}
