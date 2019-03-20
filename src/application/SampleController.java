package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
	/**
	 * Controller de l'IHM, relie au fichier Slampe.fxml
	 * gp signifie gridPane, il existe un gp(aussi appelle gpAdd) et un gpEdit
	 * @author Clement Baranger
	 * @author Pierre Lemaigre
	 *
	 */


public class SampleController implements Initializable {

	@FXML
	private TableView tview;
	ObservableList<Seance> olDonnees = FXCollections.observableArrayList();

	
	@FXML
	TableColumn columnMatiere;
	@FXML
	TableColumn columnNature;
	@FXML
	TableColumn columnDate;
	@FXML
	TableColumn columnDuree;
	@FXML
	TableColumn columnSalle;
	@FXML
	TableColumn columnEnseignant;
	@FXML
	Button buttonCharger;
	@FXML
	Button buttonSave;
	@FXML
	Button buttonAjouter;
	@FXML
	Button buttonSupprimer;
	@FXML
	Button buttonEditer;
	@FXML
	ChoiceBox<Matiere> tfMatiere;
	@FXML
	DatePicker tfDate;
	@FXML
	TextField tfDuree;
	@FXML
	TextField tfSalle;
	@FXML
	TextField tfEnseignant;
	@FXML
	ChoiceBox<Nature> tfNature;
	@FXML
	Button gpButtonValider;
	@FXML
	Button gpButtonAnnuler;
	@FXML
	ChoiceBox<Matiere> tfMatiereEdit;
	@FXML
	DatePicker tfDateEdit;
	@FXML
	TextField tfDureeEdit;
	@FXML
	TextField tfSalleEdit;
	@FXML
	TextField tfEnseignantEdit;
	@FXML
	ChoiceBox<Nature> tfNatureEdit;
	@FXML
	Button gpButtonValiderEdit;
	@FXML
	Button gpButtonAnnulerEdit;
	@FXML
	GridPane gridPane;
	@FXML
	GridPane gridPaneEdit;
	
	
	@FXML
	public void editeClick(ActionEvent event) {
		try {
			int index=tview.getSelectionModel().getSelectedIndex();
			Seance s=(Seance)olDonnees.get(index);
			gridPaneEdit.setVisible(true);
			tview.setVisible(false);
			buttonCharger.setVisible(false);
			buttonAjouter.setVisible(false);
			buttonEditer.setVisible(false);
			buttonSupprimer.setVisible(false);
			buttonCharger.setVisible(false);
			buttonSave.setVisible(false);
			tfMatiereEdit.setValue(Matiere.get(s.getMatiere()));
			tfDateEdit.setValue(s.getDate());
			tfDureeEdit.setText((s.getDureeString()));
			tfSalleEdit.setText(s.getSalle());
			tfEnseignantEdit.setText(s.getEnseignant());
			tfNatureEdit.setValue(Nature.get(s.getNature()));
		}catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Aucun cour sélectionné");
			alert.showAndWait();
		}
		String dza;
	}
	@FXML
	public void deleteClick(ActionEvent event) {
		try {
			olDonnees.remove(tview.getSelectionModel().getSelectedIndex());
		}catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Aucun cour sélectionné");
			alert.showAndWait();
		}		
	}
	@FXML
	public void loadClick(ActionEvent event) {
		Persistence p=new Persistence("saveProjetIHMTP6.data");
		
		ObservableList<Seance> save=FXCollections.observableArrayList();
		save.addAll(p.restaure());
		boolean test=true;
		for(int i=0;i<save.size();i++) {
			for(int o=0;o<olDonnees.size();o++) {
				if(save.get(i).toString().equals(olDonnees.get(o).toString()) && test) {
					test=false;
				}
			}
			if(test)
				olDonnees.add(save.get(i));
			test=true;
						
		}
		
		
	}
	@FXML
	public void ajouterClick(ActionEvent event) {
		gridPane.setVisible(true);
		tview.setVisible(false);
		buttonCharger.setVisible(false);
		buttonAjouter.setVisible(false);
		buttonEditer.setVisible(false);
		buttonSupprimer.setVisible(false);
		buttonCharger.setVisible(false);
		buttonSave.setVisible(false);
	}
	@FXML
	public void saveClick(ActionEvent event) throws IOException{
		Persistence p=new Persistence("saveProjetIHMTP6.data");
		p.sauve(olDonnees);
	}
	@FXML
	public void gpValiderClick(ActionEvent event) {
		if(nothingIsEmptyAdd()) {
			gridPane.setVisible(false);
			tview.setVisible(true);
			buttonCharger.setVisible(true);
			buttonAjouter.setVisible(true);
			buttonEditer.setVisible(true);
			buttonSupprimer.setVisible(true);
			buttonCharger.setVisible(true);
			buttonSave.setVisible(true);
			Seance s=new Seance(tfMatiere.getValue().getString(),tfDate.getValue(),Integer.parseInt(tfDuree.getText()),tfSalle.getText(),tfEnseignant.getText(),tfNature.getValue().getString());
			for(int i=0;i<olDonnees.size();i++) {
				
				if(s.toString().equals(olDonnees.get(i).toString())) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Ce cour existe deja");
					alert.showAndWait();
					return;
				}
					
			}
			
			olDonnees.add(s);
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Information(s) manquante(s) ou incorecte(s)");
			alert.showAndWait();
		}
	}
	@FXML
	public void gpValiderEditClick(ActionEvent event) {
		if(nothingIsEmptyEdit()) {
			olDonnees.remove(tview.getSelectionModel().getSelectedIndex());
			Seance s=new Seance(tfMatiereEdit.getValue().getString(),tfDateEdit.getValue(),Integer.parseInt(tfDureeEdit.getText()),tfSalleEdit.getText(),tfEnseignantEdit.getText(),tfNatureEdit.getValue().getString());
			olDonnees.add(s);
			gridPaneEdit.setVisible(false);
			tview.setVisible(true);
			buttonCharger.setVisible(true);
			buttonAjouter.setVisible(true);
			buttonEditer.setVisible(true);
			buttonSupprimer.setVisible(true);
			buttonCharger.setVisible(true);
			buttonSave.setVisible(true);
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("information(s) manquante(s) ou incorecte(s)");
			alert.showAndWait();
		}
		
	}
	@FXML
	public void gpAnnulerClick(ActionEvent event) {
		gridPane.setVisible(false);
		tview.setVisible(true);
		buttonCharger.setVisible(true);
		buttonAjouter.setVisible(true);
		buttonEditer.setVisible(true);
		buttonSupprimer.setVisible(true);
		buttonCharger.setVisible(true);
		buttonSave.setVisible(true);
	}
	@FXML
	public void gpAnnulerEditClick(ActionEvent event) {
		gridPaneEdit.setVisible(false);
		tview.setVisible(true);
		buttonCharger.setVisible(true);
		buttonAjouter.setVisible(true);
		buttonEditer.setVisible(true);
		buttonSupprimer.setVisible(true);
		buttonCharger.setVisible(true);
		buttonSave.setVisible(true);
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tview.setItems(olDonnees); // lien entre les cours et la tableview
		tview.setEditable(true); 
		
		gridPane.setVisible(false); // gridPane servant a ajouter de nouveaux cours
		gridPaneEdit.setVisible(false); // gridPane servant a modifier des cours
		
		
		//--------------------------- PARAMETRAGE DE LA TABLEVIEW ---------------------------
		columnSalle.setCellValueFactory(new PropertyValueFactory<Seance, String>("salle"));
		columnDate.setCellValueFactory(new PropertyValueFactory<Seance, LocalDate>("date"));
		columnNature.setCellValueFactory(new PropertyValueFactory<Seance, String>("nature"));
		columnDuree.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("duree"));
		columnEnseignant.setCellValueFactory(new PropertyValueFactory<Seance, String>("enseignant"));
		columnMatiere.setCellValueFactory(new PropertyValueFactory<Seance, String>("matiere"));
		//-----------------------------------------------------------------------------------
		
		//--------------------------- PARAMETRAGE DES CHOICEBOX ---------------------------
		tfMatiere.setItems(FXCollections.observableArrayList(Matiere.AP,Matiere.BD,Matiere.CDIN,Matiere.SI));
		tfMatiereEdit.setItems(FXCollections.observableArrayList(Matiere.AP,Matiere.BD,Matiere.CDIN,Matiere.SI));
		tfNature.setItems(FXCollections.observableArrayList(Nature.Cours,Nature.TD,Nature.TP));
		tfNatureEdit.setItems(FXCollections.observableArrayList(Nature.Cours,Nature.TD,Nature.TP));
		//---------------------------------------------------------------------------------
	}
	
	/**
	 * 
	 * @return true si tout les champs textuel sont complete et comporte la bonne valeur, sinon return false
	 */
	
	public boolean nothingIsEmptyEdit() {
		boolean res=false;
		// verification que rien est vide
		if(tfMatiereEdit.getValue()!=null && tfDateEdit.getValue()!=null && tfDureeEdit.getText()!=null && tfSalleEdit.getText()!=null && tfEnseignantEdit.getText()!=null && tfNatureEdit.getValue()!=null &&
		tfDureeEdit.getText().trim().length()!=0 && tfSalleEdit.getText().trim().length()!=0 && tfEnseignantEdit.getText().trim().length()!=0)
						res = true;
		// verification que la duree contient seulement des chiffres
		for(int i=0;i<tfDureeEdit.getText().trim().length() && res;i++) {
			if(tfDureeEdit.getText().charAt(i)>47 && tfDureeEdit.getText().charAt(i)<58) {
				res=true;
			}else {
				res=false;
			}
		}																				
		// verification que la salle est du bon format
		if(!(tfSalleEdit.getText().matches("^[A-Z][0-9]{3}$")))
			res=false;
		return res;
	}
	
	/**
	 * 
	 * @return true si tout les champs textuel sont complete et comporte la bonne valeur, sinon return false
	 */
	
	public boolean nothingIsEmptyAdd() {
		boolean res=false;
		// verification que rien est vide
		if(tfMatiere.getValue()!=null && tfDate.getValue()!=null && tfDuree.getText()!=null && tfSalle.getText()!=null && tfEnseignant.getText()!=null && tfNature.getValue()!=null &&
				tfDuree.getText().trim().length()!=0 && tfSalle.getText().trim().length()!=0 && tfEnseignant.getText().trim().length()!=0)
			res= true;
		// verification que la duree contient seulement des chiffres
		for(int i=0;i<tfDuree.getText().trim().length() && res;i++) {
			if(tfDuree.getText().charAt(i)>47 && tfDuree.getText().charAt(i)<58) {
				res=true;
			}else {
				res=false;
			}
		}
		// verification que la salle est du bon format
		if(!(tfSalle.getText().matches("^[A-Z][0-9]{3}$")))
			res=false;
		return res;
	}

}
