package bibliotheque;

import exceptions.*;

public interface IDocument {
	
	//renvoie le numero du document
	int numero();
		
	//renvoie le nom du document
	String nom(); 
	
	//enregistre que le document est reserve par l abonne ab
	void reserver(IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException, TropJeuneException;

	//enregistre que le document est emprunte par l abonne ab
	void emprunter(IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException, TropJeuneException;

	//enregistre que le document est disponible
	void rendreDispo() throws DejaLibreException;
	
	//verifie si le livre est dispo
	boolean estDisponible();
	
	//verifie si le livre est reserve par ab
	boolean estReserve(IUtilisateur ab);
	
	//verifie si le livre est emprunte par ab
	boolean estEmprunte(IUtilisateur ab);
	
	//verifie si un autre utilisateur veut reserver le livre
	boolean hasNext();
	
	//recupere le prochain utilisateur qui veut un livre
	IUtilisateur next();
	
}