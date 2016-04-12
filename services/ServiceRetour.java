package services;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import bibliotheque.Bibliotheque;
import exceptions.UtilisateurDeconnecteException;

public class ServiceRetour implements Runnable {
	
	private Socket client;
	private Thread thread;
	private Bibliotheque biblio;
	
	public ServiceRetour(Socket socket, Bibliotheque b) {
		this.client = socket;
		this.thread = new Thread(this);
		biblio = b;
	}

	public void run() {
		System.out.println("Bienvenue dans le service retour " + this.client.getInetAddress());
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			while (true) {
				String stringNumLivre = in.readLine();
				if(stringNumLivre == null)
					throw new UtilisateurDeconnecteException();
				int numLivre = Integer.parseInt(stringNumLivre);
				System.out.println("numero du document recu : " + numLivre);
				synchronized(biblio) {
					out.println(biblio.rendreLivre(numLivre)); //affichage du message
				}
			}
		}
		catch (Exception e) {
			System.err.println("Fin du service retour : " + e);
		}
		try {client.close();} catch (IOException e2) {}
	}

	public void lancer() {	
		thread.start();		
	}
	
}
