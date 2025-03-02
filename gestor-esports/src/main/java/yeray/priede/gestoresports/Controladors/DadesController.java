package yeray.priede.gestoresports.Controladors;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DadesController {

    @FXML
    private TextField nomField;
    @FXML
    private TextField correuField;
    @FXML
    private TextField telefonField;

    @FXML
    private Button enrereButton;

    // Método para manejar la acción de "Enrere"
    @FXML
    private void enrereButton() {
        // Aquí va la lógica para ir atrás, puede ser un cambio de vista o algo similar
        System.out.println("Botó enrere pressionat");
    }

    // Método para validar y guardar les dades introduïdes
    @FXML
    private void guardarDades() {
        String nom = nomField.getText();
        String correu = correuField.getText();
        String telefon = telefonField.getText();

        if (nom.isEmpty() || correu.isEmpty() || telefon.isEmpty()) {
            mostrarError("Tots els camps són obligatoris.");
            return;
        }

        // Aquí se debería guardar la información del usuario (en memoria o en una base de datos)
        System.out.println("Nom: " + nom);
        System.out.println("Correu: " + correu);
        System.out.println("Telefon: " + telefon);

        // Si los datos son válidos, mostrar un mensaje de éxito
        mostrarInfo("Dades desades correctament.");
    }

    // Método para mostrar un mensaje de error
    private void mostrarError(String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(missatge);
        alert.showAndWait();
    }

    // Método para mostrar un mensaje de éxito
    private void mostrarInfo(String missatge) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informació");
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}
