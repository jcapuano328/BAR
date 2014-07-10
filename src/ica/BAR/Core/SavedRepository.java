package ica.BAR.Core;

import android.util.JsonReader;
import android.util.JsonWriter;
import java.io.*;
/**
 * Created by jcapuano on 5/31/2014.
 */
public class SavedRepository {

    public static Saved read(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readSaved(reader);
        }
        finally {
            reader.close();
        }
    }
    private static Saved readSaved(JsonReader reader) throws IOException {
        Saved saved = new Saved();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("game")) {
                saved.setGame(reader.nextInt());
            } 
            else if (name.equals("turn")) {
                saved.setTurn(reader.nextInt());
            } 
            else if (name.equals("phase")) {
                saved.setPhase(reader.nextInt());
            } 
            else if (name.equals("britishMorale")) {
                saved.setBritishMorale(reader.nextInt());
            } 
            else if (name.equals("americanMorale")) {
                saved.setAmericanMorale(reader.nextInt());
            } 
            else if (name.equals("frenchMorale")) {
                saved.setFrenchMorale(reader.nextInt());
            } 
            else if (name.equals("britishVP")) {
                saved.setBritishVP(reader.nextInt());
            } 
            else if (name.equals("americanVP")) {
                saved.setAmericanVP(reader.nextInt());
            } 
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return saved;
    }

    public static void write(OutputStream out, Saved saved) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        try {
            writeSaved(writer, saved);
        }
        finally {
            writer.close();
        }
    }
    private static void writeSaved(JsonWriter writer, Saved saved) throws IOException {
        writer.beginObject();
        writer.name("game").value(saved.getGame());
        writer.name("turn").value(saved.getTurn());
        writer.name("phase").value(saved.getPhase());
        writer.name("britishMorale").value(saved.getBritishMorale());
        writer.name("americanMorale").value(saved.getAmericanMorale());
        writer.name("frenchMorale").value(saved.getFrenchMorale());
        writer.name("britishVP").value(saved.getBritishVP());
        writer.name("americanVP").value(saved.getAmericanVP());
        writer.endObject();
    }    
    
}
