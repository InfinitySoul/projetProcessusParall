package appliserveur;

import java.io.IOException;

import bibliotheque.*;
import documents.*;
import utilisateurs.*;
import serveurs.*;

public class Appli {

	private final static int PORT_RESERVATION = 2500;
	private final static int PORT_EMPRUNT = 2600;
	private final static int PORT_RETOUR = 2700;

	public static void main(String[] args) {
		Bibliotheque b = new Bibliotheque();
		
		IDocument[] ld = {
				new Livre("Le mystere de la chambre jaune", "son auteur"),
				new Livre("Le parfum de la dame en noir", "son auteur"),
				new DVD("League of legend > ALL", 16)
		};
		for (IDocument d : ld)
			b.addDocument(d);

		IUtilisateur[] lu = { 
				(IUtilisateur) new Abonne("Nicolas", 5),
				(IUtilisateur) new Abonne("Sami", 19) 
		};
		for (IUtilisateur u : lu)
			b.addUser(u);

		try {
			
			new ServeurReservation(PORT_RESERVATION, b).lancer();
			System.out.println("Serveur Reservation se lance sur le port " + PORT_RESERVATION);

			new ServeurEmprunt(PORT_EMPRUNT, b).lancer();
			System.out.println("Serveur Emprunt se lance sur le port " + PORT_EMPRUNT);

			new ServeurRetour(PORT_RETOUR, b).lancer();
			System.out.println("Serveur Retour se lance sur le port " + PORT_RETOUR);
			
		} catch (IOException e) {
			System.err.println("Pb lors de la creation du serveur : " + e);
		}
	}
	
}