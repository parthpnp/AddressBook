package application;

import application.model.Person;
import application.util.HibernateUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage window;
	
	public Main() {
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.window = primaryStage;
			
			//start session factory
			HibernateUtil.initSessionFactory();
			
			loadPersonViewScreen();
			
			this.window.initStyle(StageStyle.UNDECORATED);
			this.window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadPersonViewScreen() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PersonView.fxml"));
			AnchorPane contentPane = loader.load();
			PersonViewController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(contentPane);
			window.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean loadPersonAddScreen(Person p){
		try {
			Stage dialog = new Stage();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PersonAdd.fxml"));
			AnchorPane contentPane = loader.load();
			PersonAddController controller = loader.getController();
			controller.setMain(this);
			controller.setDialog(dialog);
			controller.setPerson(p);
			
			Scene scene = new Scene(contentPane);
			dialog.setScene(scene);
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(window);
			dialog.initStyle(StageStyle.UNDECORATED);
			dialog.showAndWait();
			
			return controller.isSaved();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		launch(args);
	}
	public Stage getWindow() {
		return window;
	}

}
