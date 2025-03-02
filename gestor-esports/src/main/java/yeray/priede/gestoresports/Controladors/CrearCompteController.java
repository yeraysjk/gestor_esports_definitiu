package yeray.priede.gestoresports.Controladors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CrearCompteController {

    @FXML
    private TextField UsuariField;
    @FXML
    private PasswordField ContrasenyaField;
    @FXML
    private PasswordField RepetirContrasenyaField;

    @FXML
    private void crearCompte() {
        String usuari = UsuariField.getText();
        String contrasenya = ContrasenyaField.getText();
        String repetirContrasenya = RepetirContrasenyaField.getText();

        if (usuari.isEmpty() || contrasenya.isEmpty() || repetirContrasenya.isEmpty()) {
            mostrarError("Tots els camps són obligatoris.");
            return;
        }

        if (!contrasenya.equals(repetirContrasenya)) {
            mostrarError("Les contrasenyes no coincideixen.");
            return;
        }

        // Aquí agregas la lógica para crear el compte
    }

    @FXML
    private void enrereButton() {
        // Aquí puedes agregar la lógica para volver a la vista anterior
    }

    private void mostrarError(String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}
