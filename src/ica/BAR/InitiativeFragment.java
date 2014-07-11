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

import ica.BAR.Core.*;
import ica.BAR.Helpers.*;

/**
 * Created by jcapuano on 6/7/2014.
 */
public class InitiativeFragment extends Fragment {
    private View rootView;

	private TextView txtBritish;
	private Button btnBritishMomentumPrev;
	private Button btnBritishMomentumNext;
	private EditText editBritishMomentum;

	private TextView txtAmerican;
	private Button btnAmericanMomentumPrev;
	private Button btnAmericanMomentumNext;
	private EditText editAmericanMomentum;

	private ImageView imgInitiativeDie1;
	private ImageView imgInitiativeDie2;
	private Button btnInitiativeDiceRoll;

	private TextView txtInitiativeResults;
    
    private Game game;
    private Saved saved;
    private Initiative initiative;
    private Dice dice;
	private PlayAudio audio;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            final ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup)
                ((ViewGroup) parent).removeView(rootView);  
        }
        else {
            initiative = new Initiative();
		    dice = new Dice(2, 1, 6);
		    audio = new PlayAudio (getActivity());
            
            Intent intent = getActivity().getIntent();
            game = Bar.getGame(intent.getIntExtra ("Game", -1));
            saved = Bar.getSaved(game);            
        
            rootView = inflater.inflate(R.layout.initiative, container, false);
        
		    // british
		    txtBritish = (TextView)rootView.findViewById(R.id.txtBritish);
		    btnBritishMomentumPrev = (Button)rootView.findViewById(R.id.btnBritishMomentumPrev);
		    btnBritishMomentumNext = (Button)rootView.findViewById(R.id.btnBritishMomentumNext);
		    editBritishMomentum = (EditText)rootView.findViewById(R.id.editBritishMomentum);
		    editBritishMomentum.setText("0");

		    // american
		    txtAmerican = (TextView)rootView.findViewById(R.id.txtAmerican);
		    btnAmericanMomentumPrev = (Button)rootView.findViewById(R.id.btnAmericanMomentumPrev);
		    btnAmericanMomentumNext = (Button)rootView.findViewById(R.id.btnAmericanMomentumNext);
		    editAmericanMomentum = (EditText)rootView.findViewById(R.id.editAmericanMomentum);
		    editAmericanMomentum.setText("0");
        
		    imgInitiativeDie1 = (ImageView)rootView.findViewById(R.id.imgInitiativeDie1);
		    imgInitiativeDie2 = (ImageView)rootView.findViewById(R.id.imgInitiativeDie2);
		    btnInitiativeDiceRoll = (Button)rootView.findViewById(R.id.btnInitiativeDiceRoll);

            txtInitiativeResults = (TextView)rootView.findViewById(R.id.txtInitiativeResults);
        
		    // british
		    btnBritishMomentumPrev.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        int value = getBritishMomentum();
			        if (--value < 1) value = 1;
			        editBritishMomentum.setText(Integer.toString(value));
			    }
		    });
		    btnBritishMomentumNext.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        int value = getBritishMomentum() + 1;
			        editBritishMomentum.setText(Integer.toString(value));
			    }
		    });
		    editBritishMomentum.addTextChangedListener(new TextWatcher() {
			    @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
			    @Override
                public void onTextChanged(CharSequence s, int start, int before, int end) {
                }
			    @Override
                public void afterTextChanged(Editable s) {
                }
            });
        
		    // american
		    btnAmericanMomentumPrev.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        int value = getAmericanMomentum();
			        if (--value < 1) value = 1;
			        editAmericanMomentum.setText(Integer.toString(value));
			    }
		    });
		    btnAmericanMomentumNext.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        int value = getAmericanMomentum() + 1;
			        editAmericanMomentum.setText(Integer.toString(value));
			    }
		    });
		    editAmericanMomentum.addTextChangedListener(new TextWatcher() {
			    @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
			    @Override
                public void onTextChanged(CharSequence s, int start, int before, int end) {
                }
                public void afterTextChanged(Editable s) {
                }
            });
            
            // dice
		    imgInitiativeDie1.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        incrementDie(1);
			        displayDice();
			        updateResults();
			    }
		    });
		    imgInitiativeDie2.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        incrementDie(2);
			        displayDice();
			        updateResults();
			    }
		    });
		    btnInitiativeDiceRoll.setOnClickListener(new OnClickListener() {
			    @Override
			    public void onClick(View arg0) {
			        audio.play();
			        dice.roll();
			        displayDice();
			        updateResults();
			    }
		    });

		    displayDice();
        }
        return rootView;
    }
    
	void updateResults() {
		String result = initiative.resolve(dice.getDie(0), dice.getDie(1), 
                                            game.getInitiativeModifier(saved.getBritishMorale()), getBritishMomentum(), 
                                            game.getInitiativeModifier(saved.getAmericanMorale()), getAmericanMomentum());
		txtInitiativeResults.setText(result, TextView.BufferType.NORMAL);
	}

	void displayDice() {
		imgInitiativeDie1.setImageResource(DiceResources.getWhiteBlack(dice.getDie(0)));
		imgInitiativeDie2.setImageResource(DiceResources.getRedWhite(dice.getDie(1)));
	}
 
	void incrementDie(int die) {
		int value = dice.getDie(die-1);
		if (++value > 6) value = 1;
		dice.setDie(die-1, value);
	}
    
	int getBritishMomentum() {
        String v = editBritishMomentum.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;           
	}
	int getAmericanMomentum() {
        String v = editAmericanMomentum.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;           
	}
    
    
    
}