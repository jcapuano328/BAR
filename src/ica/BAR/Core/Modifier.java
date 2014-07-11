package ica.BAR.Core;

public class Modifier {
	public Modifier(String name, int value) {
		Name = name;
		Value = value;
        Count = 0;
	}
	public String Name;
	public int Value;
    public int Count;
    
	public static int getModifierValue(String name, Modifier[] modifiers) {
		for (Modifier m : modifiers) {
			if (m.Name == name)
				return m.Value;
		}
		return 0;
	}
    
}
