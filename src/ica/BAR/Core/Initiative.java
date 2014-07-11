package ica.BAR.Core;

public class Initiative {
	private static final String cBritish = "British";
	private static final String cAmerican = "American";
	

	public Initiative() {
	}

	public static String[] getArmies() {
        return new String[] {cBritish, cAmerican};
	}
	
	public static String[] getArmyInitiative() {
        return new String[] {"5","4","3","2","1","0","-1","-2","-3","-4","-5"};
	}
	
	public static String[] getMomentum() {
        return getArmyInitiative();
	}
	
	public String resolve(int britdie, int amerdie, int britarmyinit, int britmomentum, int amerarmyinit, int amermomentum) {
		int britdrm = britarmyinit + (2 * britmomentum);
		int amerdrm = amerarmyinit + (2 * amermomentum);
			 
		int result = (britdie + britdrm)  - (amerdie + amerdrm);
		
		// initiative
		if (result > 0)
            return cBritish;
		else if (result < 0)
            return cAmerican;
        return "Tie: Re-roll";
	}
}
