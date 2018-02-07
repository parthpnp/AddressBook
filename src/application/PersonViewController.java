package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.dao.PersonDao;
import application.model.Person;
import application.util.HibernateUtil;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class PersonViewController implements Initializable{
	private Main main;
	PersonDao dao = new PersonDao();
	@FXML
	private AnchorPane paneDetail, paneAccount;
	@FXML
	private Button btnClose, btnMinimize, btnAdd, btnAcClose, btnAddCcount;
	@FXML
	private TextField txtSearch;
	@FXML
	private ListView<Person> listContacts;
	@FXML
	private Label lblName, lblMobile, lblEmail, txtAddress;
	@FXML
	private ComboBox<String> cboxAcType;
	@FXML
	private TextField txtAcName;
	@FXML
	private Button btnSaveAc;
	
	public PersonViewController() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnClose.setOnAction(e -> {
			//close session factory
			HibernateUtil.getFactory().close();
			Platform.exit();
		});
		btnMinimize.setOnAction(e -> {
			main.getWindow().setIconified(true);
		});
		btnAdd.setOnAction(e -> {
			Person p = new Person();
			if(main.loadPersonAddScreen(p)){
				//main.getContacts().add(p);
				dao.insertPerson(p);
				getContacts();
			}
		});
		btnAddCcount.setOnAction(e -> {
			paneAccount.setVisible(true);
		});
		btnAcClose.setOnAction(e -> {
			paneAccount.setVisible(false);
		});
		
		listContacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				if(newValue != null){
					Person p = newValue;
					lblName.setText(p.getName());
					lblMobile.setText(p.getMobile());
					lblEmail.setText(p.getEmail());
					paneDetail.setVisible(true);
				}else {
					paneDetail.setVisible(false);
				}
			}
		});
		listContacts.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
			
			@Override
			public ListCell<Person> call(ListView<Person> param) {
				return new ListCell<Person>(){
					@Override
					protected void updateItem(Person item, boolean empty) {
						super.updateItem(item, empty);
						if(empty || item == null){
							setText(null);
						}else {
							setStyle("-fx-padding: 10.0");
							setText(item.getName());
						}
					}
				};
			}
		});
		txtSearch.textProperty().addListener((observable, oldVal, newVal) -> {
			if(newVal.length() > 1){
				;
			}
		});		
	}
	private void getContacts() {
		
		listContacts.setItems(FXCollections.observableArrayList(dao.getAllPerson()));
	}
	public void setMain(Main main) {
		this.main = main;
		getContacts();
	}
}
