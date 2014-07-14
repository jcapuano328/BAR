package ica.BAR.Helpers;

/**
 * Created by jcapuano on 5/18/2014.
 */
public class DiceResources {
	public static int getWhiteBlack(int die) {
		int res = ica.BAR.R.drawable.whiteb10_0;
		switch (die)
		{
			case 1:					
				res = ica.BAR.R.drawable.whiteb10_1;
				break;
			case 2:
				res = ica.BAR.R.drawable.whiteb10_2;
				break;
			case 3:
				res = ica.BAR.R.drawable.whiteb10_3;
				break;
			case 4:
				res = ica.BAR.R.drawable.whiteb10_4;
				break;
			case 5:
				res = ica.BAR.R.drawable.whiteb10_5;
				break;
			case 6:
				res = ica.BAR.R.drawable.whiteb10_6;
				break;
			case 7:
				res = ica.BAR.R.drawable.whiteb10_7;
				break;
			case 8:
				res = ica.BAR.R.drawable.whiteb10_8;
				break;
			case 9:
				res = ica.BAR.R.drawable.whiteb10_9;
				break;
			case 0:					
			default:
				break;
		}
		return res;
	}

	public static int getRedWhite(int die) {
		int res = ica.BAR.R.drawable.redw10_0;
		switch (die)
		{
			case 1:					
				res = ica.BAR.R.drawable.redw10_1;
				break;
			case 2:
				res = ica.BAR.R.drawable.redw10_2;
				break;
			case 3:
				res = ica.BAR.R.drawable.redw10_3;
				break;
			case 4:
				res = ica.BAR.R.drawable.redw10_4;
				break;
			case 5:
				res = ica.BAR.R.drawable.redw10_5;
				break;
			case 6:
				res = ica.BAR.R.drawable.redw10_6;
				break;
			case 7:
				res = ica.BAR.R.drawable.redw10_7;
				break;
			case 8:
				res = ica.BAR.R.drawable.redw10_8;
				break;
			case 9:
				res = ica.BAR.R.drawable.redw10_9;
				break;
			case 0:					
			default:
				break;
		}
		return res;
	}
}
