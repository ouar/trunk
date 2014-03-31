package fr.gfi.android.quiz.activities.quiz;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fr.gfi.android.quiz.R;
import fr.gfi.quiz.json.entite.Reponse;

public class MyCustomAdaptater extends ArrayAdapter<Reponse> {

	private List<Reponse> lReponses = new ArrayList<Reponse>();
	private Activity context;
	private RelativeLayout layoutBarreNavigation = null;
	private LinearLayout layoutOfDynamicContent = null;

	public MyCustomAdaptater(Activity _context, int resource, List<Reponse> _lReponses) {
		super(_context, resource, _lReponses);
		context = _context;
		layoutOfDynamicContent = (LinearLayout) context.findViewById(R.id.layoutOfDynamicContent);
		layoutBarreNavigation = (RelativeLayout) context.findViewById(R.id.layoutBarre);
		lReponses = new ArrayList<Reponse>();
		lReponses.addAll(_lReponses);
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View viewReturn = null;
		
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			viewReturn = inflator.inflate(R.layout.reponse, null);

			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.checkbox = (CheckBox) viewReturn.findViewById(R.id.checkReponse);
			viewHolder.text = (TextView) viewReturn.findViewById(R.id.labelReponse);

			viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Reponse reponse = (Reponse) viewHolder.checkbox.getTag();
					reponse.setChoisie(buttonView.isChecked());
				}
			});
			viewReturn.setTag(viewHolder);
			viewHolder.checkbox.setTag(lReponses.get(position));
		}else {
			 viewReturn = convertView;
			((ViewHolder) viewReturn.getTag()).checkbox.setTag(lReponses.get(position));
		}
		ViewHolder holder = (ViewHolder) viewReturn.getTag();
		holder.text.setText(lReponses.get(position).getReponse().getLibelle());
		holder.checkbox.setChecked((lReponses.get(position).isChoisie()==null)?false:lReponses.get(position).isChoisie().booleanValue());
		return viewReturn;
	}

	@Override
	public int getCount() {
		return lReponses.size();
	}
	
	
}