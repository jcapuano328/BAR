package ica.BAR.Core;

import java.util.*;

public class Melee {

    private static List<String[]> table = new ArrayList<String[]>() {{
		// 1-3
		add(new String[] {
            "2/- (D Momentum)",
			"AC/- (D Momentum)",
			"1*/-",
			"1/-",
			"1/-",
			"D/-",
			"D/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/R",
			"-/D (A Momentum)",
			"-/D (A Momentum)"
        });
		// 1-2
		add(new String[] {
            "AC/- (D Momentum)",
			"1*/- (D Momentum)",
			"1/-",
			"D/-",
			"D/-",
			"D/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/R",
			"-/D",
			"-/D (A Momentum)",
			"-/1* (A Momentum)"
        });
		// 1-1
        add(new String[] {
            "AC/- (D Momentum)",
			"1*/- (D Momentum)",
			"1/-",
			"D/-",
			"D/-",
			"R/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/D",
			"-/D",
			"-/1 (A Momentum)",
			"-/1* (A Momentum)"
        });
		// 3-2
        add(new String[] {
            "AC/- (D Momentum)",
			"1*/- (D Momentum)",
			"1/-",
			"D/-",
			"D/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/D",
			"-/D",
			"-/1",
			"-/1* (A Momentum)",
			"-/DC (A Momentum)"
        });
		// 2-1
		add(new String[] {
            "AC/- (D Momentum)",
			"1*/- (D Momentum)",
			"1/-",
			"D/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/R",
			"-/D",
			"-/D",
			"-/1",
			"-/1* (A Momentum)",
			"-/AC (A Momentum)"
        });
		// 3-1
		add(new String[] {
            "1*/- (D Momentum)",
			"D/- (D Momentum)",
			"D/-",
			"R/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/D",
			"-/D",
			"-/1",
			"-/1*",
			"-/DC (A Momentum)",
			"-/AC (A Momentum)"
        });
		// 4-1
		add(new String[] {
            "D/- (D Momentum)",
			"D/- (D Momentum)",
			"R/-",
			"R/-",
			"PIN",
			"R/R",
			"-/R",
			"-/D",
			"-/D",
			"-/1",
			"-/1*",
			"-/DC",
			"-/AC (A Momentum)",
			"-/2 (A Momentum)"
		});
	}};

    private static Modifier[] attackmodifiers = {
		new Modifier("Defender Rifle", +1),
		new Modifier("Defender Disrupted/Shattered", +1),
		new Modifier("Dragoon vs Disrupted/Shattered", +1),
		new Modifier("Defenders all Amer Militia/Brit Aux", +1),
		new Modifier("Defender Surrounded", +1),
		new Modifier("Defender Nelson's Ferry", +1)
    };
    private static Modifier[] defendmodifiers = {
		new Modifier("Attacker Rifle", -1),
		new Modifier("Attackers All Amer Militia", -1),
		new Modifier("Up Slope, Run", -1),
		new Modifier("Ravine", -2),
		new Modifier("Fieldworks", -1),
		new Modifier("Blackjack Oak", -2),
		new Modifier("Attackers any non-ford Brandywine Creek", -1),
		new Modifier("Attackers all non-ford Brandywine Creek", -1),
		new Modifier("Meeting House", -1),
		new Modifier("Guilford CH, McCuiston PH, Wantoot PH", -1),
		new Modifier("Town", -1),
		new Modifier("Swamp Fox in swamp", -1),
		new Modifier("Attacker Surrounded", -1)
	};

	public Melee() {
	}
	
	public static String[] getOdds() {
        return new String[] {"1-3","1-2","1-1","3-2","2-1","3-1","4-1"};
	}
	
	public static String[] getNationalities() {
        return new String[] {"British", "American", "French"};
	}
	
	public static Modifier[] getAttackModifiers() {
        return attackmodifiers;
	}
	
	public static Modifier[] getDefendModifiers() {
        return defendmodifiers;
	}
	
	public String resolve(int combatdie, int tacticaldie, 
						int oddsindex, 
						int attackmorale, int attackarmymorale, int attackleader, boolean attacktacticaldrm, boolean attackdiversion, String[] attackdrms, 
						int defendmorale, int defendarmymorale, int defendleader, boolean defendtacticaldrm, String[] defenddrms) {
		try {
			int index = combatdie 
						+ getTacticalDrm(tacticaldie, attacktacticaldrm, defendtacticaldrm) 
						+ attackmorale + attackleader + getDRM(attackmodifiers, attackdrms)
						+ (attackdiversion ? -1 : 0)
						- defendmorale - defendleader + getDRM(defendmodifiers, defenddrms);
						
			String[] results = table.get(oddsindex);
						
			index += 2; // index-ize the die roll
			if (index < 0) index = 0;
			else if (index > results.length-1) index = results.length - 1;
			
            return results[index];
		} 
		catch (Exception ex) {
            return "";
		}
	}
	private int getTacticalDrm(int tacticaldie, boolean attacktacticaldrm, boolean defendtacticaldrm) {
		int adrm = attacktacticaldrm ?  1 : 0;
		int ddrm = defendtacticaldrm ? -1 : 0;
		
		int d = tacticaldie + adrm + ddrm;
		if (d < 1) return -2;
		if (d < 3) return -1;
		if (d < 7) return 0;
		if (d < 9) return 1;
		return 2;
	}
	
	private int getDRM(Modifier[] modifiers, String[] selectedmodifiers) {
		int drm=0;
		for (String s : selectedmodifiers)
			drm += Modifier.getModifierValue(s, modifiers);
		return drm;
	}
}
