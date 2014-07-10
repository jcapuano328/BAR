package ica.BAR.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.lang.reflect.*;

import ica.BAR.BarrageFragment;
import ica.BAR.CombatFragment;
import ica.BAR.VictoryFragment;
import ica.BAR.DopFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    private String customClassName;

	public TabsPagerAdapter(FragmentManager fm, String customClassName) {
		super(fm);
        this.customClassName = customClassName;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
            return new BarrageFragment();
		case 1:
            return new CombatFragment();
		case 2:
            if (!customClassName.isEmpty()) {
                try {
                    Class c = Class.forName("ica.BAR." + customClassName);
                    Constructor constructor = c.getConstructor();
                    return (Fragment)constructor.newInstance();
                }
                catch (Exception ex) {
                    Log.e("Custom Activity", "Failed to launch custom activity", ex);
                }
            }
            return new VictoryFragment();
		case 3:
            return new VictoryFragment();
		}

		return new Fragment();
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return !customClassName.isEmpty() ? 4 : 3;
	}

}
