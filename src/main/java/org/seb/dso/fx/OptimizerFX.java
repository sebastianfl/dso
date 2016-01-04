package org.seb.dso.fx;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OptimizerFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setResources(ResourceBundle.getBundle("messages" /**, new Locale("ru_RU") **/ ));

		Parent root = fxmlLoader.load(getClass().getResource("optimizer.fxml").openStream());


		Scene scene = new Scene(root);

		stage.setTitle("Drakensang Optimizer");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
