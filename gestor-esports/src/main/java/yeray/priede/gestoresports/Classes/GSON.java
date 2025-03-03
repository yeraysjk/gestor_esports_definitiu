package yeray.priede.gestoresports.Classes;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import yeray.priede.gestoresports.LocalDateAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class GSON {
    private final Gson gson;

    public GSON() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void escriuMapAFitxerJson(String ruta, Map<String, Torneig> tornejosMap) throws IOException {
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(tornejosMap, writer);
        }
    }

    public Map<String, Torneig> retornaFitxerJsonAMap(String fitxerJson) throws IOException {
        try (FileReader reader = new FileReader(fitxerJson)) {
            Type mapType = new TypeToken<Map<String, Torneig>>(){}.getType();
            return gson.fromJson(reader, mapType); // Lee el archivo y convierte el JSON en un mapa
        }
    }


    public void escriuLlistaAJson(String ruta, List<Participant> participants) throws IOException {
        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(participants, writer);
        }
    }

    public List<Participant> retornaFitxerJsonALlista(String fitxerJson) throws IOException {
        try (FileReader reader = new FileReader(fitxerJson)) {
            Type listType = new TypeToken<List<Participant>>(){}.getType();
            return gson.fromJson(reader, listType);
        }
    }
}
