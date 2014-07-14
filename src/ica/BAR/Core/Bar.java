package ica.BAR.Core;

import android.content.Context;
import android.util.Log;
import java.io.*;
import java.text.*;
import java.util.*;


public class Bar {
    private static Context ctx;
    private static List<Game> games;
    private static Saved saved;
    private static final String GameFileName = "games.json";
    private static final String SaveGameFileName = "saved.json";

    public static void initialize(Context c) {
        ctx = c;
    }
    
    public static List<Game> getGames() {
        try {
            if (games == null) {
                games = GameRepository.read(ctx.getAssets().open(GameFileName));
            }
        }
        catch (Exception ex) {
            Log.e("getGames", "Failed to get games", ex);
        }
        return games;
    }
    
    public static Game getGame(int id) {
        List<Game> l = getGames();
        for (Game g : l) {
			if (g.getId() == id)
				return g;
		}
		return null;        
    }
    
    public static Saved getSaved(Game game) {
        try {
            if (saved == null) {
                saved = SavedRepository.read(ctx.openFileInput(SaveGameFileName));
            }
        }
        catch (FileNotFoundException fex) {
            saved = new Saved();
            if (game != null)
                saved.setGame(game.getId());
        }
        catch (Exception ex) {
            Log.e("getSaved", "Failed to get saved game", ex);
        }
        return saved;
    }
    
    public static void saveSaved() {
        try {
            if (saved != null) {
                SavedRepository.write(ctx.openFileOutput(SaveGameFileName, 0), saved);
            }
        }
        catch (Exception ex) {
            Log.e("saveSaved", "Failed to save game", ex);
        }
    }
    
    public static void prevTurn(Game game, Saved saved) {
        int turn = saved.getTurn();
        if (--turn < 0) {
            turn = 0;
        }
        else if (turn >= game.getTurns()) {
            turn = game.getTurns();
        }
        saved.setTurn(turn);
    }
    
    public static void nextTurn(Game game, Saved saved) {
        int turn = saved.getTurn();
        if (turn < 0) {
            turn = 0;
        }
        else if (++turn >= game.getTurns()) {
            turn = game.getTurns();
        }
        saved.setTurn(turn);
    }

    public static String getCurrentTurn(Game game, Saved saved) {
        int turn = saved.getTurn();
        Calendar start = game.getStartDateTime();
        start.add(Calendar.HOUR, turn);

        //SimpleDateFormat tft = new SimpleDateFormat ("kk:mm");
        SimpleDateFormat tft = new SimpleDateFormat ("MMMM d, yyyy hh:mm a");
        return tft.format(start.getTime());
    }
    
    public static void prevPhase(Game game, Saved saved) {
        int phase = saved.getPhase();
		if (--phase < 0) {
            prevTurn(game, saved);
            phase = game.getPhases().length - 1;
		}
		else if (phase >= game.getPhases().length) {
            nextTurn(game, saved);
		    phase = 0;
		}
        saved.setPhase(phase);
    }   
    
    public static void nextPhase(Game game, Saved saved) {
        int phase = saved.getPhase();
		if (phase < 0) {
            prevTurn(game, saved);
            phase = game.getPhases().length - 1;
		}
		else if (++phase >= game.getPhases().length) {
            nextTurn(game, saved);
		    phase = 0;
		}
        saved.setPhase(phase);
    }
    
    public static String getCurrentPhase(Game game, Saved saved) {
        return game.getPhase(saved.getPhase());
    }
}
