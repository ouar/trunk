package fr.gfi.android.quiz.helpers;


import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;
import fr.gfi.android.quiz.R;

public class ComponentsCustomization {
	SpannableStringBuilder sb = null; 
	static TextAppearanceSpan orangeTextAppearanceSpan = null;
	static TextAppearanceSpan grayTextAppearanceSpan = null;
	
	private static ComponentsCustomization _instance;

	private ComponentsCustomization(Context context) {
		
		sb = new SpannableStringBuilder();
		grayTextAppearanceSpan = new TextAppearanceSpan(context,
				R.style.GrayAppearance);
		orangeTextAppearanceSpan = new TextAppearanceSpan(context,
				R.style.OrangeAppearance);
	}

	public static ComponentsCustomization getInstance(Context context) {
		if (_instance == null) {
			_instance = new ComponentsCustomization(context);
		}
		return _instance;
	}
	
	public void sloganRenderText(TextView textviewComponent,
			String commercialText, String managertext, String chefProjetText, String OtherText)
	{
		if(sb.toString().isEmpty())
		{
			sb.append(commercialText);
			sb.setSpan(orangeTextAppearanceSpan, 0, sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			int start = sb.length();
			sb.append(managertext);
			sb.setSpan(orangeTextAppearanceSpan, start, sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			 start = sb.length();
			sb.append(chefProjetText);
			sb.setSpan(orangeTextAppearanceSpan, start, sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			 start = sb.length();
			sb.append(OtherText);
			sb.setSpan(grayTextAppearanceSpan, start, sb.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		
		textviewComponent.setText(sb);
	}
}
