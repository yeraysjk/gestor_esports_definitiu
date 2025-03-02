module yeray.priede.gestoresports {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;

    exports yeray.priede.gestoresports;
    exports yeray.priede.gestoresports.Controladors;  // Asegúrate de exportar este paquete

    opens yeray.priede.gestoresports.Controladors to javafx.fxml; // Permite la reflexión de FXML
    opens yeray.priede.gestoresports.Classes to com.google.gson;

}
