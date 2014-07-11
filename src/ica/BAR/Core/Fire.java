package ica.BAR.Core;

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
	private static Modifier[] modifiers = {
	    new Modifier("Forest / Lt Forest", -1),					
	    new Modifier("Orchard", -1),
	    new Modifier("Blackjack", -1),
	    new Modifier("Lt Infantry", -1),
	    new Modifier("Arty / Dragoons", +1),
	    new Modifier("First Volley", +1),
	    new Modifier("Ferguson's Rifles", +1),
	    new Modifier("Fieldworks", -1),
	    new Modifier("Meeting House", -2),
	    new Modifier("Guilford CH", -1),
	    new Modifier("McCuiston PH", -1),
	    new Modifier("Wantoot PH", -2),
	    new Modifier("Santee River", +1)
    };

	public Fire() {
	}
	
	public static String[] getSPs() {
        return new String[] {"1","2","3-5","6-9","10+"};
	}
	
	public static String[] getRange() {
        return new String[] {"Adjacent","2-3 hexes"};
	}
	
	public static String[] getType() {
        return new String[] {"Rifle v Other","Rifle v Arty","Arty v All"};
	}
	
	public static Modifier[] getModifiers() {
        return modifiers;
    }
            
	public String resolve(int hitdie, int damagedie, int spsindex, int typeindex, int rangeindex, String[] hitdrms) {
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

	private boolean isHit(int die, String[] hitdrms, int spsindex, int rangeindex) {
		try {
			int hitdrm = 0;
			for (String s : hitdrms)
				hitdrm += Modifier.getModifierValue(s, modifiers);
				
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
