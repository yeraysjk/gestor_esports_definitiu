package yeray.priede.gestoresports;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yeray.priede.gestoresports.Controladors.MainViewController;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/yeray/priede/gestoresports/MainView.fxml"));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestió eSports");

            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
            primaryStage.setMaxWidth(1200);
            primaryStage.setMaxHeight(900);

            primaryStage.show();

            MainViewController controller = loader.getController();
            controller.carregarDades(); // Càrrega automàtica de dades JSON

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
