package ica.BAR.Core;

class Modifier {
	public Modifier(String name, int value) {
		Name = name;
		Value = value;
	}
	public String Name;
	public int Value;
    
	public static int getModifierValue(String name, Modifier[] modifiers) {
		for (Modifier m : modifiers) {
			if (m.Name == name)
				return m.Value;
		}
		return 0;
	}
    
}
