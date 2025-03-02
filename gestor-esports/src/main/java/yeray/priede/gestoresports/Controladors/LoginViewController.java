package yeray.priede.gestoresports.Controladors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button crearCompteButton;

    // Método que se ejecuta cuando se hace clic en el botón "Login"
    @FXML
    private void onLoginClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Lógica para validar usuario y contraseña
        if (username.isEmpty() || password.isEmpty()) {
            mostrarError("Usuari i contrasenya són obligatoris.");
            return;
        }

        // Aquí debes añadir la lógica de validación del login
        // Por ejemplo, comprobar si el usuario y la contraseña son correctos
        System.out.println("Iniciant sessió per: " + username);

        // Si el login es exitoso, se puede cambiar de vista
        // Esto se puede hacer con un controlador adecuado o una transición
    }

    // Método que se ejecuta cuando se hace clic en el botón "Crear compte"
    @FXML
    private void onRegisterClick() {
        // Aquí debes redirigir a la vista de creación de cuenta
        System.out.println("Anant a la pàgina de creació de compte.");
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
