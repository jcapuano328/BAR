package ica.BAR.Core;

import java.util.*;

/**
 * Created by jcapuano on 5/25/2014.
 */
public class Game {
    private int id;
    private String name;
    private String desc;
    private String image;
    private int turns;
    private ArrayList<MoraleLevel> levels;
    private Calendar startDateTime;
    private int startBritishMorale;
    private int startAmericanMorale;
    private int startFrenchMorale;
    private static final String[] phases = {
		"1. Movement", 
		"1. Rally", 
		"1. Def Arty Fire", 
		"1. Rifle Fire", 
		"1. Close Combat", 
		"2. Movement", 
		"2. Rally", 
		"2. Def Arty Fire", 
		"2. Rifle Fire", 
		"2. Close Combat", 
		"End of Turn", 
    };
    
    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public ArrayList<MoraleLevel> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<MoraleLevel> levels) {
        this.levels = levels;
    }

    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        try {
            this.startDateTime = ica.BAR.Helpers.ISO8601.toCalendar(startDateTime);
        }
        catch (Exception ex) {
        }
    }

    public int getStartBritishMorale() {
        return startBritishMorale;
    }

    public void setStartBritishMorale(int startBritishMorale) {
        this.startBritishMorale = startBritishMorale;
    }

    public int getStartAmericanMorale() {
        return startAmericanMorale;
    }

    public void setStartAmericanMorale(int startAmericanMorale) {
        this.startAmericanMorale = startAmericanMorale;
    }

    public int getStartFrenchMorale() {
        return startFrenchMorale;
    }

    public void setStartFrenchMorale(int startFrenchMorale) {
        this.startFrenchMorale = startFrenchMorale;
    }
    
    public String[] getPhases() {
        return phases;
    }
    
    public String getPhase(int phase) {
        if (phase < 0)
            return phases[0];
        if (phase >= phases.length)
            return phases[phases.length-1];
        return phases[phase];
    }

    public int getInitiativeModifier(int level) {
		if (levels.get(0).isWithin(level)) return 1;
		if (levels.get(1).isWithin(level)) return 0;
		if (levels.get(2).isWithin(level)) return 1;
		return 0;
	}

	public int getMoraleModifier(int level) {
		if (levels.get(0).isWithin(level)) return 0;
		if (levels.get(0).isWithin(level)) return -1;
		if (levels.get(0).isWithin(level)) return -2;
		return 0;
	}

    
}
