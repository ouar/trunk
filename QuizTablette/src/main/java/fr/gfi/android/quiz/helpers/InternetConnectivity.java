package fr.gfi.android.quiz.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class InternetConnectivity {	
	public static boolean haveInternet(Context _context)
	{
		NetworkInfo info = (NetworkInfo) ((ConnectivityManager) _context
							.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		
		if(info == null || !info.isConnected())
		{
			return false;
		}
		
		if(info.isRoaming())
		{
			return false;
		}
		
		return true;
	}
	
	public static int whichConnection(Context _context)
	{
		NetworkInfo info = (NetworkInfo) ((ConnectivityManager) _context
				.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		
		return info.getType();
	}
	
}
