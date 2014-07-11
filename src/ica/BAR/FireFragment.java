package ica.BAR;

import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.content.*;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.*;

import ica.BAR.Adapters.ModifierListItemAdapter;
import ica.BAR.Core.*;
import ica.BAR.Helpers.*;

public class FireFragment extends Fragment {
    
    private View rootView;
    
    private Spinner spinFireType;
    private Spinner spinFireSPs;
    private Spinner spinFireRange;
    
    private ListView listModifiers;
    private ModifierListItemAdapter adapterModifiers;
    
	private ImageView imgFireDie1;
	private ImageView imgFireDie2;
	private Button btnFireDiceRoll;

	private TextView txtFireResults;
    
    private Game game;
    private Fire fire;
    private Dice dice;
	private PlayAudio audio;
    private int type;
    private int strength;
    private int range;
    private ArrayList<Modifier> modifiers;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            final ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(rootView);  
        }
        else {
    
		    dice = new Dice(2, 0, 9);
		    audio = new PlayAudio (getActivity());
            fire = new Fire();
    
            Intent intent = getActivity().getIntent();
            game = Bar.getGame(intent.getIntExtra ("Game", -1));
        
            modifiers = Fire.getModifiers();
            type = 0;
            strength = 0;
            range = 0;
    
            rootView = inflater.inflate(R.layout.fire, container, false);
        
            spinFireType = (Spinner)rootView.findViewById(R.id.spinFireType);
            spinFireSPs = (Spinner)rootView.findViewById(R.id.spinFireStrength);
            spinFireRange = (Spinner)rootView.findViewById(R.id.spinFireRange);
    
            listModifiers = (ListView)rootView.findViewById(R.id.listFireModifiers);
        
		    imgFireDie1 = (ImageView)rootView.findViewById(R.id.imgFireDie1);
		    imgFireDie2 = (ImageView)rootView.findViewById(R.id.imgFireDie2);
		    btnFireDiceRoll = (Button)rootView.findViewById(R.id.btnFireDiceRoll);

            txtFireResults = (TextView)rootView.findViewById(R.id.txtFireResults);
            
		    spinFireType.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Fire.getTypes()));
		    spinFireType.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    type = pos;
                    updateResults();
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });

            spinFireSPs.setAdapter(new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_dropdown_item, Fire.getSPs()));
		    spinFireSPs.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    strength = pos;
			        updateResults();
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });            

            spinFireRange.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Fire.getRanges()));
		    spinFireRange.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    range = pos;
			        updateResults();
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });            

            adapterModifiers = new ModifierListItemAdapter(getActivity().getApplicationContext(), modifiers);
            adapterModifiers.registerDataSetObserver(new DataSetObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    updateResults();
                }
            });
            listModifiers.setAdapter(adapterModifiers);

		    imgFireDie1.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        incrementDie(1);
			        displayDice();
			        updateResults();
			    }
		    });
		    imgFireDie2.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        incrementDie(2);
			        displayDice();
			        updateResults();
			    }
		    });
		    btnFireDiceRoll.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        audio.play();
			        dice.roll();
			        displayDice();
			        updateResults();
			    }
		    });

		    displayTypes();
		    displaySPs();
		    displayRanges();
		    displayDice();
        }
        return rootView;
    }
    
	void updateResults() {
		String result = fire.resolve(dice.getDie(0), dice.getDie(1), strength, type, range, getModifiers());
		txtFireResults.setText(result, TextView.BufferType.NORMAL);
	}

    private ArrayList<Modifier> getModifiers() {
        ArrayList<Modifier> mods = new ArrayList<Modifier>();
        for (Modifier cm : modifiers) {
            if (cm.Count > 0) {
                mods.add(cm);
            }
        }
        return mods;
    }
    
	void displayTypes() {
		spinFireType.setSelection(type);
    }
	void displaySPs() {
		spinFireSPs.setSelection(strength);
    }
	void displayRanges() {
        spinFireRange.setSelection(range);
    }
    
	void displayDice() {
		imgFireDie1.setImageResource(DiceResources.getWhiteBlack(dice.getDie(0)));
		imgFireDie2.setImageResource(DiceResources.getRedWhite(dice.getDie(1)));
	}
 
	void incrementDie(int die) {
		int value = dice.getDie(die-1);
		if (++value > 6) value = 1;
		dice.setDie(die-1, value);
	}
}