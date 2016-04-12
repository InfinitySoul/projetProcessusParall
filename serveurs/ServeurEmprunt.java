package serveurs;

import java.net.ServerSocket;
import java.io.IOException;

import bibliotheque.Bibliotheque;
import services.ServiceEmprunt;

public class ServeurEmprunt implements Runnable {

	private ServerSocket listen_socket;
	private Bibliotheque biblio;

	public ServeurEmprunt(int port, Bibliotheque b) throws IOException {
		listen_socket = new ServerSocket(port);
		biblio = b;
	}

	public void run() {
		try {
			while (true)
				new ServiceEmprunt(listen_socket.accept(), biblio).lancer();
		} catch (IOException e) {
			try {
				this.listen_socket.close();
			} catch (IOException e1) {
				
			}
			System.err.println("Pb sur le port d'ecoute :" + e);
		}
	}

	public void lancer() {
		(new Thread(this)).start();
	}
	
}
