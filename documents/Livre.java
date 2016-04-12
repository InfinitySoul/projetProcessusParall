package documents;

public class Livre extends Document {

	private String auteur;
	
	public Livre(String nom, String nomAuteur) {
		super(nom);
		auteur = nomAuteur;
	}

}
