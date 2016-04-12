package bibliotheque;

import java.util.ArrayList;
import java.util.Timer;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import timers.*;
import exceptions.*;

public class Bibliotheque {

	private static final int TEMPS_MAX_RESERVATION = 18000000;  //cinq heures
	private static final int TEMPS_MAX_EMPRUNT = 1209600000;  //deux semaines
	
	private ArrayList<IDocument> listeDocuments;
	private ArrayList<IUtilisateur> listeAbonnes;
	
	public Bibliotheque() {
		listeDocuments = new ArrayList<IDocument>();
		listeAbonnes = new ArrayList<IUtilisateur>();
	}
	
	public void addDocument(IDocument d) {
		listeDocuments.add(d);
	}
	
	public void addUser(IUtilisateur u) {
		listeAbonnes.add(u);
	}
	
	public String emprunterLivre(int numeroDocument, int numeroAbonne) {
		IDocument d = this.getDocument(numeroDocument);
		if (d == null)
			return "Numero document inconnu";
		IUtilisateur ab = this.getAbonne(numeroAbonne);
		if (ab == null)
			return "Numero abonne inconnu";
		try {
				d.emprunter(ab);
				Timer timer = new Timer();
				timer.schedule(new RetardEmprunt(d, ab, timer), TEMPS_MAX_EMPRUNT);
		} catch (PasLibreException e) {
			return "Document non disponible";
		} catch (MemeUtilisateurException e1) {
			return "Vous avez deja emprunte ce document.";
		} catch (InterditEmpruntException e) {
			return "Vous etes interdit d'emprunt";
		} catch (TropJeuneException e) {
			return "Vous etes trop jeune pour emprunter ce DVD";
		}
		return "Le document a bien ete emprunte";
	}

	public String reserverLivre(int numeroDocument, int numeroAbonne) {
		IDocument d = this.getDocument(numeroDocument);
		if (d == null)
			return "Numero document inconnu";
		IUtilisateur ab = this.getAbonne(numeroAbonne);
		if (ab == null)
			return "Numero abonne inconnu";
		try {
				d.reserver(ab);
				Timer timer = new Timer();
				timer.schedule(new FinReservation(this, d, ab, timer), TEMPS_MAX_RESERVATION);
		} catch (PasLibreException e) {
			return "Document non disponible";
		} catch (MemeUtilisateurException e1) {
			return "Vous avez deja reserve ce document.";
		} catch (InterditEmpruntException e) {
			return "Vous etes interdit d'emprunt";
		} catch (TropJeuneException e) {
			return "Vous etes trop jeune pour emprunter ce DVD";
		}
		return "Le document a bien ete reserve";
	}

	public String rendreLivre(int numeroDocument) throws FileNotFoundException {
		IDocument d = getDocument(numeroDocument);
		if (d == null)
			return "Numero document inconnu";
		try {
			d.rendreDispo();
			if(d.hasNext())
				notifier(d);
		} catch (DejaLibreException e) {
			return "Ce document est deja rendu.";
		}
		return "Document rendu";
	}
	
	public void notifier(IDocument d) throws FileNotFoundException {
		PrintWriter file = new PrintWriter("notification a " + d.next().getNom() + ".txt");
		file.println(d.nom() + " est disponible.");
		file.close();
	}

	/**
	 * 
	 * @param numeroDocument
	 *            /numeroAbonne Permet de savoir si le numero de livre/d'abonne
	 *            est correct
	 */
	public boolean numeroDocumentExiste(int numeroDocument) {
		return getDocument(numeroDocument) != null;
	}

	public boolean numeroAbonneExiste(int numeroAbonne) {
		return getAbonne(numeroAbonne) != null;
	}

	public IDocument getDocument(int numeroDocument) {
		for (IDocument d : listeDocuments) {
			if (d.numero() == numeroDocument)
				return d;
		}
		return null;
	}

	public IUtilisateur getAbonne(int numeroAbonne) {
		for (IUtilisateur a : listeAbonnes) {
			if (a.getNumero() == numeroAbonne)
				return a;
		}
		return null;
	}
	
}
