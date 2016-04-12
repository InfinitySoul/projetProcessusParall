package documents;

import bibliotheque.IUtilisateur;
import exceptions.*;

public class DVD extends Document {

	private boolean ageRestrictionBasse;
	private final static int VALEUR_AGE_RESTRICTION_MMINIMAL = 12;
	
	private boolean ageRestrictionDeux;
	private final static int VALEUR_AGE_RESTRICTION_DEUX = 16;
	
	public DVD(String nom, int valeurAgeMinimal) {
		super(nom);
		
		if(valeurAgeMinimal >= DVD.VALEUR_AGE_RESTRICTION_DEUX) {
			this.ageRestrictionDeux = true;
			this.ageRestrictionBasse = true;
		}
		else {
			this.ageRestrictionDeux = false;
			if(valeurAgeMinimal >= DVD.VALEUR_AGE_RESTRICTION_MMINIMAL)
				this.ageRestrictionBasse = true;
			else
				this.ageRestrictionBasse = false;
		}
	}
	
	@Override
	public void reserver(IUtilisateur ab) throws MemeUtilisateurException, PasLibreException, TropJeuneException, InterditEmpruntException {
		if( ! ageRequis(ab))
			throw new TropJeuneException();
		super.reserver(ab);
	}
	
	@Override
	public void emprunter(IUtilisateur ab) throws MemeUtilisateurException, PasLibreException, TropJeuneException, InterditEmpruntException {
		if( ! ageRequis(ab))
			throw new TropJeuneException();
		super.emprunter(ab);
	}
	
	public boolean ageRequis(IUtilisateur ab) {
		if(ageRestrictionDeux == true)
			return ab.getAge() >= DVD.VALEUR_AGE_RESTRICTION_DEUX;
		if(ageRestrictionBasse == true)
			return ab.getAge() >= DVD.VALEUR_AGE_RESTRICTION_MMINIMAL;
		return true;
	}

}
