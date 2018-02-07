package application;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

import com.sun.javafx.geom.RoundRectangle2D;

import application.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PersonAddController implements Initializable{
	private Main main;
	private Stage dialog;
	private boolean saved = false;
	private Person person;
	
	@FXML
	private Button btnClose;
	@FXML
	private TextField txtName, txtMobile, txtEmail;
	@FXML
	private TextArea txtAddress;
	@FXML
	private Button btnSave, btnAddPhoto;
	@FXML
	private ImageView imgPhoto;
	
	public PersonAddController() {
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnClose.setOnAction(e -> {
			saved = false;
			dialog.close();
		});
		btnSave.setOnAction(e -> {
			person.setName(txtName.getText());
			person.setEmail(txtEmail.getText());
			person.setMobile(txtMobile.getText());
			saved = true;
			dialog.close();
		});
		btnAddPhoto.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files Only", "*.png", "*.jpg"));
			File photo = fileChooser.showOpenDialog(dialog);
			if(photo != null){
				File dest = new File("resources/img/" + photo.getName());
				if(dest != null){
					try {
						Files.copy(photo.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				imgPhoto.setImage(new Image(dest.toURI().toString()));
				Rectangle rect = new Rectangle(imgPhoto.getFitWidth(), imgPhoto.getFitHeight());
				rect.setArcWidth(10.0);
				rect.setArcHeight(10.0);
				imgPhoto.setClip(rect);
			}
		});
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public void setDialog(Stage dialog) {
		this.dialog = dialog;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public boolean isSaved() {
		return saved;
	}
}
