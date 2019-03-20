package application;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

 	/**
 	 * 
 	 * @author Clement Baranger
 	 * @author Pierre Lemaigre
 	 */

public class Seance {
	private SimpleStringProperty matiere;
	private SimpleStringProperty nature;
	private SimpleObjectProperty<LocalDate> date;
	private SimpleIntegerProperty duree;
	private SimpleStringProperty salle;
	private SimpleStringProperty enseignant;
	
	public Seance() {
		
	}
	
	
	public Seance(String m,LocalDate d,int du,String s,String e,String n) {
		matiere=new SimpleStringProperty(m);
		nature=new SimpleStringProperty(n);
		date=new SimpleObjectProperty<LocalDate>(d);
		duree=new SimpleIntegerProperty(du);
		salle=new SimpleStringProperty(s);
		enseignant=new SimpleStringProperty(e);
	}	

	public Seance(Seance seance) {
		this.equals(seance);
	}

	/**
	 * 
	 * @return un string de la matiere
	 */
	
	public String getMatiere() {
		return matiere.get();
	}
	/**
	 * 
	 * @param matiere
	 */
	public void setMatiere(String matiere) {
		this.matiere.set(matiere);
	}
	/**
	 * 
	 * @return un string de la nature
	 */
	public String getNature() {
		return nature.get();
	}
	/**
	 * 
	 * @param nature
	 */
	public void setNature(String nature) {
		this.nature.set(nature);
	}
	/**
	 * 
	 * @return un string de la date
	 */
	public LocalDate getDate() {
		return date.get();
	}
	/**
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date.set(date);
	}
	/**
	 * 
	 * @return un string de la duree
	 */
	public int getDuree() {
		return duree.get();
	}
	
	public String getDureeString() {
		return duree.getValue().toString();
	}
	/**
	 * 
	 * @param duree
	 */
	public void setDuree(int duree) {
		this.duree.set(duree);
	}
	/**
	 * 
	 * @return un string de la salle
	 */
	public String getSalle() {
		return salle.get();
	}
	/**
	 * 
	 * @param salle
	 */
	public void setSalle(String salle) {
		this.salle.set(salle);
	}
	/**
	 * 
	 * @return un string de l'ensaignant
	 */
	public String getEnseignant() {
		return enseignant.get();
	}
	/**
	 * 
	 * @param enseignant
	 */
	public void setEnseignant(String enseignant) {
		this.enseignant.set(enseignant);
	}

	@Override
	public String toString() {
		return "Seance [matiere=" + matiere + ", nature=" + nature + ", date="
				+ date + ", duree=" + duree + ", salle=" + salle
				+ ", enseignant=" + enseignant + "]";
	}
}
