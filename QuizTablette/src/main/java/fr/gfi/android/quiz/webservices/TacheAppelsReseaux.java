   package fr.gfi.android.quiz.webservices;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.helpers.Messages;
import fr.gfi.android.quiz.webservices.MyRestClient.RequestMethod;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;

public class TacheAppelsReseaux extends AsyncTask<Void, Void,Quiz> {

	public static  ProgressDialog dialog = null;
	public static Context applicationContext;
	public String url_to_parse = null;
	public static String jsonResult = null;
	public static boolean isTimeout = false;
	public static boolean canGetConnected = true;
	
	Quiz gfiQuiz = null;
	
	@Override
	protected void onPreExecute() {
		String systemMessage = applicationContext.getResources().getString(R.string.app_message);
		String etatConnection = applicationContext.getResources().getString(R.string.connection_status_encours);
		dialog = ProgressDialog.show(applicationContext,systemMessage,etatConnection, true);
	}
	
	@Override
	protected Quiz doInBackground(Void... arg0) {
		MyRestClient client = new MyRestClient(url_to_parse);
		try {
			Log.i("url_to_parse",url_to_parse);
			String json = client.Execute(RequestMethod.GET);
			Log.i("client.Execute(RequestMethod.GET);","Après client.Execute(RequestMethod.GET);");
		    gfiQuiz = JsonBuilder.getQuizBeanFromJson(json);
		    Log.i("Quiz object from webservice ", gfiQuiz.getLQuestions().toString());
			   return gfiQuiz;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return gfiQuiz;
}

	
	protected void onPostExecute(Quiz quizResult) {
		if(dialog != null){
			if(dialog.isShowing()  )  dialog.dismiss();
		}
		
		if(isTimeout  )
			Messages.parsingFailedCauseByTimeout(applicationContext);
		
		if(gfiQuiz != null){
			Log.i("gfiQuiz","gfiQuiz pas null");
			Messages.flashcodeSuccessAndJsonRetrieve(applicationContext, gfiQuiz);
		}else
		{
			if(canGetConnected == false)
			{
				Log.i("canGetConnected","Imossible de se connecter à l'hote");
				Messages.unknownHost(applicationContext);
			}else
			{
				Log.i("canGetConnected","Objet Quizz null ");
				Messages.noQuizObject(applicationContext);
			}
		}
	}
}
