package documents;

import java.util.LinkedList;

import bibliotheque.IDocument;
import bibliotheque.IUtilisateur;
import exceptions.*;

public abstract class Document implements IDocument {
	
	private static int nombreDocuments = 0;

	private int numero;
	private String nom;
	private EtatLivre etat;
	private LinkedList<IUtilisateur> listeAttente;
	
	public Document(String nom) {
		numero = ++nombreDocuments;
		this.nom = nom;
		etat = new Disponible();
		listeAttente = new LinkedList<IUtilisateur>();
	}

	public int numero() {
		return numero;
	}

	public String nom() {
		return this.nom;
	}

	public void reserver(IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException, TropJeuneException {
		etat.reserver(this, ab);
		if(listeAttente.contains(ab))
			listeAttente.remove(ab);
	}

	public void emprunter(IUtilisateur ab) throws InterditEmpruntException, MemeUtilisateurException, PasLibreException, TropJeuneException {
		etat.emprunter(this, ab);
		if(listeAttente.contains(ab))
			listeAttente.remove(ab);
	}

	public void rendreDispo() throws DejaLibreException {
		etat.rendreDispo(this);
	}

	public boolean estDisponible() {
		return etat.estDisponible();
	}
	
	public boolean estReserve(IUtilisateur ab) {
		return etat.estReserve(ab);
	}
	
	public boolean estEmprunte(IUtilisateur ab) {
		return etat.estEmprunte(ab);
	}
	
	public boolean hasNext() {
		return listeAttente.size() > 0;
	}
	
	public IUtilisateur next() {
		IUtilisateur prochain = listeAttente.getFirst();
		listeAttente.removeFirst();
		return prochain;
	}
	
	public void enAttente(IUtilisateur ab) {
		if(!listeAttente.contains(ab))
			listeAttente.add(ab);
	}
	
	public void setEtat(EtatLivre e) {
		etat = e;
	}
	
}
