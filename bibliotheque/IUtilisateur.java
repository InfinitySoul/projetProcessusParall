package bibliotheque;

public interface IUtilisateur {

	int getNumero();
	
	String getNom();
	
	void interdireEmprunt();
	
	void libererEmprunt();

	int getAge();
	
	boolean estInterditEmprunt();
	
}
