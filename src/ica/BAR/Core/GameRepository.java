package ica.BAR.Core;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.*;
import java.util.*;

/**
 * Created by jcapuano on 5/29/2014.
 */
public class GameRepository {

    public static ArrayList<Game> read(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readGames(reader);
        }
        finally {
            reader.close();
        }
    }
    private static ArrayList<Game> readGames(JsonReader reader) throws IOException {
        ArrayList<Game> games = new ArrayList<Game>();

        reader.beginArray();
        while (reader.hasNext()) {
            games.add(readGame(reader));
        }
        reader.endArray();
        return games;
    }

    private static Game readGame(JsonReader reader) throws IOException {
        Game game = new Game();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                game.setId(reader.nextInt());
            } 
            else if (name.equals("name")) {
                game.setName(reader.nextString());
            }                
            else if (name.equals("desc")) {
                game.setDesc(reader.nextString());
            }                
            else if (name.equals("image")) {
                game.setImage(reader.nextString());
            }                
            else if (name.equals("moraleLevels") && reader.peek() != JsonToken.NULL) {
                game.setLevels(readLevels(reader));
            }
            else if (name.equals("startDateTime")) {
                game.setStartDateTime(reader.nextString());
            }                
            else if (name.equals("startBritishMorale")) {
                game.setStartBritishMorale(reader.nextInt());
            } 
            else if (name.equals("startAmericanMorale")) {
                game.setStartAmericanMorale(reader.nextInt());
            } 
            else if (name.equals("startFrenchMorale")) {
                game.setStartFrenchMorale(reader.nextInt());
            } 
            else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return game;
    }
    
    private static ArrayList<MoraleLevel> readLevels(JsonReader reader) throws IOException {
        ArrayList<MoraleLevel> l = new ArrayList<MoraleLevel>();
        
        reader.beginArray();
        while (reader.hasNext()) {
            MoraleLevel ml = new MoraleLevel();
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("name")) {
                    ml.setName(reader.nextString());
                }
                else if (name.equals("high")) {
                    ml.setHigh(reader.nextInt());
                }
                else if (name.equals("low")) {
                    ml.setLow(reader.nextInt());
                }
                else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            l.add(ml);
        }            
     
        reader.endArray();
        return l;
    }
}
