package applireservation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AppliReservation {

	private final static String ADR_IP_BIBLIO = "localhost";

	private final static int PORT_RESERVATION = 2500;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket(ADR_IP_BIBLIO, PORT_RESERVATION);
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter socketOut = new PrintWriter(s.getOutputStream(), true);
		
		Scanner clavier = new Scanner(System.in);
		
		System.out.println("Numero d'abonne :");
		int numAbonne = clavier.nextInt();
		System.out.println("Numero du document a reserver (tapez 0 pour quitter) :");
		int numDoc = clavier.nextInt();
		
		while(numDoc != 0){
			socketOut.println(numAbonne);
			socketOut.println(numDoc);
			System.out.println(socketIn.readLine());
			
			System.out.println("Numero du document a reserver (tapez 0 pour quitter) :");
			numDoc = clavier.nextInt();
		}
		
		s.close();
		clavier.close();
	}
	
}
