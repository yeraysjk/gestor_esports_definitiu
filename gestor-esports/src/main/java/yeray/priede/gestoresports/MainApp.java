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
            // Cargar la vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/yeray/priede/gestoresports/MainView.fxml"));
            AnchorPane root = loader.load();

            // Establecer la escena
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestió eSports");

            // Establecer el tamaño de la ventana
            primaryStage.setWidth(800);  // Ancho deseado
            primaryStage.setHeight(600); // Alto deseado

            // Hacer la ventana redimensionable pero con un tamaño máximo y mínimo
            primaryStage.setMinWidth(600);   // Ancho mínimo
            primaryStage.setMinHeight(400);  // Alto mínimo
            primaryStage.setMaxWidth(1200);  // Ancho máximo
            primaryStage.setMaxHeight(900);  // Alto máximo

            // Mostrar la ventana
            primaryStage.show();

            // Obtener el controlador de la vista principal
            MainViewController controller = loader.getController();

            // Cargar los torneos desde el archivo

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);  // Llama al método launch de JavaFX
    }
}
