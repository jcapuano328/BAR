package ica.BAR.Core;

import java.util.*;

public class Fire {
	private int [][] hittable = { 
        {7,6,4,2,1},    // range 1
        {9,8,7,6,5}     // range 2
    };
	private static FireResult[] rvo = {
		new FireResult(0,3,"AM"),
		new FireResult(4,6,"R"),
		new FireResult(7,8,"D"),
		new FireResult(9,99,"1*")
    };
	private static FireResult[] rva = {
		new FireResult(0,3,"R"),
		new FireResult(4,6,"D"),
		new FireResult(7,8,"1"),
		new FireResult(9,99,"1*")
    };        
	private static FireResult[] ava = {
		new FireResult(0,3,"R"),
		new FireResult(4,6,"D"),
		new FireResult(7,8,"1"),
		new FireResult(9,99,"1*")
    };        
	private static ArrayList<Modifier> modifiers = new ArrayList<Modifier>() {{
	    add(new Modifier("Forest / Lt Forest", -1));
	    add(new Modifier("Orchard", -1));
	    add(new Modifier("Blackjack", -1));
	    add(new Modifier("Lt Infantry", -1));
	    add(new Modifier("Arty / Dragoons", +1));
	    add(new Modifier("First Volley", +1));
	    add(new Modifier("Ferguson's Rifles", +1));
	    add(new Modifier("Fieldworks", -1));
	    add(new Modifier("Meeting House", -2));
	    add(new Modifier("Guilford CH", -1));
	    add(new Modifier("McCuiston PH", -1));
	    add(new Modifier("Wantoot PH", -2));
	    add(new Modifier("Santee River", +1));
    }};

	public Fire() {
	}
	
	public static ArrayList<String> getTypes() {
        return new ArrayList<String>() {{
            add("Rifle v Other");
            add("Rifle v Arty");
            add("Arty v All");
        }};
	}
	
	public static ArrayList<String> getSPs() {
        return new ArrayList<String>() {{
            add("1");
            add("2");
            add("3-5");
            add("6-9");
            add("10+");
        }};
	}
	
	public static ArrayList<String> getRanges() {
        return new ArrayList<String>() {{
            add("Adjacent");
            add("2-3 hexes");
        }};
	}
	
	public static ArrayList<Modifier> getModifiers() {
        return modifiers;
    }
            
	public String resolve(int hitdie, int damagedie, int spsindex, int typeindex, int rangeindex, List<Modifier> hitdrms) {
		if (isHit(hitdie, hitdrms, spsindex, rangeindex)) {
			try {
                FireResult[] results = getResultTable(typeindex);
				for (FireResult result : results) {
					if (result.isWithin(damagedie)) {
                        return result.Result;
					}
				}
			} 
			catch (Exception ex) {
			}
		}
        return "Miss";
	}

	private boolean isHit(int die, List<Modifier> hitdrms, int spsindex, int rangeindex) {
		try {
			int hitdrm = 0;
			for (Modifier drm : hitdrms)
				hitdrm += drm.Value;
				
			return ((die+hitdrm) >= hittable[rangeindex][spsindex]);
		} 
		catch (Exception ex) {
		}
		
		return false;
	}
    
    private FireResult[] getResultTable(int type) {
        if (type == 0) 
            return rvo;
        else if (type == 1) 
            return rva;
        else if (type == 2) 
            return ava;
        return rvo;
    }
}
