package ica.BAR.Core;

/**
 * Created by jcapuano on 5/30/2014.
 */
public class Saved {
    private int game;
    private int turn;
    private int phase;
    private int britishMorale;
    private int americanMorale;
    private int frenchMorale;
    private int britishVP;
    private int americanVP;

    public Saved() {
        game = -1;
        turn = 0;
        phase = 0;
        britishMorale = 0;
        americanMorale = 0;
        frenchMorale = 0;
        britishVP = 0;
        americanVP = 0;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }
    
    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getBritishMorale() {
        return britishMorale;
    }

    public void setBritishMorale(int britishMorale) {
        this.britishMorale = britishMorale;
    }

    public int getAmericanMorale() {
        return americanMorale;
    }

    public void setAmericanMorale(int americanMorale) {
        this.americanMorale = americanMorale;
    }

    public int getFrenchMorale() {
        return frenchMorale;
    }

    public void setFrenchMorale(int frenchMorale) {
        this.frenchMorale = frenchMorale;
    }

    public int getBritishVP() {
        return britishVP;
    }

    public void setBritishVP(int britishVP) {
        this.britishVP = britishVP;
    }

    public int getAmericanVP() {
        return americanVP;
    }

    public void setAmericanVP(int americanVP) {
        this.americanVP = americanVP;
    }

    public boolean isValid() {
        return game >= 0;
    }

    public void reset(Game g) {
		game = g.getId();
		turn = 0;
		phase = 0;
        britishMorale = 0;
        americanMorale = 0;
        frenchMorale = 0;
        britishVP = 0;
        americanVP = 0;
	}    
}
