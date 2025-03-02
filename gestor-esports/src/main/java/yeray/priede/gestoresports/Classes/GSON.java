package yeray.priede.gestoresports.Classes;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class GSON {

    private final Gson gson;

    // Constructor que registra el adaptador para LocalDate
    public GSON() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())  // Registrar el adaptador para LocalDate
                .setPrettyPrinting()  // Para un formato bonito en la salida JSON
                .create();
    }

    // Método para escribir un Map de torneos en un archivo JSON
    public void escriuMapAFitxerJson(String ruta, Map<String, Torneig> tornejosMap) throws IOException {
        String json = gson.toJson(tornejosMap);  // Convertimos el mapa de torneos a JSON

        // Abrimos el archivo y escribimos el JSON en él
        try (FileWriter writer = new FileWriter(ruta)) {
            writer.write(json);  // Escribimos el JSON en el archivo
        }
    }
    public <T> List<T> retornaFitxerJsonALlistaObjecte(String fitxerJson, Class<T> clazz) throws IOException {
        try (FileReader reader = new FileReader(fitxerJson)) {
            Type listType = TypeToken.getParameterized(List.class, clazz).getType();  // Definir el tipo de la lista
            return gson.fromJson(reader, listType);  // Convertir el archivo JSON en una lista de objetos
        }
    }
    // Método para leer un archivo JSON y convertirlo en un Map<String, Torneig>
    public Map<String, Torneig> retornaFitxerJsonALmapaObjecte(String fitxerJson) throws IOException {
        Type mapType = new TypeToken<Map<String, Torneig>>(){}.getType();
        try (FileReader reader = new FileReader(fitxerJson)) {
            return gson.fromJson(reader, mapType);
        }
    }

    // Método para escribir un objeto en un archivo JSON
    public void escriuObjecteJAVAAFitxerJson(String ruta, Object objecte) throws IOException {
        String json = gson.toJson(objecte);  // Convertimos el objeto a JSON

        // Abrimos el archivo y escribimos el JSON en él
        try (FileWriter writer = new FileWriter(ruta)) {  // No usamos append aquí
            writer.write(json);  // Escribimos el JSON en el archivo
        }
    }
    public void escriuMapAJson(String fitxerJson, Map<String, Torneig> map) throws IOException {
        try (FileWriter writer = new FileWriter(fitxerJson)) {
            gson.toJson(map, writer);  // Usamos GSON para convertir el mapa a JSON y escribirlo en el archivo
        }
    }

    public Map<String, Torneig> retornaFitxerJsonAMap(String fitxerJson, Class<String> keyClass, Class<Torneig> valueClass) throws IOException {
        try (FileReader reader = new FileReader(fitxerJson)) {
            Type type = TypeToken.getParameterized(Map.class, keyClass, valueClass).getType();
            return gson.fromJson(reader, type);
        }
    }



    // Clase interna que implementa el adaptador para manejar LocalDate
    public static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.format(formatter));  // Convertimos LocalDate a string
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsString(), formatter);  // Convertimos el string a LocalDate
        }
    }
}
