package utilisateurs;

import bibliotheque.IUtilisateur;

public class Abonne implements IUtilisateur {
	
	private static int nombreAbonnes = 0;

	private int numero;
	private String nom;
	private boolean interditEmprunt;
	private int age;
	
	public Abonne(String nom, int a) {
		this.numero = ++nombreAbonnes;
		this.nom = nom;
		interditEmprunt = false;
		age = a;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public String getNom() {
		return this.nom;
	}

	public void interdireEmprunt() {
		interditEmprunt = true;
	}
	
	public void libererEmprunt() {
		interditEmprunt = false;
	}
	
	public boolean estInterditEmprunt() {
		return interditEmprunt;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public boolean equals(Object o){
	    if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Abonne)) return false;
	    Abonne a = (Abonne)o;
	    return numero == a.numero && nom == a.nom;
	}
	
}
