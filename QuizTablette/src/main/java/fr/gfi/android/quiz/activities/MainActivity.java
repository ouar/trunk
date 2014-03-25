package fr.gfi.android.quiz.activities;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.constants.MagicValues;
import fr.gfi.android.quiz.flashcode.process.FlashCodeProcessActivity;
import fr.gfi.android.quiz.helpers.ComponentsCustomization;
import fr.gfi.android.quiz.helpers.ReusablesMethodes;

public class MainActivity extends AbstractActivityOrientationPortrait implements OnClickListener {

	// Attributes 
	TextView slogan = null;
	Button flascode_button = null;
	TextView copyright = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		slogan= (TextView)findViewById(R.id.slogan);
		flascode_button = (Button)findViewById(R.id.flascode_button);
		copyright = (TextView)findViewById(R.id.copyright);
		this.initialiseComponent();		
			
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_about:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void initialiseComponent()
	{
		ComponentsCustomization.getInstance(this).sloganRenderText(slogan,
				MagicValues.SLOGAN_COMMERCIAL_TEXT, MagicValues.SLOGAN_MANAGER_TEXT, 
				MagicValues.SLOGAN_CHEF_PROJET_TEXT, MagicValues.SLOGAN_OTHER_TEXT);

		
		flascode_button.setText(MagicValues.FLASHCODE_TEXT);
		flascode_button.setOnClickListener(this);
		
		copyright.setText(this.getResources().getString(R.string.copyright));
	}

	@Override
	public void onClick(View v) {
		if((Button)v == this.flascode_button)
			ReusablesMethodes.gotoNextActivity(this, FlashCodeProcessActivity.class);
			this.finish();
		
	}
}
