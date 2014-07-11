package ica.BAR.Core;

public class Morale
{
	public Morale() {
	}
	
	public static String[] getUnitMorale() {
        return new String[] {"5","4","3","2","1","0","-1","-2","-3","-4","-5"};
	}
	
	public static String[] getArmyMorale() {
        return new String[] {"5","4","3","2","1","0","-1","-2","-3","-4","-5"};
	}
	
	public static String[] getLeader() {
        return new String[] {"5","4","3","2","1","0","-1","-2","-3","-4","-5"};
	}
	
	public boolean check(int die, int unitmorale, int armymorale, int leader) {
        return (die + unitmorale + armymorale + leader >= 5);
	}
}



