package yeray.priede.gestoresports.Controladors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import yeray.priede.gestoresports.Classes.GSON;
import yeray.priede.gestoresports.Classes.Participant;
import yeray.priede.gestoresports.Classes.Torneig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class MainViewController implements Initializable {

    @FXML
    private ListView<Torneig> lstTornejos;
    @FXML
    private ListView<Participant> lstParticipants;
    @FXML
    private TextField txtNomParticipant, txtNickParticipant, txtEquipParticipant, txtPuntuacioParticipant;
    @FXML
    private Map<String, Torneig> tornejosMap = new HashMap<>();
    @FXML
    private Stack<String> historialAccions = new Stack<>();

    // Método para agregar un participante
    @FXML
    private void afegirParticipant(ActionEvent event) {
        String nom = txtNomParticipant.getText();
        String nickname = txtNickParticipant.getText();
        String equip = txtEquipParticipant.getText();
        String puntuacio = txtPuntuacioParticipant.getText();

        if (nom.isEmpty() || nickname.isEmpty() || equip.isEmpty() || puntuacio.isEmpty()) {
            mostrarError("Tots els camps són obligatoris per afegir un participant.");
            return;
        }

        Participant participant = new Participant(nom, nickname, equip, Integer.parseInt(puntuacio));

        Torneig torneigSeleccionat = lstTornejos.getSelectionModel().getSelectedItem();
        if (torneigSeleccionat != null) {
            torneigSeleccionat.afegirParticipant(participant);
            lstParticipants.getItems().add(participant);
            mostrarInfo("Participant afegit correctament.");
        } else {
            mostrarError("Selecció de torneig incorrecta.");
        }
    }

    // Método para eliminar un participante
    @FXML
    private void eliminarParticipant(ActionEvent event) {
        Participant participantSeleccionat = lstParticipants.getSelectionModel().getSelectedItem();
        if (participantSeleccionat != null) {
            Torneig torneigSeleccionat = lstTornejos.getSelectionModel().getSelectedItem();
            if (torneigSeleccionat != null) {
                torneigSeleccionat.getParticipants().remove(participantSeleccionat);
                lstParticipants.getItems().remove(participantSeleccionat);
                mostrarInfo("Participant eliminat correctament.");
            } else {
                mostrarError("Selecció de torneig incorrecta.");
            }
        } else {
            mostrarError("Selecció de participant incorrecta.");
        }
    }

    // Método para crear un torneo
    @FXML
    public void crearTorneig(ActionEvent actionEvent) {
        // Solicitar el nombre del torneo
        TextInputDialog dialogNom = new TextInputDialog();
        dialogNom.setTitle("Crear Torneig");
        dialogNom.setHeaderText("Introduïu el nom del torneig:");
        Optional<String> nomResult = dialogNom.showAndWait();

        if (nomResult.isPresent() && !nomResult.get().isEmpty()) {
            String nomTorneig = nomResult.get();

            // Solicitar la fecha con un DatePicker
            DatePicker datePicker = new DatePicker();
            datePicker.setPromptText("Selecciona la data del torneig");
            VBox vbox = new VBox(datePicker);
            Alert dateDialog = new Alert(Alert.AlertType.INFORMATION);
            dateDialog.setTitle("Crear Torneig");
            dateDialog.setHeaderText("Selecciona la data del torneig:");
            dateDialog.getDialogPane().setContent(vbox);

            // Mostrar el DatePicker y esperar la entrada
            Optional<ButtonType> result = dateDialog.showAndWait();

            LocalDate data = null;
            if (datePicker.getValue() != null) {
                data = datePicker.getValue();
            }

            if (data == null) {
                mostrarError("La data del torneig és obligatòria.");
                return;
            }

            // Solicitar el juego
            TextInputDialog dialogJoc = new TextInputDialog();
            dialogJoc.setTitle("Crear Torneig");
            dialogJoc.setHeaderText("Introduïu el joc del torneig:");
            Optional<String> jocResult = dialogJoc.showAndWait();
            if (!jocResult.isPresent() || jocResult.get().isEmpty()) {
                mostrarError("El joc del torneig és obligatori.");
                return;
            }
            String jocTorneig = jocResult.get();

            // Solicitar el formato
            TextInputDialog dialogFormat = new TextInputDialog();
            dialogFormat.setTitle("Crear Torneig");
            dialogFormat.setHeaderText("Introduïu el format del torneig:");
            Optional<String> formatResult = dialogFormat.showAndWait();
            if (!formatResult.isPresent() || formatResult.get().isEmpty()) {
                mostrarError("El format del torneig és obligatori.");
                return;
            }
            String formatTorneig = formatResult.get();

            // Solicitar los premios
            TextInputDialog dialogPremis = new TextInputDialog();
            dialogPremis.setTitle("Crear Torneig");
            dialogPremis.setHeaderText("Introduïu els premis del torneig:");
            Optional<String> premisResult = dialogPremis.showAndWait();
            if (!premisResult.isPresent() || premisResult.get().isEmpty()) {
                mostrarError("Els premis del torneig són obligatoris.");
                return;
            }
            String premisTorneig = premisResult.get();

            // Crear el nuevo torneo
            Torneig nouTorneig = new Torneig(nomTorneig, data, jocTorneig, formatTorneig, premisTorneig);
            lstTornejos.getItems().add(nouTorneig);
            tornejosMap.put(nomTorneig, nouTorneig);

            // Guardar los torneos en el archivo JSON automáticamente
            guardarTorneigEnJson(nouTorneig);

            historialAccions.push("Crear torneig: " + nomTorneig);
            mostrarInfo("Torneig creat correctament: " + nomTorneig);
        } else {
            mostrarError("El nom del torneig no pot estar buit.");
        }
    }

    // Método para cargar los torneos
    @FXML
    public void cargarTornejos() {
        try {
            // Llamar al método que carga los torneos desde el archivo JSON
            Map<String, Torneig> tornejos = cargarTornejosExistentes();

            // Verificar si el mapa no está vacío
            if (tornejos != null && !tornejos.isEmpty()) {
                // Actualizar la lista en el ListView
                lstTornejos.getItems().setAll(tornejos.values());
                mostrarInfo("Tornejos carregats correctament.");
            } else {
                mostrarError("No s'han carregat tornejos.");
            }
        } catch (IOException e) {
            mostrarError("Error carregant els tornejos: " + e.getMessage());
        }
    }

    // Método para guardar un torneo en el archivo JSON (en formato clave-valor)
    private void guardarTorneigEnJson(Torneig nouTorneig) {
        try {
            // Leer los torneos existentes (si los hay)
            Map<String, Torneig> tornejosMap = cargarTornejosExistentes();  // Cambié la lista por un mapa

            // Añadir el nuevo torneo al mapa
            tornejosMap.put(nouTorneig.getNom(), nouTorneig);  // La clave es el nombre del torneo

            // Usamos la librería GSON para escribir el mapa de torneos en el archivo JSON
            GSON gsonHelper = new GSON();
            gsonHelper.escriuMapAJson("tornejos.json", tornejosMap);  // Escribimos el mapa actualizado en el archivo

            mostrarInfo("Torneig desat correctament en el fitxer JSON.");
        } catch (IOException e) {
            mostrarError("Error en desar el torneig al fitxer JSON: " + e.getMessage());
        }
    }

    // Método para cargar los torneos desde el archivo JSON
    public Map<String, Torneig> cargarTornejosExistentes() throws IOException {
        File archivo = new File("tornejos.json");
        if (!archivo.exists() || archivo.length() == 0) {
            // Si el archivo no existe o está vacío, retornar un mapa vacío
            return new HashMap<>();
        } else {
            // El archivo tiene contenido, cargar los torneos desde el archivo
            GSON gsonHelper = new GSON();
            return gsonHelper.retornaFitxerJsonAMap("tornejos.json", String.class, Torneig.class);
        }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Llamar a cargarTornejos cuando la vista se inicializa
        cargarTornejos();
    }
}
