package yeray.priede.gestoresports;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GSON {

    private final Gson gson;

    // Constructor que registra el adaptador para LocalDate
    public GSON() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())  // Registrar el adaptador para LocalDate
                .setPrettyPrinting()  // Para un formato bonito en la salida JSON
                .create();
    }

    public void escriuObjecteJAVAAFitxerJson(String ruta, Object objecte) throws IOException, ParseException {
        String json = gson.toJson(objecte);  // Convertimos el objeto a JSON

        // Abrimos el archivo y escribimos el JSON en él
        try (FileWriter writer = new FileWriter(ruta)) {  // No usamos append aquí
            writer.write(json);  // Escribimos el JSON en el archivo
        }
    }


    // Método para leer un archivo JSON y convertirlo en una lista de objetos
    public <T> List<T> retornaFitxerJsonALlistaObjecte(String fitxerJson, Class<T> clazz) throws IOException, ParseException {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        try (FileReader reader = new FileReader(fitxerJson)) {
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            throw new ParseException("Error al deserialitzar el fitxer JSON", 0);
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
