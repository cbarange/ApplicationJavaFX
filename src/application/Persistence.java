package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class permettant la sauvegarde dans un fichier nomme savePrjetIHMTP6.data, elle extends de la class Sample controller pour pouvoir afficher les exceptions sous la forme d'une fenetre d'erreur et non l'écrir
 * simplement dans une console. Elle peut ainsi gerer trois execptions: Le fichier est dossier, le fichier n'est pas cree ou le fichier est illisible
 * comme convenu avec le fichier est ecrase a chaque foit que le bouton "sauvegarde" est appuye. Pour ne pas l'ecraser il faut utiliser le constructeur File(unNom, true)
 * @author Clement Baranger
 *	@author Pierre Lemaigre
 */

public class Persistence extends SampleController{
	File file;

	public Persistence(String unNom) {
		file = new File(unNom);
	}
	/**
	 * 
	 * @param uneListe : contenant l'ensemble des cours
	 * @throws IOException
	 */
	public void sauve(ObservableList<Seance> uneListe) throws IOException {
		FileWriter fw;
		BufferedWriter br2 = null;
		String line = null;
		try {
			fw = new FileWriter(file);
			br2 = new BufferedWriter(fw);
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Le fichier est un dossier \n \t veuillez supprimer le dossier nommé saveProjetIHMTP6.data ou vérifier les privilèges accordé.");
			alert.showAndWait();
		}

		for (int i = 0; i < uneListe.size(); i++) {
			br2.write(uneListe.get(i).getMatiere() + '§'
					+ uneListe.get(i).getDate() + '§'
					+ uneListe.get(i).getDuree() + '§'
					+ uneListe.get(i).getSalle() + '§'
					+ uneListe.get(i).getEnseignant() + '§'
					+ uneListe.get(i).getNature()+'§');
		}
		br2.close();
	}
	/**
	 * 
	 * @return une liste contenant tous les cours du fichier de sauvegarde, si se cours existe deja dans le tableau il ne sera pas ajoute
	 */
	public ObservableList<Seance> restaure() {
		ObservableList<Seance> uneListe = FXCollections.observableArrayList();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			try {
				line = br.readLine();
				String matiere ="";
				String dateString ="";
				LocalDate date = null;
				String dureeString ="";
				String salle ="";
				String enseignant ="";
				String nature ="";
				int cpt = 0;
				for (int index = 0; index < line.length(); index++) {
					char c = line.charAt(index);
					if (c == '§' || c=='?') {
						cpt++;
					} else {
						switch (cpt) {
							case 0 :
								matiere += c;
								break;
							case 1 :
								dateString += c;
								break;
							case 2 :
								dureeString += c;
								break;
							case 3 :
								salle += c;
								break;
							case 4 :
								enseignant += c;
								break;
							case 5 :
								nature += c;
																			
						}
					}
					if(cpt==6) {
						cpt = 0;
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						formatter = formatter.withLocale(Locale.FRANCE);
						date = LocalDate.parse(dateString, formatter);
						uneListe.add(new Seance(matiere,date,Integer.parseInt(dureeString),salle,enseignant,nature));
						matiere="";
						dateString = "";
						dureeString = "";
						salle = "";
						enseignant = "";
						nature = "";
					}
				}
				

				br.close();
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Le fichier est illisible\n\t veuillez effacer le fichier nommé saveProjetIHMTP6.data dans la racine de l'application ou vérifier les privilèges accordé.");
				alert.showAndWait();
			}
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Le fichier n'existe pas, sauvegarder pour le créer");
			alert.showAndWait();
		}
		return uneListe;
	}

}
